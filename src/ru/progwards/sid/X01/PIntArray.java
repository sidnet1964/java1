package ru.progwards.sid.X01;

public class PIntArray {
    BArray<int[]> array;
    int blocksize;
    int size;
    int pagesize;

    PIntArray(int blocksize) {
        this.blocksize = blocksize;
        array = new BArray<>(blocksize, blocksize);
        array.add(new int[blocksize]);
        size = pagesize = 0;
    }

    public void add(int item) {
        int[] page = array.get(array.length()-1);
        if(pagesize == page.length) {
            page = new int[blocksize];
            array.add(page);
            pagesize = 0;
        }
        page[pagesize++]= item;
        size++;
    }

    public int get(int index) {
        int index1 = index / blocksize;
        int index2 = index % blocksize;
        return array.get(index1)[index2];
    }

    public int length() {
        return size;
    }
}
