package com.gmail.gorbasenko245.service.impl;

import com.gmail.gorbasenko245.model.Node;
import com.gmail.gorbasenko245.model.TestObj;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(value = JUnit4.class)
public class MyHashMapImplTest<K, V> {

    @Test
    public void testSizeMethod() {
        // given
        final MyHashMapImpl<Integer, Long> map = new MyHashMapImpl<Integer, Long>();

        // when
        map.put(1, 11L);
        map.put(2, 22L);
        map.put(3, 33L);

        // expected
        assertEquals(map.size(), 3);
    }

    @Test(expected = NullPointerException.class)
    public void testPutMethodIfInputKeyEqualsNull() {
        // given
        final MyHashMapImpl<Integer, Long> map = new MyHashMapImpl<Integer, Long>();

        // when
        map.put(null, 11L);
    }

    @Test
    public void testPutMethodAddingNewNode() {
        // given
        final MyHashMapImpl<Integer, Long> map = new MyHashMapImpl<Integer, Long>();
        final int key = 1;
        final long value = 11L;
        final Node<Integer, Long> node = new Node<>(key, value);

        // when
        map.put(1, 11L);

        // expected
        assertEquals(map.getHashtable()[0], node);
    }

    @Test
    public void testPutMethodUpdateNodeByExistingKey() {
        // given
        final MyHashMapImpl<Integer, Long> map = new MyHashMapImpl<Integer, Long>();
        final int key = 1;
        final long oldValue = 11L;
        final long newValue = 123L;
        final Node<Integer, Long> expectedNode = new Node<>(key, newValue);
        map.put(key, oldValue);

        // when
        map.put(key, newValue);

        // expected
        assertEquals(map.getHashtable()[0], expectedNode);
    }

    @Test
    public void testPutAndGetMethodsInCollisionCase() {
        // given
        final MyHashMapImpl<Object, Object> objectObjectMyHashMap = new MyHashMapImpl<Object, Object>();
        final TestObj key1 = new TestObj("123", 123, 123);
        final TestObj key2 = new TestObj("1234", 123, 123);

        // when
        objectObjectMyHashMap.put(key1, "Hello1");
        objectObjectMyHashMap.put(key2, "Hello2");

        // expected
        assertEquals(objectObjectMyHashMap.size(), 2);
        assertEquals(objectObjectMyHashMap.get(key1), "Hello1");
        assertEquals(objectObjectMyHashMap.get(key2), "Hello2");
    }

    @Test(expected = NullPointerException.class)
    public void testGetMethodIfInputKeyEqualsNull() {
        // given
        final MyHashMapImpl<Integer, Long> map = new MyHashMapImpl<Integer, Long>();

        // when
        map.get(null);
    }

    @Test
    public void testGetMethod() {
        // given
        final MyHashMapImpl<Integer, Long> map = new MyHashMapImpl<>();
        final int key = 2;
        final long value = 22L;

        // when
        map.put(1, 11L);
        map.put(key, value);
        map.put(3, 33L);

        // expected
        assertEquals(map.get(key), (Long) value);

    }

    @Test
    public void testIfSizeOfMapMoreThanThresholdThenMapWillExtendInTwice() {
        // given
        final MyHashMapImpl<Integer, Long> map = new MyHashMapImpl<>();
        final int lengthOfHashtableBeforeExtension = 16;
        final int expectedLengthOfHashtableAfterExtension = lengthOfHashtableBeforeExtension * 2;
        assertEquals(map.getHashtable().length, lengthOfHashtableBeforeExtension);

        // when
        map.put(1, 11L);
        map.put(2, 22L);
        map.put(3, 33L);
        map.put(4, 44L);
        map.put(5, 55L);
        map.put(6, 66L);
        map.put(7, 77L);
        map.put(8, 88L);
        map.put(9, 99L);
        map.put(10, 100L);
        map.put(11, 110L);
        map.put(12, 120L);
        map.put(13, 130L);

        // expected
        assertEquals(map.getHashtable().length, expectedLengthOfHashtableAfterExtension);
    }
}