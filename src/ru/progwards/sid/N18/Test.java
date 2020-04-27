package ru.progwards.sid.N18;

import java.util.Set;
import java.util.TreeMap;

public class Test {
    public static void main(String[] args) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(3, 11);
        map.put(2, 11);
        map.put(1, 11);
        map.put(5, 11);
        map.put(4, 11);

        Integer highestKey = map.lastKey();
        Integer lowestKey = map.firstKey();
        System.out.println(highestKey);
        System.out.println(lowestKey);
        Set<Integer> keysLessThan3 = map.headMap(3).keySet();
        Set<Integer> keysGreaterThanEqTo3 = map.tailMap(3).keySet();

//        assertEquals(new Integer(5), highestKey);
//        assertEquals(new Integer(1), lowestKey);
//        assertEquals("[1, 2]", keysLessThan3.toString());
//        assertEquals("[3, 4, 5]", keysGreaterThanEqTo3.toString());

    }
   }
