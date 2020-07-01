package ru.progwards.java2.lessons.gc;

import java.util.ArrayDeque;
import java.util.concurrent.ThreadLocalRandom;

public class HeapTest {
    static final int maxSize = 900_000_000;
//    static final int maxSize = 1_048_576;
    static final int maxSmall = 10;
    static final int maxMedium = 100;
    static final int maxBig = 1_000;
    static final int maxHuge = 10_000;
    static int allocated = 0;

    static class Block {
        public int ptr;
        public int size;

        public Block(int ptr, int size) {
            this.ptr = ptr;
            this.size = size;
        }
    }

    static int getRandomSize() {
        int n = Math.abs(ThreadLocalRandom.current().nextInt()%10);
        int size = Math.abs(ThreadLocalRandom.current().nextInt());
        if (n < 6)
            size %= maxSmall;
        else if (n < 8)
            size %= maxMedium;
        else if (n < 9)
            size %= maxBig;
        else
            size %= maxHuge;
        return size > (maxSize-allocated)-1 ? (maxSize-allocated)/2+1 : size+1;
    }
//  ====================================
    public static void main(String[] args) throws InvalidPointerException, OutOfMemoryException {
        Heap heap = new Heap(maxSize);
        ArrayDeque<Block> blocks = new ArrayDeque<>();
        int count = 0;
        int allocTime = 0;
        int freeTime = 0;
        heap.start = System.currentTimeMillis(); //  отсчет от момента создания объекта

        long start = System.currentTimeMillis();
        // alloc and free 30% random
        while ((maxSize - allocated) > 50000) {
            long lstart, lstop;
            int size = getRandomSize();
            allocated += size;
            count++;
            lstart = System.currentTimeMillis();
//  malloc
            int ptr = heap.malloc(size);
            lstop = System.currentTimeMillis();
            allocTime += lstop-lstart;
            blocks.offer(new Block(ptr, size));
            int n = Math.abs(ThreadLocalRandom.current().nextInt()%25);
            if (n == 0) {
                //n = Math.abs(ThreadLocalRandom.current().nextInt()%blocks.size());
                for (int i=0; i<5; i++) {
                    Block block = blocks.poll();
                    if (block == null)
                        break;
                    lstart = System.currentTimeMillis();
//  free
                    heap.free(block.ptr);
                    lstop = System.currentTimeMillis();
                    freeTime += lstop - lstart;
                    allocated -= block.size;
                }
                //blocks.remove(n);
            }
            n = Math.abs(ThreadLocalRandom.current().nextInt()%100000);
//  временно отключить
//            if (n == 0)
//                System.out.println(maxSize-allocated);
        }
        heap.stop = System.currentTimeMillis();
        System.out.println(heap.iter + "/" + (heap.stop - start) + "/" + heap.freeTree.size());

        long stop = System.currentTimeMillis();
        System.out.println("malloc time: "+allocTime+" free time: "+freeTime);
        System.out.println("total time: "+(stop-start)+" count: "+count);
        System.out.println(heap.delBlock + "/" + heap.insBlock+"/"+heap.freBlock);
        System.out.println("busyTree.size() = " + heap.busyTree.size());
        System.out.println("freeTree.size() = " + heap.freeTree.size());
        System.out.println("maxHeapSize  = " + heap.maxHeapSize);
        System.out.println("heap.allSize = " + heap.allSize);
//        System.out.println("busyTree = " + heap.busyTree);
        System.out.println("freeTree = " + heap.freeTree);
    }
}
//  static final int maxSize = 932_735_283;
//  -- вариант с перебором ArrayList через индекс
//  malloc time: 7479 free time: 99879
//  total time: 107645 count: 2064813
//  -- вариант с потоком и двойной сортировкой
//  malloc time: 94854 free time: 99093
//  total time: 194356 count: 2071972
//  -- вариант с потоком без сортировки по ptr
//  malloc time: 10469 free time: 95474
//  total time: 106364 count: 2060549
//  -- дополнительная статистика по операциям
//  malloc time: 15381 free time: 92926
//  total time: 108605 count: 2076560
//  412_752 / 1_663_808 / 415_055
//  delBlock / insBlock / freBlock

//  без сортировки по ptr
//  malloc time: 111 free time: 5
//  total time: 147 count: 24579
//  4968/19611/5110
//  busyTree.size() = 19469
//  freeList.size() = 143
//  maxHeapSize  = 11048576
//  heap.allSize = 11006085

//  c сортировкой по ptr
//  malloc time: 203 free time: 2
//  total time: 233 count: 25715
//  5134/20581/5220
//  busyTree.size() = 20495
//  freeList.size() = 87    - существенно меньше
//maxHeapSize  = 11048576
//heap.allSize = 10999013
//  -------------------------------------------
//  HashMap с сортировкой
//malloc time: 36120 free time: 186
//total time: 36605 count: 1844140
//367344/1476796/369060
//busyTree.size() = 1475080
//freeList.size() = 1717
//maxHeapSize  = 832735283
//heap.allSize = 832691114
//  TreeMap с сортировкой
//malloc time: 45849 free time: 225
//total time: 46400 count: 1844328
//364492/1479836/366890
//busyTree.size() = 1477438
//freeList.size() = 2399
//maxHeapSize  = 832735283
//heap.allSize = 832685529

//  29.06.2020 20:45 HashMap
//malloc time: 61574 free time: 191
//total time: 62358 count: 1998961
//397377/1601584/399561
//busyTree.size() = 1599400
//freeList.size() = 2185
//maxHeapSize  = 900000000
//heap.allSize = 899951029

//  29.06.2020 20:50 TreeMap
//malloc time: 50561 free time: 563
//total time: 51625 count: 1996879
//397785/1599094/399405
//busyTree.size() = 1597474
//freeList.size() = 1621
//maxHeapSize  = 900000000
//heap.allSize = 899953895

