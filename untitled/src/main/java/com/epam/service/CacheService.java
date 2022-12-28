package com.epam.service;

import com.epam.entity.ValueObject;

public interface CacheService {

    ValueObject get(Integer key);

    void put(Integer key, ValueObject valueObject);

}
