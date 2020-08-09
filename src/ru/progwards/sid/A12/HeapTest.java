package ru.progwards.sid.A12;

import java.util.ArrayDeque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

public class HeapTest {
    static final int THREADS_COUNT = 3;
    static final int THREAD_SLEEP = 1;
//    static final int maxSize = 1_932_735_283;
    static final int maxSize = 100_000;
    static final int maxSmall = 10;
    static final int maxMedium = 100;
    static final int maxBig = 1000;
    static final int maxHuge = 10000;
    static Integer allocated = 0;
    //  счетчики для статистики
    static AtomicInteger allocTime = new AtomicInteger(0);
    static AtomicInteger freeTime = new AtomicInteger(0);
    static AtomicInteger count = new AtomicInteger(0);

    //  ------------------------------------
    static class Block {
        public int ptr;
        public int size;

        public Block(int ptr, int size) {
            this.ptr = ptr;
            this.size = size;
        }
    }
    //  ------------------------------------
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
    //  ------------------------------------
    static synchronized void incAllocated(int size) {
        allocated += size;
    }
    //  ------------------------------------
    static void runTest(Heap heap) {
        ArrayDeque<Block> blocks = new ArrayDeque<>();
        int allocTime = 0;
        int freeTime = 0;

        // alloc and free 30% random
        while ((maxSize - allocated) > 50000) {
            long lstart, lstop;
            int size = getRandomSize();
            incAllocated(size);     //  allocated += size   ####################
            count.incrementAndGet();    //
            lstart = currentTimeMillis();
            //  ::::::::::::::::::::::::::::::::::::::::::::
            int ptr = heap.malloc(size);
            lstop = currentTimeMillis();
            allocTime += lstop-lstart;
            blocks.offer(new Block(ptr, size)); //  добавить элемент в конец очереди
            int n = Math.abs(ThreadLocalRandom.current().nextInt() % 25);
            if (n == 0) {
                //n = Math.abs(ThreadLocalRandom.current().nextInt()%blocks.size());
                for (int i=0; i<5; i++) {
                    Block block = blocks.poll();    //  взять элемент из начала очереди
                    if (block == null)
                        break;
                    lstart = currentTimeMillis();
                    //  ::::::::::::::::::::::::::::::::::::::::::::
                    heap.free(block.ptr);
                    lstop = currentTimeMillis();
                    freeTime += lstop - lstart;
                    incAllocated(-block.size);  //  allocated += size   ########
                }
                try {
                    Thread.sleep(THREAD_SLEEP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            n = Math.abs(ThreadLocalRandom.current().nextInt()%100_000);
            if (n==0)
                out.println(maxSize-allocated);
        }
        HeapTest.allocTime.addAndGet(allocTime);
        HeapTest.freeTime.addAndGet(freeTime);
    }

    //  ------------------------------------
    public static void main(String[] args) throws InterruptedException {
        long start = currentTimeMillis();
//        System.out.println(ForkJoinPool.getCommonPoolParallelism());
        Heap heap = new Heap(maxSize);
        Thread threads[] = new Thread[THREADS_COUNT];   //  массив потоков
        for (int i=0; i<THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    out.println("thread started");
                    runTest(heap);
                }
            });
            threads[i].start();
        }
        for (int i=0; i<THREADS_COUNT; i++) {
            threads[i].join();
        }
        long stop = currentTimeMillis();
        out.println("malloc time: "+allocTime+" free time: "+freeTime);
        out.println("total time: "+(stop-start)+" count: "+count.get());
    }
}
//  A12
//  static final int maxSize = 900_000_000;
//  malloc time: 7928 free time: 1805
//  total time: 52958 count: 1992566
//  malloc time: 8577 free time: 1604
//  total time: 50353 count: 1991082