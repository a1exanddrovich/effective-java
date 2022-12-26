package com.epam.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.epam.entity.ValueObject;
import com.epam.util.KeyVersusValueDoubleLinkedListContainer;
import com.epam.util.Node;

@RequiredArgsConstructor
@Log
public class LFUCache implements CacheService {

    private static final Integer INITIAL_VALUE_FREQUENCY = 1;

    private final Map<Integer, Node> keys = new ConcurrentHashMap<>();
    private final Map<Integer, KeyVersusValueDoubleLinkedListContainer> frequency = new ConcurrentHashMap<>();
    private final int capacity;

    @Override
    public ValueObject get(Integer key) {
        if (!keys.containsKey(key)) {
            return null;
        }
        return getNodeByKey(key).getValue();
    }

    @Override
    public void put(Integer key, ValueObject valueObject) {
        if (capacity == 0) {
            return;
        }

        if (keys.containsKey(key)) {
            Node node = getNodeByKey(key);
            node.setValue(valueObject);
        } else {
            if (keys.size() == capacity) {
                Integer leastFrequent = defineLeastFrequencyValue();
                KeyVersusValueDoubleLinkedListContainer leastFrequentList = frequency.get(leastFrequent);
                Node node = leastFrequentList.deleteFirstNode();
                freeByNode(node);
            }
            Node node = new Node(key, valueObject);
            addFrequencyIfAbsent(INITIAL_VALUE_FREQUENCY);
            addNodeToFrequency(node, INITIAL_VALUE_FREQUENCY);

            keys.put(key, node);
        }
    }

    private Node getNodeByKey(Integer key) {
        if (!keys.containsKey(key)) {
            return null;
        }

        Node currentNode = keys.get(key);

        KeyVersusValueDoubleLinkedListContainer list = frequency.get(currentNode.getFrequency());
        list.deleteNodeByKey(currentNode.getKey());

        currentNode.incrementFrequency();
        addFrequencyIfAbsent(currentNode.getFrequency());
        addNodeToFrequency(currentNode, currentNode.getFrequency());

        return currentNode;
    }

    private void addFrequencyIfAbsent(Integer frequencyValue) {
        if (!frequency.containsKey(frequencyValue)) {
            frequency.put(frequencyValue, new KeyVersusValueDoubleLinkedListContainer());
        }
    }

    private void addNodeToFrequency(Node node, Integer frequencyValue) {
        frequency.get(frequencyValue).addNode(node);
    }

    private Integer defineLeastFrequencyValue() {
        int leastFrequent = Integer.MAX_VALUE;
        for (Integer frequent : frequency.keySet()) {
            if (frequency.get(frequent).isEmpty()) {
                continue;
            }
            leastFrequent = Math.min(frequent, leastFrequent);
        }

        return leastFrequent;
    }

    private void freeByNode(Node node) {
        Integer key = node.getKey();
        LOG.info(String.format("Was removed key - %s, with value - %s",
                key,
                node.getValue()));
        keys.remove(key);
    }

}
