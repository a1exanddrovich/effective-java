package com.epam.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import org.junit.jupiter.api.Test;

import com.epam.entity.ValueObject;

class LRUCacheTest {

    public static final int CAPACITY = 2;

    private final CacheService sut = new LRUCache(CAPACITY);

    @Test
    void shouldCacheProperlyViaLRUStrategy() {
        sut.put(1, new ValueObject("TestValue1"));
        sut.put(2, new ValueObject("TestValue2"));

        assertThat(sut.get(1).getValue(), is("TestValue1"));
        assertThat(sut.get(2).getValue(), is("TestValue2"));

        sut.put(3, new ValueObject("TestValue3"));

        assertThat(sut.get(1), nullValue());
    }

}
