package ru.progwards.java2.lessons.gc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Heap {
    static short sFill = 1; //  символ заполнения
    int maxHeapSize;    //  размер кучи
    byte[] bytes;       // - собственно, куча
    List<Allock> inputList;    //  список (или другая структура данных) свободных блоков
    List<Allock> outputList;   //  список (или другая структура данных) занятых блоков

    public Heap(int maxHeapSize) {
        this.maxHeapSize = maxHeapSize;
        bytes = new byte[this.maxHeapSize];
        inputList   = new ArrayList<>();
        outputList  = new ArrayList<>();
        inputList.add(new Allock(0, maxHeapSize));  //  свободная ... касса
    }
    //  заполнить выделенный блок данными
    void memFill(int ptr, int size, byte data){
        for (int i = 0; i<size; i++)
            bytes[i+ptr] = data;
    }
    //  "размещает", т.е. помечает как занятый блок памяти с количеством ячеек массива heap равным size
    public int malloc(int size){
        //  поиск сбободного участка
        for (int i = 0; i < inputList.size(); i++) {
            Allock block = inputList.get(i);      /// проверить вариант заполнение с конца
            if (size < block.size) {
//                System.out.println(block.size);
                //  откусить от этого блока нужный кусок
                int point = block.ptr;  //  запомнить указатель
                //  заполнить "кучу" данными
                memFill(point, size, (byte) sFill++);
                //  создать запись в списке занятых областей
                outputList.add(new Allock(point, size));
                if (size == block.size)
                    inputList.remove(i);    //  весь блок использован, удалить его
                else {
                    //  сократить текущий блок на size
                    block.size -= size;
                    block.ptr += size;
                    inputList.set(i, block);
                }
                return point;
            }
        }
        throw new OutOfMemoryException("Нет свободного участка памяти", size);
    }
    //  "удаляет", т.е. помечает как свободный блок памяти по "указателю"
    public void free(int ptr){
        //  поиск занятого участка
        for (int i = 0; i < outputList.size(); i++) {
            Allock block = outputList.get(i);
            if (ptr == block.ptr) {
//                System.out.println(block.ptr);
                //  очистить данные "кучи"
                memFill(ptr, block.size, (byte) (-1*(sFill++)));    //  -bytes[ptr]
                //  создать запись в списке свободных областей
                inputList.add(new Allock(ptr, block.size));
                //  удалить блок из списка
                outputList.remove(i);
                return;
            }
        }
        throw new InvalidPointerException("Неверный адрес участка памяти", ptr);
    }
    //  осуществляет дефрагментацию кучи
    public void defrag(){
        if (inputList.size() <= 1)
            return; //  пустой список или состоит из одного элемента
        //  отсортировать inputList
        inputList.sort((a, b) -> Integer.compare(a.ptr, b.ptr));
        ArrayDeque<Integer> input;  //  коллекция для смежных блоков
//        while (true) {  //  выход по внутреннему условию
        for (int i = 0; i < inputList.size(); i++) {    //  проверить список из одного элемента
            input = new ArrayDeque<>();
            input.addLast(i);
            //  чтобы объединить все смежые блоки необходимо
            int prevPtr = inputList.get(i).ptr;     //  начало первого блока
            int prevSize = inputList.get(i).size;   //  размер первого блока
            for (int j = i+1; j < inputList.size(); j++) {    //  проверить список из одного элемента
                i++;    //  работа с внешним счетчиком
                Allock block = inputList.get(j);
//                System.out.println("j = " + j + " prevPtr = " + prevPtr + " prevSize = " + prevSize);
                if (prevPtr + prevSize == block.ptr) {
                    input.addLast(j);
//                    System.out.println("Общий размер = " + (prevSize + block.size));
                    prevSize += block.size; //  увеличить размер блока для проверки
                    //  увеличить предыдущий блок на текущий size
//                    block.size += prevSize;
//                    block.ptr = prevPtr;
//                    memFill(block.ptr, block.size, (byte) (-1 * (sFill++)));
//                    inputList.set(j - 1, block);  //  записать все в предыдущий блок
//                    inputList.remove(j);        //  удалить текущий блок
                }
                else {
                    break;
                }
                System.out.println("j = " + j + " input = " + input);
            }
            //  выход из цикла
            //  удалить лишние блоки из списка
            while (input.size() > 1) {
                int indDel = input.removeLast();
                inputList.remove(indDel);
            }
            int indDel = input.removeLast();
            Allock block = inputList.get(indDel);
            block.size = prevSize;
            block.ptr = prevPtr;
            memFill(block.ptr, block.size, (byte) (-1 * (sFill++)));
            inputList.set(indDel, block);  //  записать все в предыдущий блок
            System.out.println("i = " + i + " input = " + input);
        }
    }
    //  компактизация кучи - перенос всех занятых блоков в начало хипа
    public void compact(){
        
    }

    @Override
    public String toString() {
        return "Heap{" +
                "bytes=" + Arrays.toString(bytes) +
                '}';
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
            return "{" +
                    "ptr=" + ptr +
                    ", size=" + size +
                    '}';
        }
    }

    public static void main(String[] args) {
        final int mSize = 64;
        Heap extMemory = new Heap(mSize);
        System.out.println(extMemory.malloc(2));
        System.out.println(extMemory.malloc(2));
        System.out.println(extMemory.malloc(2));
        System.out.println(extMemory.malloc(2));
        System.out.println(extMemory.malloc(2));
        System.out.println(extMemory.malloc(2));
        System.out.println(extMemory.malloc(2));
        System.out.println(extMemory.malloc(2));
        System.out.println(extMemory.malloc(2));
//        System.out.println(extMemory.malloc(16));
        System.out.println(extMemory.inputList);
        System.out.println(extMemory.outputList);
        System.out.println(extMemory.toString());
        extMemory.free(2);
        extMemory.free(4);
        extMemory.free(6);

        extMemory.free(10);
        extMemory.free(12);
        extMemory.free(14);

        System.out.println(extMemory.inputList);
        System.out.println(extMemory.outputList);
        System.out.println(extMemory.toString());
        extMemory.defrag();
        System.out.println(extMemory.inputList);
        System.out.println(extMemory.outputList);
        System.out.println(extMemory.toString());
    }
}
//[{ptr=16, size=32}, {ptr=48, size=64}, {ptr=240, size=784}]
//[{ptr=0, size=16}, {ptr=112, size=128}]
//--------------------------------------------------------------------------------------------------