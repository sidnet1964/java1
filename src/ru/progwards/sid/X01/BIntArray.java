package ru.progwards.sid.X01;

public class BIntArray {
    int[] array;
    int blocksize;
    int size;

    BIntArray(int initsize, int blocksize) {
        this.blocksize = blocksize;
        array = new int[initsize];
        size = 0;
    }

    public void add(int item) {
        if(size == array.length) {
            int[] newArray = new int[array.length+blocksize];
            copyData(array, newArray);
            array = newArray;
        }
        array[size++]= item;
    }

    void copyData(int[] src, int[] dst) {
        for(int i=0; i<src.length; i++)
            dst[i] = src[i];
    }

    public int get(int index) {
        return array[index];
    }

    public int length() {
        return size;
    }
}
