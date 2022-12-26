package com.epam.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.epam.entity.ValueObject;

@NoArgsConstructor
public class Node {

    //package-private
    @Getter
    Integer key;
    //package-private
    @Getter
    @Setter
    ValueObject value;
    //package-private
    @Getter
    Integer frequency;
    //package-private
    Node prev;
    //package-private
    Node next;

    public Node(Integer key, ValueObject value) {
        this.key = key;
        this.value = value;
        this.frequency = 1;
    }

    public void incrementFrequency() {
        this.frequency++;
    }

}
