package com.gmail.gorbasenko245.service;

public interface MyHashMap<K, V> {

    void put(K k, V v);

    V get(K k);

    int size();
}
