package com.example.core.service.base;

public interface IBaseService<K, V> {

    V getById(K id);
}
