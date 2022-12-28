package com.epam.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import org.junit.jupiter.api.Test;

import com.epam.entity.ValueObject;

class LFUCacheTest {

    public static final int CAPACITY = 2;

    private final CacheService sut = new LFUCache(CAPACITY);

    @Test
    void shouldCacheProperlyViaLFUStrategy() {
        sut.put(1, new ValueObject("TestValue1"));
        sut.put(2, new ValueObject("TestValue2"));

        assertThat(sut.get(1).getValue(), is("TestValue1"));

        sut.get(1);
        sut.get(1);

        sut.put(3, new ValueObject("TestValue3"));

        assertThat(sut.get(2), nullValue());
        assertThat(sut.get(1).getValue(), is("TestValue1"));
    }

}
