package com.gmail.gorbasenko245;

import com.gmail.gorbasenko245.service.MyHashMap;
import com.gmail.gorbasenko245.service.impl.MyHashMapImpl;

public class Main {
    public static void main(String[] args) {
      MyHashMap<Integer, Long> map = new MyHashMapImpl<Integer, Long>();

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
      map.put(14, 140L);
      map.put(15, 140L);
      map.put(16, 140L);
      map.put(17, 140L);
      map.put(18, 140L);

      System.out.println(map);
      System.out.println(map.size());
      System.out.println(map.get(13));
    }
}
