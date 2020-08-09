package ru.progwards.sid.semaphore;

import ru.progwards.java2.lessons.gc.InvalidPointerException;
import ru.progwards.java2.lessons.gc.OutOfMemoryException;

import java.util.*;
import java.util.concurrent.*;

public class Heap {
    static short sFill = 1; //  символ заполнения
    static short sClea = 1; //  символ заполнения
    long start, stop;
    int iter = 0;
    static int delBlock = 0;    //  счетчик удалений
    static int insBlock = 0;    //  счетчик добавлений
    static int freBlock = 0;    //  счетчик освобождений
    static int allSize;            //  занято памяти
    static int maxHeapSize;        //  размер кучи
    byte[] bytes;           // - собственно, куча
    static ConcurrentSkipListMap<Integer, Integer> busyTree; //  карта занятых блоков (+)
    static ConcurrentSkipListMap<Integer, ConcurrentSkipListSet<Integer>> freeTree;
    ConcurrentSkipListSet<Integer> freeSet;   //  пустая заготовка для одного набора

    public Heap(int maxHeapSize) {
//        start = System.currentTimeMillis(); //  отсчет от момента создания объекта
        this.maxHeapSize = maxHeapSize;
        bytes = new byte[this.maxHeapSize];
        busyTree = new ConcurrentSkipListMap<>();
        freeTree = new ConcurrentSkipListMap<>();
//        freeSet = new ConcurrentSkipListSet<>();
//        freeSet.add(0);
        freeSet = new ConcurrentSkipListSet<>(Set.of(0));
        freeTree.put(maxHeapSize, freeSet);
    }
    //  --------------------------------
    //  распечатать цепочку блоков
    void memPrint(Iterable<Allock> myList, short nFill){
        System.out.println("-----------------");
        for (Allock elem : myList ){
            System.out.println(elem + "~" + nFill);
        }
        System.out.println("=================");
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
//        @Override
//        public void run() {
//            malloc(size);
//        }
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
            System.out.println("freeTree.size() == 0 -> " + freeTree.size());
            throw new OutOfMemoryException("Нет свободных блоков для ", size);
        }
//        System.out.println("seek = " + size + " freeTree = " + freeTree);
//        long start = System.currentTimeMillis();
        Map.Entry<Integer, ConcurrentSkipListSet<Integer>> firstPtr = freeTree.ceilingEntry(size);
//        System.out.println("size = " + freeTree.size() + " firstPtr = " + firstPtr);
//          if (firstPtr == null) - запустить компактизацию
        if (firstPtr != null){
            int findSize = firstPtr.getKey();    //  реальный размер блока
            ConcurrentSkipListSet<Integer> findSet = firstPtr.getValue(); //  набор адресов
            int findPtr = findSet.pollFirst();  //  возвращает первый элемент и удаляет
            if (findSet.isEmpty())
                freeTree.remove(findSize);
            else {
                //  блок для исследования ситуации
                System.out.println("Запрос  = " + size + " остаток в findSet = " + findSet);
            }
            //  откусить от этого блока нужный кусок
//debug            memFill(pointBeginBlock, size, (byte) sFill++);  //  заполнить "кучу" данными
            allSize += size;    //  общий объем занятой памяти
            if (size == findSize) {
                //  создать запись в списке занятых областей
                busyTree.put(findPtr, size);
                //  если блок занять полностью - удалить из списка свободных
                //  уже удален (при чтении)
                delBlock++;
            }
            else {
                //  вариант с перезаписью блока
                //  создать запись в списке занятых областей
                busyTree.put(findPtr, size);
                //  остался кусок размером (findSize - size) с адресом (findPtr + size)
                freeTreeAdd(findSize - size, findPtr + size);
                insBlock++;
            }
//            if (iter++ % 10_000 == 0) {
//                stop = System.currentTimeMillis();
//                System.out.println(iter + "/" + (stop - start) + "/" + freeTree.size());
//            }
            return findPtr;
        }
        System.out.println("-- Ошибка размещения = " + size);
        System.out.println(freeTree);
        System.out.println(busyTree);

        //        memPrint(freeHash, (short) 0);
        //        memPrint(busyList, (short) 1);
        //  проанализировать общий объем памяти
        if (maxHeapSize - allSize < size)
            throw new OutOfMemoryException("Нет достаточного размера памяти", size);

        throw new OutOfMemoryException("Нет свободного участка памяти", size);
    }
    //  --------------------------------
    public static void freeTreeAdd(int keyTree, int valueTreeAsSet) {
        if (freeTree.containsKey(keyTree)){
            //  такой элемент существует, найти его
            ConcurrentSkipListSet<Integer> existSet = freeTree.get(keyTree);
            existSet.add(valueTreeAsSet);
        }
        else {
            //  такого элемента еще нет - создать его
            ConcurrentSkipListSet<Integer> existSet = new ConcurrentSkipListSet<>(Set.of(valueTreeAsSet));
            freeTree.put(keyTree, existSet);
        }
    }

    //  --------------------------------
    //  "удаляет", т.е. помечает как свободный блок памяти по "указателю"
    public static void free(int ptr){
        freBlock++;
        int busySize = busyTree.get(ptr);   //  размер освобождаемого блока
        if (busySize > 0) {
            allSize -= busySize;    //  общий объем занятой памяти
            busyTree.remove(ptr);   //  удалить блок из списка занятых
            freeTreeAdd(busySize, ptr);     //  добавить блок в список свободных
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
        try {
            System.out.println(future1.get());
            System.out.println(future2.get());
            System.out.println(future3.get());
            System.out.println(future4.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(extMemory.freeTree);
        System.out.println(extMemory.busyTree);
        es.execute(new Free(0));
        es.execute(new Free(12));
        es.shutdown();
        System.out.println(extMemory.freeTree);
        System.out.println(extMemory.busyTree);
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
                        System.out.println(e);
                        return;
                    }
                }
                break;
            case 1: //  возрастающая последовательность
                for (int i = 0; i < maxHeapSize; i++){
                    try {
                        malloc(i+1);
                    } catch (OutOfMemoryException e){
                        System.out.println(e);
                        return;
                    }
                }
                break;
            case 2: //  убывающая последовательность
                for (int i = (int) Math.sqrt(maxHeapSize); i > 0; i--){
                    try {
                        malloc(i+1);
                    } catch (OutOfMemoryException e){
                        System.out.println(e);
                        return;
                    }
                }
                break;
        }
    }
    //  ------------------------------------
    //  вспомагательная процедура освобождения
    public void aFree(int step) {
//        Allock oBlock;
        switch (step) {
            case 0: //  освобождение случайного блока
                int rand = mathRandomInterval(0, busyTree.size());
                System.out.println("Освобождение блока # " + rand + " из " + busyTree.size());
                int k = 0;
                for(Map.Entry<Integer, Integer> entry : busyTree.entrySet()) {
//                    System.out.println(entry.getKey() + " -> " + entry.getValue());
                    if (k++ == rand) {
                        free(entry.getKey());
//                        Future<?> future = es.submit(new Free(entry.getKey()));
                        return;
                    }
                }
                break;
        }
    }
}
//------------------------------------------------------------------------------
//  0
//  2
//  4
//  6
//  {120=[8]}
//  {0=2, 2=2, 4=2, 6=2}
//  {2=[6], 120=[8]}
//  {2=2, 4=2}