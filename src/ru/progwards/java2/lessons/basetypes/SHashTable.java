package ru.progwards.java2.lessons.basetypes;

public class SHashTable<T> {
    Object[] table;

    SHashTable(int n) {
        table = new Object[n];
    }

    public int getHash(int key) {
        return key % table.length;
    }

    void add(int key, T item) {
        int index = getHash(key);
        table[index] = item;
    }

    T get(int key) {
        int index = getHash(key);
        return (T)table[index];
    }
}
