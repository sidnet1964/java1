package ru.progwards.java2.lessons.basetypes;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

//  Реализовать класс DoubleHashTable - хэш таблица с двойным хэшированием
//  При двойном хешировании используются две независимые хеш-функции h1(k) и h2(k).
//  Пусть k — это наш ключ, m — размер нашей таблицы, n mod m — остаток от деления n на m,
//  тогда сначала исследуется ячейка с адресом h1(k), если она уже занята,
//  то рассматривается (h1(k)+h2(k)) mod m, затем (h1(k)+2⋅h2(k)) mod m и так далее.
//  В общем случае идёт проверка последовательности ячеек (h1(k)+i⋅h2(k)) mod m где i=(0,1,...,m−1)
public class DoubleHashTable<K,V> implements Iterable<K>{
    @Override
    public String toString() {
        return "DoubleHashTable{" +
                "table=" + Arrays.toString(table) +
                '}';
    }
    //  ----------------------------------------------
    //  описание итератора
    @NotNull
    @Override
    public Iterator<K> iterator() {
        return new MyIterator<>(this.table[0]);
    }
    class MyIterator<K> implements Iterator<K>{
        DHashItem<K,V> curr;
        private int ind;
        MyIterator(DHashItem<K,V> curr){
            this.curr = curr;
            ind = -1;
        }
        @Override
        public boolean hasNext() {
            if (ind < size-1) {   //  table.length-1
                return true;
            }
            return false;
        }

        @Override
        public K next() {
            ind++;
            try {
                return (K)table[ind].key;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw e;
            }
        }
    }
    //  ----------------------------------------------------
    //  описание класса-элемента
    class DHashItem<K,V> {
        //  переменные класса-элемента
        private V item;
        private K key;

        DHashItem(K key, V item) {
            this.key = key;
            this.item = item;
        }
        K getKey() {
            return key;
        }
        V getItem() {
            return item;
        }
        boolean equals(DHashItem<K,V> item) {
            return key == item.key;
        }

        @Override
        public String toString() {
            return "{" +
                    "item=" + item +
                    ", key=" + key +
                    '}';
        }
    }
    //  ----------------------------------------------------
    //  описание класса
    DHashItem<K,V>[] table;   //  переменная класса
    boolean[] delet;
    private int size;   //  количество элементов
    //  конструктор класса
    DoubleHashTable(int n) { //  n = 101
        table = new DHashItem[n];
        delet = new boolean[n];
        size = 0;
    }
//    void add(Item item) {
    void add(K key, V item) {
        int x = getHash1(key);
        int y = getHash2(key);
        for (int i = 0; i < table.length; i++) {
            if (table[x] == null || delet[x]) {
                DHashItem<K,V> li = new DHashItem(key, item);
                table[x] = li;
                delet[x] = false;
                size++;
                return;
            }
            x = (x + i * y) % table.length;
        }
        System.out.println("Необходимо расширение таблицы");
        resize();
        add(key, item); //  повторный вызов
        //        table.resize(); // ошибка, требуется увеличить размер таблицы
    }
    //  увеличение размера таблицы
    void resize(){
        int oldSize = table.length;
        int newSize = oldSize * 2;
        //  интересная конструкция с объявлением нового массива
        DHashItem<K,V>[] newArray = new DHashItem[newSize];
        System.arraycopy(table, 0, newArray, 0, oldSize);
        table =  newArray;  //  массив увеличенного размера
        boolean[] newDelet = new boolean[newSize];
        System.arraycopy(delet, 0, newDelet, 0, oldSize);
        delet = newDelet;
    }
    V get(K key) {
        int x = getHash1(key);
        int y = getHash2(key);
        for (int i = 0; i < table.length; i++) {
            if (table[x] != null)
                if (table[x].key == key && !delet[x])
                    return table[x].item;
                else
            return null;
            x = (x + y) % table.length;
        }
        return null;
    }
    //  2.3 - удалить элемент по ключу
    public void remove(K key){
        int x = getHash1(key);
        int y = getHash2(key);
        for (int i = 0; i < table.length; i++) {
            if (table[x] != null)
                if (table[x].key == key) {
                    delet[x] = true;
                    size--;
                }
                else
                    return;
            x = (x + y) % table.length;
        }
    }
    //  2.4 - изменить значение ключа у элемента с key1 на key2
    public void change(K key1, K key2){
        int x = getHash1(key1);
        int y = getHash2(key1);
        for (int i = 0; i < table.length; i++) {
            if (table[x] != null)
                if (table[x].key == key1)
                    table[x].key = key2;
                else
                    return;
            x = (x + y) % table.length;
        }
    }
    //  2.5 - получить количество элементов
    public int size() {
        return size;
    }
    //  2.6
    public Iterator<DoubleHashTable<K,V>> getIterator(){
        return null;
    }
    public int getHash1(K key) {
        return (int)key % table.length;  //  key % table.length
    }
    //  h2(k)=k mod (m−1)+1
    public int getHash2(K key) {
        return (int)key % (table.length - 1) + 1;
    }
}
