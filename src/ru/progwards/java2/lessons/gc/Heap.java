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
    //  ------------------------------------------------------------------------
    //  осуществляет дефрагментацию кучи
    public void defrag(){
        if (inputList.size() <= 1)
            return; //  пустой список или состоит из одного элемента
        //  отсортировать inputList
        inputList.sort((a, b) -> Integer.compare(a.ptr, b.ptr));
//        System.out.println(inputList);
//        System.out.println(outputList);
//        System.out.println(this.toString());

        ArrayDeque<Integer> input;  //  коллекция для смежных блоков
//        while (true) {  //  выход по внутреннему условию
        for (int i = 0; i < inputList.size()-1; i++) {    //   i++    проверить список из одного элемента
            input = new ArrayDeque<>();
            input.addLast(i);
//            System.out.println("Нач i = " + i + " input = " + input);
            //  чтобы объединить все смежые блоки необходимо
            int prevPtr = inputList.get(i).ptr;     //  начало первого блока
            int prevSize = inputList.get(i).size;   //  размер первого блока
            for (int j = i+1; j < inputList.size(); j++) {    //  проверить список из одного элемента
                i++;    //  работа с внешним счетчиком
                Allock block = inputList.get(j);
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
                inputList.remove(indDel);
                i--;    //  отмотать счетчик
            }
            int indDel = input.removeLast();
            Allock block = inputList.get(indDel);
            block.size = prevSize;
            block.ptr = prevPtr;
            memFill(block.ptr, block.size, (byte) (-1 * (sFill++)));
            inputList.set(indDel, block);  //  записать все в предыдущий блок
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
        if (outputList.size() == 0)
            return; //  пустой список, нечего переносить
        //  отсортировать inputList
        inputList.sort((a, b) -> Integer.compare(a.ptr, b.ptr));
        //  отсортировать outputList
        outputList.sort((a, b) -> Integer.compare(a.ptr, b.ptr));
        for (int i = 0; i < outputList.size(); i++) {
            Allock oBlock = outputList.get(i);
            for (int j = 0; j < inputList.size(); j++) {
                Allock iBlock = inputList.get(j);
                if (oBlock.ptr < iBlock.ptr)
                    break;
                else {
                    System.out.println("Вхо i = " + i + ", j = " + j + "/" + oBlock + "/" + iBlock);
                    if (oBlock.size <= iBlock.size) {
                        //  начало переноса
                        for (int k = 0; k < oBlock.size; k++) {
                            bytes[iBlock.ptr + k] = bytes[oBlock.ptr + k];
                            bytes[oBlock.ptr + k] = (byte) -bytes[oBlock.ptr + k]; //  "очистка" памяти
                        }
                        if ((oBlock.size == iBlock.size)) {
                            //  блоки равны по размеру, обновить оба адреса
                            int iPtr = iBlock.ptr;
                            iBlock.ptr = oBlock.size;   //  заменить адрес блока
                            oBlock.ptr = iPtr;          //  заменить адрес блока
                        } else {
                            //  обновить элементы списков
                            int oPtr = oBlock.ptr;      //  запомнить одес занятого блока
                            int oSize = iBlock.size - oBlock.size;  //  разница размеров
                            oBlock.ptr = iBlock.ptr;    //  заменить адрес блока
                            iBlock.ptr += oBlock.size;  //  заменить адрес блока
                            iBlock.size -= oBlock.size; //  уменьшить размер свободного блока
                            inputList.add(new Allock(oPtr, oSize)); //  новый свободный блок на разность
                        }
                        inputList.set(j, iBlock);
                        outputList.set(i, oBlock);
                        //  отсортировать inputList
                        inputList.sort((a, b) -> Integer.compare(a.ptr, b.ptr));
                        //  отсортировать outputList
//                    outputList.sort((a, b) -> Integer.compare(a.ptr, b.ptr));

                    }
//                    if (iBlock.size == 0)
//                        //  удалить пустой блок
//                        inputList.remove(j);
//                    else
//                    {
//                        inputList.set(j,iBlock);
//                        outputList.set(i,oBlock);
//                    }
                    break;  //  выход из цикла по j
                }
            }
        System.out.println("Кон i = " + i);
        System.out.println(inputList);
        System.out.println(outputList);
        System.out.println(this.toString());
        }
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
            return "{" + ptr +
                    ", " + size +
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
//        extMemory.free(6);

        extMemory.free(10);
        extMemory.free(12);
        extMemory.free(14);

//        System.out.println(extMemory.inputList);
//        System.out.println(extMemory.outputList);
//        System.out.println(extMemory.toString());

        extMemory.defrag();

        System.out.println();
        System.out.println(extMemory.inputList);
        System.out.println(extMemory.outputList);
        System.out.println(extMemory.toString());

        extMemory.compact();

        System.out.println();
        System.out.println(extMemory.inputList);
        System.out.println(extMemory.outputList);
        System.out.println(extMemory.toString());

    }
}
//[{ptr=16, size=32}, {ptr=48, size=64}, {ptr=240, size=784}]
//[{ptr=0, size=16}, {ptr=112, size=128}]
//--------------------------------------------------------------------------------------------------