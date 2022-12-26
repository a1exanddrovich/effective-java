package com.epam.service;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.TimeUnit;

import com.epam.entity.ValueObject;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;
import com.google.common.cache.RemovalListener;

@Log
@Log4j2
public class LRUCache implements CacheService {

    private final Cache<Integer, ValueObject> cache;

    public LRUCache(Integer capacity) {
        cache = CacheBuilder.newBuilder()
                .maximumSize(capacity)
                .concurrencyLevel(8)
                .removalListener((RemovalListener<Integer, ValueObject>) removalNotification -> LOG.info(String.format("Was removed key - %s, with value - %s",
                                removalNotification.getKey(),
                                removalNotification.getValue())))
                .expireAfterAccess(5, TimeUnit.SECONDS)
                .recordStats()
                .build();
    }

    @Override
    public ValueObject get(Integer key) {
        return cache.getIfPresent(key);
    }

    @Override
    public void put(Integer key, ValueObject valueObject) {
        cache.put(key, valueObject);
    }

    public void logStats() {
        CacheStats stats = cache.stats();
        LOG.info(String.format("Average time spent for putting new values into the cache - %s, number of cache evictions - %s",
                stats.averageLoadPenalty(),
                stats.evictionCount()));
    }

}
