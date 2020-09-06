package com.gmail.gorbasenko245.model;

import java.util.Objects;

public class Node<K, V> {

    private K key;
    private V value;
    private int hash;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<K, V> node = (Node) o;
        return (Objects.equals(key, node.key) &&
                Objects.equals(value, node.value)) || (Objects.equals(hash, node.hash));
    }

    @Override
    public int hashCode() {
        hash = 31;
        hash = hash * 17 + key.hashCode();
        hash = hash * 17 + value.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", value=" + value +
                ", hash=" + hash +
                '}';
    }
}
