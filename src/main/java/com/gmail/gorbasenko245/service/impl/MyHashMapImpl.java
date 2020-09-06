package com.gmail.gorbasenko245.service.impl;

import com.gmail.gorbasenko245.model.Node;
import com.gmail.gorbasenko245.service.MyHashMap;

import java.util.Arrays;

public class MyHashMapImpl<K, V> implements MyHashMap<K, V> {

    private Node<K, V>[] hashtable;
    private int threshold;
    private int size;
    private static final float LOAD_FACTOR = 0.75f;

    public MyHashMapImpl() {
        size = 0;
        hashtable = new Node[16];
        threshold = (int) (hashtable.length * LOAD_FACTOR);
    }

    @Override
    public void put(final K key, final V value) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (size >= threshold) {
            threshold *= 2;
            extensionArray();
        }
        final Node<K, V> newNode = new Node<K, V>(key, value);
        final int newKeyHash = keyHash(key);
        final int index = getIndexFromKeyHash(newKeyHash);
        newNode.setHash(newKeyHash);

        //add new node
        if (hashtable[index] == null) {
            saveToTable(newNode, index);
        } else {
            //update by key
            final Node<K, V> currentNode = hashtable[index];
            if (isEqualsKey(key, newKeyHash, currentNode)) {
                hashtable[index].setValue(value);
            } else {
                //in case of collision
                int i = incrementCyclically(index);
                while (true) {
                    if (hashtable[i] == null) {
                        saveToTable(newNode, i);
                        return;
                    } else {
                        i = incrementCyclically(i);
                    }
                }
            }
        }
    }

    @Override
    public V get(final K k) {
        if (k == null) {
            throw new NullPointerException();
        }

        final int keyHash = keyHash(k);
        final int index = getIndexFromKeyHash(keyHash);

        int iterationCount = 0;
        int i = getCyclically(index);
        while (iterationCount < hashtable.length) {
            final Node<K, V> currentNode = hashtable[i];
            if (isEqualsKey(k, keyHash, currentNode)) {
                return currentNode.getValue();
            }
            //in case of collision
            i = incrementCyclically(i);
            iterationCount++;
        }
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    private int incrementCyclically(int i) {
        return getCyclically(i + 1);
    }

    private boolean isEqualsKey(K newKey, int newKeyHash, Node<K, V> currentNode) {
        return currentNode.getKey().equals(newKey) && currentNode.getHash() == newKeyHash;
    }

    private void saveToTable(final Node<K, V> newNode, final int i) {
        hashtable[i] = newNode;
        size++;
    }

    private int getCyclically(int index) {
        return (index) % hashtable.length;
    }

    private int keyHash(K key) {
        int hash = 31;
        hash = hash + key.hashCode();
        return hash;
    }

    private int getIndexFromKeyHash(final int hash) {
        return Math.abs(getCyclically(hash));
    }

    private void extensionArray() {
        final Node<K, V>[] temp = hashtable;
        hashtable = new Node[temp.length * 2];
        System.arraycopy(temp, 0, hashtable, 0, temp.length);
    }

    @Override
    public String toString() {
        return "MyHashMapImpl{" +
                "hashtable=" + Arrays.toString(hashtable) +
                '}';
    }

    //for testing
    public Node<K, V>[] getHashtable() {
        return hashtable;
    }
}
