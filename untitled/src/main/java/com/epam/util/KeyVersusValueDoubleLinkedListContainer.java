package com.epam.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class KeyVersusValueDoubleLinkedListContainer {

    private final Map<Integer, Node> nodes = new ConcurrentHashMap<>();

    private final Node head;
    private final Node tail;

    public KeyVersusValueDoubleLinkedListContainer() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public void addNode(Node node) {
        tail.prev.next = node;
        node.prev = tail.prev;
        tail.prev = node;
        node.next = tail;
        nodes.put(node.key, node);
    }

    public Node deleteNodeByKey(Integer key) {
        if (!nodes.containsKey(key)) {
            return null;
        }
        Node currentNode = nodes.get(key);
        currentNode.prev.next = currentNode.next;
        currentNode.next.prev = currentNode.prev;
        nodes.remove(key);
        return currentNode;
    }

    public Node deleteFirstNode() {
        if (head.next == tail) {
            return null;
        }
        return deleteNodeByKey(head.next.key);
    }

    public boolean isEmpty() {
        return nodes.isEmpty();
    }

}
