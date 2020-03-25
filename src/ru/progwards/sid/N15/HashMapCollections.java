package ru.progwards.sid.N15;

import java.util.*;

public class HashMapCollections {
    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        for (int i = 1; i <= 5; i++) hashMap.put(i, "Строка" + i);
        System.out.println(hashMap);
        Set<Integer> keys = hashMap.keySet();
        Collection<String> collection = hashMap.values();
        Set<Map.Entry<Integer, String>> entries = hashMap.entrySet();
        System.out.println(keys);
        System.out.println(collection);
        System.out.println(entries);
    }
}
