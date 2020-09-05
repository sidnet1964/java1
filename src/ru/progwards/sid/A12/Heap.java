package ru.progwards.sid.A12;
//  версия с ceilingKey
import ru.progwards.java2.lessons.gc.InvalidPointerException;
import ru.progwards.java2.lessons.gc.OutOfMemoryException;

import java.util.Arrays;

import java.util.concurrent.*;
import static java.lang.System.out;

public class Heap {
//    static short sFill = 1; //  символ заполнения
//    static short sClea = 1; //  символ заполнения
//    long start, stop;
//    int iter = 0;
    static Integer count1 = 0;
    static int delBlock = 0;    //  счетчик удалений
    static int insBlock = 0;    //  счетчик добавлений
    static int freBlock = 0;    //  счетчик освобождений
    static int allSize;         //  общий объем занятой памяти
    static int maxHeapSize;     //  размер кучи
    byte[] bytes;               //  собственно, куча
    static ConcurrentSkipListMap<Integer, Integer> busyTree; //  карта занятых блоков (+)
    static ConcurrentSkipListMap<Integer, ConcurrentLinkedQueue<Integer>> freeTree;
    ConcurrentLinkedQueue<Integer> freeSet;   //  пустая заготовка для одного набора

    public Heap(int maximHeapSize) {
//        start = System.currentTimeMillis(); //  отсчет от момента создания объекта
        this.maxHeapSize = maximHeapSize;
        bytes = new byte[this.maxHeapSize];
        busyTree = new ConcurrentSkipListMap<>();
        freeTree = new ConcurrentSkipListMap<>();
//        freeSet = new ConcurrentLinkedQueue<>(Set.of(0));
        freeSet = new ConcurrentLinkedQueue<>();
        freeSet.add(0);
        freeTree.put(this.maxHeapSize, freeSet);
    }
    //  --------------------------------
    //  распечатать цепочку блоков
    void memPrint(Iterable<Allock> myList, short nFill){
        out.println("-----------------");
        for (Allock elem : myList ){
            out.println(elem + "~" + nFill);
        }
        out.println("=================");
    }
    //  ------------------------------------
    static synchronized void incAllSize(int size) {
        allSize += size;
    }
    //  заполнить выделенный блок данными
    void memFill(int ptr, int size, byte data){
        for (int i = 0; i<size; i++)
            bytes[i+ptr] = data;
    }
    //  ========================================================================
    static class Malloc implements Callable<Integer> {
        int size;
        public Malloc(int size) {
            this.size = size;
        }
        @Override
        public Integer call() throws Exception {
            Integer res = malloc(size);
            return res;
        }
    }
    //  ========================================================================
    static class Free implements Runnable {
        int ptr;
        public Free(int ptr) {
            this.ptr = ptr;
        }
        @Override
        public void run() {
            free(ptr);
        }
    }
    //  --------------------------------
    //  "размещает", т.е. помечает как занятый блок памяти с количеством ячеек массива heap равным size
    public static int malloc(int size){
        //  поиск сбободного участка
        if (freeTree.size() == 0){
            out.println("freeTree.size() == 0 -> " + freeTree.size());
            throw new OutOfMemoryException("Нет свободных блоков для ", size);
        }
        out.println(Thread.currentThread().getName() + " mall seek = " + size + " freeTree = " + freeTree + " busyTree = " + busyTree.size());
//        long start = System.currentTimeMillis();
        synchronized (count1) {
        Integer firstKey = freeTree.ceilingKey(size);
//            out.println(Thread.currentThread().getName() + " mall size+ = " + freeTree.size() + " firstPtr = " + firstPtr);
//          if (firstKey == null)   //  запустить компактизацию
            if (firstKey != null) {

                ConcurrentLinkedQueue<Integer> findSet = freeTree.get(firstKey); //  набор адресов
                int findPtr = findSet.poll();  //  возвращает первый элемент и удаляет
                if (findSet.isEmpty())
                    freeTree.remove(firstKey);
                else {
                    //  блок для исследования ситуации
                    out.println(Thread.currentThread().getName() + " mall Запрос  = " + size + " остаток в findSet = " + findSet);
                }
                //  откусить от этого блока нужный кусок
//debug            memFill(pointBeginBlock, size, (byte) sFill++);  //  заполнить "кучу" данными
                incAllSize(size);     //  allSize += size   ####################
                busyTree.put(findPtr, size);    //  создать запись в списке занятых областей
                if (size == firstKey) {
                    delBlock++; //  уже удален (при чтении)
                }
                else {  //  вариант с перезаписью блока
                    //  остался кусок размером (findSize - size) с адресом (findPtr + size)
                    freeTreeAdd(firstKey - size, findPtr + size);
                    insBlock++;
                }
                return findPtr;
            }
        }
        out.println("-- Ошибка размещения = " + size);
        out.println(freeTree);
        out.println(busyTree);

        //        memPrint(freeHash, (short) 0);
        //        memPrint(busyList, (short) 1);
        //  проанализировать общий объем памяти
        if (maxHeapSize - allSize < size)
            throw new OutOfMemoryException("Нет достаточного размера памяти", size);

        throw new OutOfMemoryException("Нет свободного участка памяти", size);
    }
    //  --------------------------------
    public static synchronized void freeTreeAdd(int keyTree, int valueTreeAsSet) {
        if (freeTree.containsKey(keyTree)){
            //  такой элемент существует, найти его
            ConcurrentLinkedQueue<Integer> existSet = freeTree.get(keyTree);
            existSet.add(valueTreeAsSet);
            int x = 0;
        }
        else {  //  такого элемента еще нет - создать его
            ConcurrentLinkedQueue<Integer> existSet = new ConcurrentLinkedQueue<>();
            freeTree.put(keyTree, existSet);
            existSet.add(valueTreeAsSet);
            int y = 0;
        }
    }

    //  --------------------------------
    //  "удаляет", т.е. помечает как свободный блок памяти по "указателю"
    public static synchronized void free(int ptr){
        freBlock++;
        int busySize = busyTree.get(ptr);   //  размер освобождаемого блока
        if (busySize > 0) {
            incAllSize(-busySize);     //  allSize += size   ####################
            busyTree.remove(ptr);   //  удалить блок из списка занятых
            freeTreeAdd(busySize, ptr);     //  добавить блок в список свободных
            out.println(Thread.currentThread().getName() + " free size- = " + freeTree.size() + " ptr = " + ptr);
            return;
        }
        throw new InvalidPointerException("Неверный адрес участка памяти", ptr);
    }

    //  --------------------------------
    @Override
    public String toString() {
        return "Heap{" + Arrays.toString(bytes) + '}';
    }

    class Allock {
        public int ptr;
        public int size;

        public Allock(int ptr, int size) {
            this.ptr = ptr;
            this.size = size;
        }
    }
    //  ------------------------------------
    public static void main(String[] args) {
        final int mSize = 128;
        Heap extMemory = new Heap(mSize);
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<Integer> future1 = es.submit(new Malloc(2));
        Future<Integer> future2 = es.submit(new Malloc(4));
        Future<Integer> future3 = es.submit(new Malloc(6));
        Future<Integer> future4 = es.submit(new Malloc(8));
//        es.shutdown();
        out.println(extMemory.freeTree);
        out.println(extMemory.busyTree);
        es.execute(new Free(0));
        es.execute(new Free(12));
        es.shutdown();
        out.println(extMemory.freeTree);
        out.println(extMemory.busyTree);
    }
    //  ------------------------------------
    public static final int mathRandomInterval( int min, int max) {
        return (int)((Math.random() * (max - min)) + min);
    }
    //
    //  вспомагательная процедура заполнения
    void aMallok(int step){
        switch (step) {
            case 0: //  случайная последовательность, заполнение половины
                for (int i = 0; i < maxHeapSize/3; i++){
                    try {
                        int rand = mathRandomInterval(1, 6);
                        malloc(rand);
                    } catch (OutOfMemoryException e){
                        out.println(e);
                        return;
                    }
                }
                break;
            case 1: //  возрастающая последовательность
                for (int i = 0; i < maxHeapSize; i++){
                    try {
                        malloc(i+1);
                    } catch (OutOfMemoryException e){
                        out.println(e);
                        return;
                    }
                }
                break;
            case 2: //  убывающая последовательность
                for (int i = (int) Math.sqrt(maxHeapSize); i > 0; i--){
                    try {
                        malloc(i+1);
                    } catch (OutOfMemoryException e){
                        out.println(e);
                        return;
                    }
                }
                break;
        }
    }
}
//------------------------------------------------------------------------------
