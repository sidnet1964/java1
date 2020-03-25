package ru.progwards.sid.N15;

import java.util.*;

public class Mapper {
    static HashMap<Integer, String> a2map(int[] akey, String[] aval){
        HashMap<Integer, String> hashMap = new HashMap<>();
        for (int i=0; i<akey.length; i++)
            hashMap.put(akey[i], aval[i]);
        return hashMap;
    }
//  вставляет в HashMap пару <n> "Число n", если она там отсутствует,
//  Проверить от 1 до максимального числа size включительно.
//  Метод возвращает количество добавленных элементов. Пример пары: 1 "Число 1"
    static int fillHoles(Map<Integer, String> map, int size){
        int count = 0;
        for (int i = 1; i <= size; i++)
            if  (map.putIfAbsent(i, "Число " + i) == null)
                count++;
        return count;
    }
//  который добавляет пару key-value в map при выполнении следующих условий:
//значение с таким key должно отсутствовать
//значение key долно быть больше головы TreeMap
//значение key долно быть меньше хвоста TreeMap
    static void checkAndAdd(TreeMap<Integer, String> map, Integer key, String value){
        if (!map.isEmpty() && Integer.compare(key, map.firstKey()) > 0 && Integer.compare(key, map.lastKey()) < 0)
            map.putIfAbsent(key, value);
        System.out.println(map);
    }
    public static void main(String[] args) {
        int akey1[] = {9, 7, 5};
        String aval1[] = {"Вася", "Петя", "Даша"};
        System.out.println(a2map(akey1, aval1));
        HashMap<Integer, String> hashMap = new HashMap<>();
        for (int i = 0; i <= 10; i+=2) hashMap.put(i, "Число " + i);
        System.out.println(fillHoles(hashMap, 12));
        TreeMap<Integer, String> treeMap = new TreeMap<>();
//        for (int i = 0; i <= 20; i+=2) treeMap.put(i, "Число " + i);
        checkAndAdd(treeMap, 0, "Team 11");
        checkAndAdd(treeMap, 0, "Team 11");
    }
}
