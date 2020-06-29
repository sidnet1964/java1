package ru.progwards.java2.lessons.gc;

import java.util.*;

public class Heap {
    static int iter = 0;
    static short sFill = 1; //  символ заполнения
    static short sClea = 1; //  символ заполнения
    long start;
    int delBlock = 0;    //  счетчик удалений
    int insBlock = 0;    //  счетчик добавлений
    int freBlock = 0;    //  счетчик освобождений
    int allSize;            //  занято памяти
    int maxHeapSize;        //  размер кучи
    byte[] bytes;           // - собственно, куча
    List<Allock> freeList;              //  список свободных блоков
    TreeMap<Integer, Integer> freeTree; //  карта свободных блоков (+)
//    HashMap<Integer, Integer> freeHash; //  карта занятых блоков (+)
//    HashMap<Integer, Integer> freeTree; //  карта занятых блоков (+)
    List<Allock> busyList;   //  список (или другая структура данных) занятых блоков
    TreeMap<Integer, Integer> busyTree; //  карта занятых блоков (+)

    public Heap(int maxHeapSize) {
        start = System.currentTimeMillis(); //  отсчет от момента создания объекта
        this.maxHeapSize = maxHeapSize;
        bytes = new byte[this.maxHeapSize];
        freeList = new ArrayList<>();
        freeList.add(new Allock(0, maxHeapSize));  //  свободная ... касса
        freeTree = new TreeMap<>(); //  (+)
//        freeHash = new HashMap<>();
//        freeTree = new HashMap<>();
        freeTree.put(0, maxHeapSize); //  (+)
        busyList = new ArrayList<>();
        busyTree = new TreeMap<>();
//        busyTree.put(0, 1); //  (+)
//debug        memFill(0, maxHeapSize, (byte) (-1*(sClea++))); //  первоначальное заполнение (debug)
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
    //  --------------------------------
    //  "размещает", т.е. помечает как занятый блок памяти с количеством ячеек массива heap равным size
    public int malloc(int size){
        //  поиск сбободного участка
        if (freeTree.size() == 0){
            System.out.println("freeList.size() == 1 -> " + freeList.size());
            throw new OutOfMemoryException("Нет свободных блоков для ", size);
        }
//        Optional<Allock> oMin = freeList.stream().sorted(new PtrComparator()).filter(x -> x.size >= size).min(Comparator.comparingInt(x -> x.size));
//  вариант без сортировки
//        Optional<Allock> oMin = freeList.stream().filter(x -> x.size >= size).min(Comparator.comparingInt(x -> x.size));
//        Optional<Integer> iMin = freeHash.stream().min(Comparator.comparingInt(x -> x.getValue));
//        Integer firstPtr = freeTree.navigableKeySet().stream().findFirst().get();
//        Integer firstPtr = freeTree.navigableKeySet().stream().filter(x -> x.g > size).findFirst().get();
//        System.out.print("size = " + size);
//        System.out.println("freeTree = " + freeTree);
//        long start = System.currentTimeMillis();
        Map.Entry<Integer, Integer> firstPtr = freeTree.entrySet().stream().filter(x -> x.getValue() >= size).sorted(new PtrComparator()).findFirst().get();
//        Map.Entry<Integer, Integer> firstPtr = freeTree.entrySet().stream().filter(x -> x.getValue() >= size).limit(1).findAny().get();

 //        if (oMin.isPresent()) {
        if (firstPtr != null){
//            int sizePtr = freeTree.get(firstPtr);   //  размер блока по указателю
            int sizePtr = firstPtr.getValue();  //  размер блока по указателю
//            Allock block = oMin.get();
//            System.out.println(", min = " + firstPtr + ", freeTree = " + freeTree);
//                System.out.println(block.size);
            //  откусить от этого блока нужный кусок
            //  проверить вариант использования конца блока!!!
            int pointBeginBlock = firstPtr.getKey();  //  запомнить указатель на начало блока
            //  заполнить "кучу" данными
//debug            memFill(pointBeginBlock, size, (byte) sFill++);
            allSize += size;    //  общий объем занятой памяти
            if (size == sizePtr) {
                //  создать запись в списке занятых областей
                busyTree.put(pointBeginBlock, size);
                //  если блок занять полностью - удалить из списка свободных
                freeTree.remove(pointBeginBlock);
                delBlock++;
            }
            else {
//                //  вариант с перезаписью блока
//                //  создать запись в списке занятых областей
//                busyTree.put(pointBeginBlock, size);
//                freeTree.remove(pointBeginBlock);
//                freeTree.put(pointBeginBlock + size, sizePtr - size);

                //  вариант с обновлением блока!!!
                //  создать запись в списке занятых областей
                busyTree.put(pointBeginBlock + sizePtr - size, size);
//                freeTree.remove(pointBeginBlock);
                freeTree.replace(pointBeginBlock, sizePtr - size);
                insBlock++;
            }
//            if (iter++ % 100_000 == 0) {
//                long stop = System.currentTimeMillis();
//                System.out.println(iter + "/" + (stop - start) + "/" + freeTree.size());
//            }
            return pointBeginBlock + sizePtr - size;
        }
//        }
        System.out.println("-- Ошибка размещения = " + size);
        memPrint(freeList, (short) 0);
        memPrint(busyList, (short) 1);
        //  проанализировать общий объем памяти
        if (maxHeapSize - allSize < size)
            throw new OutOfMemoryException("Нет достаточного размера памяти", size);

//        defrag();
//        System.out.println("-- После дефрагментации = " + size);
////        memPrint(inputList, (short) 0);
////        memPrint(outputList, (short) 1);
//        int rezume = malloc(size);
//        if (rezume > 0)
//            return rezume;

//        System.out.println(inputList);
//        System.out.println(outputList);
//        System.out.println(toString());
//        compact();
//        defrag();
//        System.out.println();
//        System.out.println("-- Компактизация = " + size);
//        System.out.println(inputList);
//        System.out.println(outputList);
//        malloc(size);
        throw new OutOfMemoryException("Нет свободного участка памяти", size);
    }
    //  --------------------------------
    //  "удаляет", т.е. помечает как свободный блок памяти по "указателю"
    public void free(int ptr){
        freBlock++;
        int busySize = busyTree.get(ptr);
        if (busySize > 0) {
            freeTree.put(ptr, busySize);
            //  удалить блок из списка
            busyTree.remove(ptr);
            allSize -= busySize;    //  общий объем занятой памяти
            return;
        }
        throw new InvalidPointerException("Неверный адрес участка памяти", ptr);
    }
    //  ------------------------------------------------------------------------
    //  осуществляет дефрагментацию кучи
    public void defrag(){
        System.out.println("++ Дефрагментация");
        sClea = 1; //  символ заполнения - сбросить
        if (freeList.size() <= 1)
            return; //  пустой список или состоит из одного элемента
        //  отсортировать freeList
        freeList.sort((a, b) -> Integer.compare(a.ptr, b.ptr));

        //        System.out.println(inputList);
        //        System.out.println(outputList);
        //        System.out.println(this.toString());

        ArrayDeque<Integer> input;  //  коллекция для смежных блоков
//        while (true) {  //  выход по внутреннему условию
        for (int i = 0; i < freeList.size()-1; i++) {    //   i++    проверить список из одного элемента
            input = new ArrayDeque<>();
            input.addLast(i);
//            System.out.println("Нач i = " + i + " input = " + input);
            //  чтобы объединить все смежые блоки необходимо
            int prevPtr = freeList.get(i).ptr;     //  начало первого блока
            int prevSize = freeList.get(i).size;   //  размер первого блока
            for (int j = i+1; j < freeList.size(); j++) {    //  проверить список из одного элемента
                i++;    //  работа с внешним счетчиком
                Allock block = freeList.get(j);
//                System.out.println("Вхо i = " + i + ", j = " + j + " Ptr = " + prevPtr + " Size = " + prevSize);
                if (prevPtr + prevSize == block.ptr) {
                    input.addLast(j);
//                    System.out.println("Общий размер = " + (prevSize + block.size));
                    prevSize += block.size; //  увеличить размер блока для проверки
                }
                else {
                    break;  //  выход из цикла по j
                }
//                System.out.println("Вых i = " + i + " , j = " + j + " input = " + input);
            }
            //  выход из цикла
            //  удалить лишние блоки из списка
//            i -= input.size();    //  отмотать счетчик
            while (input.size() > 1) {
                int indDel = input.removeLast();
                freeList.remove(indDel);
                i--;    //  отмотать счетчик
            }
            int indDel = input.removeLast();
            Allock block = freeList.get(indDel);
            block.size = prevSize;
            block.ptr = prevPtr;
            memFill(block.ptr, block.size, (byte) (-1 * (sClea++)));
            freeList.set(indDel, block);  //  записать все в предыдущий блок
            i--;    //  отмотать счетчик
//            System.out.println("Кон i = " + i + " input = " + input);
//            System.out.println();
//            System.out.println(inputList);
//            System.out.println(outputList);
//            System.out.println(this.toString());
        }
    }
    //  ------------------------------------------------------------------------
    //  компактизация кучи - перенос всех занятых блоков в начало хипа
    public void compact(){
        System.out.println("++ Компактизация");
        if (busyList.size() == 0)
            return; //  пустой список, нечего переносить
        //  отсортировать inputList
        freeList.sort((a, b) -> Integer.compare(a.ptr, b.ptr));
        //  отсортировать outputList
        busyList.sort((a, b) -> Integer.compare(a.ptr, b.ptr));

        for (int i = 0; i < busyList.size(); i++) {
            Allock oBlock = busyList.get(i);
            for (int j = 0; j < freeList.size(); j++) {
                Allock iBlock = freeList.get(j);
                if (oBlock.ptr < iBlock.ptr)
                    break;
                else {
                    System.out.println("Вхо i = " + i + ", j = " + j + "/" + oBlock + "/" + iBlock);
                    if (oBlock.size <= iBlock.size) {
                        //  начало переноса
                        short sTemp = (short) (-1 * sClea++);   //  временное значение
                        for (int k = 0; k < oBlock.size; k++) {
                            bytes[iBlock.ptr + k] = bytes[oBlock.ptr + k];
//                            bytes[oBlock.ptr + k] = (byte) -bytes[oBlock.ptr + k]; //  "очистка" памяти
                            bytes[oBlock.ptr + k] = (byte) sTemp; //  "очистка" памяти
                        }
                        if ((oBlock.size == iBlock.size)) {
                            //  блоки равны по размеру, обновить оба адреса
                            int iPtr = iBlock.ptr;
                            iBlock.ptr = oBlock.ptr;   //  заменить адрес блока
                            oBlock.ptr = iPtr;          //  заменить адрес блока
                        } else {
                            //  обновить элементы списков
                            int oPtr = oBlock.ptr;      //  запомнить одес занятого блока
//                            int oSize = iBlock.size - oBlock.size;  //  разница размеров
                            oBlock.ptr = iBlock.ptr;    //  заменить адрес блока
                            iBlock.ptr += oBlock.size;  //  заменить адрес блока
                            iBlock.size -= oBlock.size; //  уменьшить размер свободного блока
                            freeList.add(new Allock(oPtr, oBlock.size)); //  новый свободный блок
                        }
                        freeList.set(j, iBlock);
                        busyList.set(i, oBlock);
                        //  отсортировать inputList
                        freeList.sort((a, b) -> Integer.compare(a.ptr, b.ptr));
                        //  отсортировать outputList
//                    outputList.sort((a, b) -> Integer.compare(a.ptr, b.ptr));
                        break;  //  выход из цикла по j
                    }
                }
            }
        System.out.println("Кон i = " + i);
        System.out.println(freeList);
        System.out.println(busyList);
///        System.out.println(this.toString());
        }
    }

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

        @Override
        public String toString() {
            return "" + ptr + "~" + size ;
        }

        public int getPtr() {
            return ptr;
        }

        public int getSize() {
            return size;
        }
    }
    //  ------------------------------------
    public static void main(String[] args) {
        final int mSize = 128;
        Heap extMemory = new Heap(mSize);
        extMemory.aMallok(0);
//        extMemory.aMallok(1);
//        extMemory.aMallok(2);
//        System.out.println(extMemory.malloc(2));

        System.out.println("-- Размещение");
        System.out.println("Занято - " + extMemory.allSize + " , свободно - " + (mSize - extMemory.allSize));
        System.out.println("Занятых - " + extMemory.busyTree.size() + " , свободных - " + (extMemory.freeTree.size()));
        System.out.println(extMemory.freeTree);
        System.out.println(extMemory.busyTree);
        System.out.println(extMemory.toString());

        for (int i = 1; i <= extMemory.busyTree.size()/3; i++)
            extMemory.aFree(0);
//        extMemory.aFree(0);
//        extMemory.aFree(0);
//        extMemory.aFree(0);

        System.out.println("-- Освобождение");
        System.out.println("Занято - " + extMemory.allSize + " , свободно - " + (mSize - extMemory.allSize));
        System.out.println("Занятых - " + extMemory.busyTree.size() + " , свободных - " + (extMemory.freeTree.size()));
        System.out.println(extMemory.freeTree);
        System.out.println(extMemory.busyTree);
        System.out.println(extMemory.toString());

//        extMemory.defrag();
//
//        System.out.println();
        System.out.println("-- Дефрагментация");
//        System.out.println(extMemory.freeList);
//        System.out.println(extMemory.busyTree);
//        System.out.println(extMemory.toString());

        extMemory.aMallok(0);

        System.out.println("-- Размещение -- вторая волна");
        System.out.println("Занято - " + extMemory.allSize + " , свободно - " + (mSize - extMemory.allSize));
        System.out.println("Занятых - " + extMemory.busyTree.size() + " , свободных - " + (extMemory.freeList.size()));
        System.out.println(extMemory.freeList);
        System.out.println(extMemory.busyTree);
        System.out.println(extMemory.toString());

//        extMemory.compact();
//
//        System.out.println();
//        System.out.println("-- Компактизация");
//        System.out.println(extMemory.inputList);
//        System.out.println(extMemory.outputList);
//        System.out.println(extMemory.toString());
//
//        extMemory.defrag();
//
//        System.out.println();
//        System.out.println("-- Дефрагментация");
//        System.out.println(extMemory.inputList);
//        System.out.println(extMemory.outputList);
//        System.out.println(extMemory.toString());

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
                        int rand = mathRandomInterval(1, 5);
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
        Allock oBlock;
        switch (step) {
            case 0: //  освобождение случайного блока
                int rand = mathRandomInterval(0, busyTree.size());
                System.out.println("Освобождение блока # " + rand + " из " + busyTree.size());
                int k = 0;
                for(Map.Entry<Integer, Integer> entry : busyTree.entrySet()) {
//                    System.out.println(entry.getKey() + " -> " + entry.getValue());
                    if (k++ == rand) {
//                    oBlock = busyList.get(rand);
                        free(entry.getKey());
                        return;
                    }
                }
                break;
            case 1: //  освобождение первого блока
                oBlock = busyList.get(0);
                free(oBlock.ptr);
                break;
            case 2:  //  освобождение последнего блока
                oBlock = busyList.get(busyList.size()-1);
                free(oBlock.ptr);
                break;
        }
    }
    //  Map.Entry<Integer, Integer>
//    class PtrComparator implements Comparator<Allock>{
    class PtrComparator implements Comparator<Map.Entry<Integer, Integer>>{
////        public int compare(Allock a, Allock b){
//            public int compare(Allock a, Allock b){
////            return Integer.compare(a.getPtr(), (b.getPtr()));
//                return Integer.compare(a.getPtr(), (b.getPtr()));
//        }

        @Override
        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
            return Integer.compare(o1.getValue(), o2.getValue());
        }
    }
}
//[{ptr=16, size=32}, {ptr=48, size=64}, {ptr=240, size=784}]
//[{ptr=0, size=16}, {ptr=112, size=128}]
//--------------------------------------------------------------------------------------------------