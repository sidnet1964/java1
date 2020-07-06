package ru.progwards.sid.A00;

import java.util.concurrent.locks.ReentrantLock;

public class PrintScan {
    static class DocPrin implements Runnable {
        ReentrantLock lock;
        String name;
        int pages;

        public DocPrin(ReentrantLock lock, String name, int pages) {
            this.lock = lock;
            this.name = name;
            this.pages = pages;
        }

        @Override
        public void run() {
            try {
                lock.lock();
                print(this.name, this.pages);
            } finally {
                // снять блокировку
//                System.out.println("Пoтoк " + name + " разблокирует счетчик");
                lock.unlock();
            }
        }
    }

    static class DocScan implements Runnable {
        ReentrantLock lock;
        String name;
        int pages;

        public DocScan(ReentrantLock lock, String name, int pages) {
            this.lock = lock;
            this.name = name;
            this.pages = pages;
        }

        @Override
        public void run() {
            try {
                lock.lock();
                scan(this.name, this.pages);
            } finally {
                // снять блокировку
//                System.out.println("Пoтoк " + name + " разблокирует счетчик");
                lock.unlock();
            }
        }
    }

    static void print(String name, int pages) {
        for (int i = 0; i < pages; i++) {
            System.out.println("print " + name + " page " + (i + 1));
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static void scan(String name, int pages) {
        for (int i = 0; i < pages; i++) {
            System.out.println("scan " + name + " page " + (i + 1));
            try {
                Thread.sleep(75);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final int count = 4;
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();
        for (int l = 0; l < count; l++) {
            new Thread(new DocScan(lock1, "COD_" + l, 5)).start();
            new Thread(new DocPrin(lock2, "DOC_" + l, 5)).start();
        }
        Thread.sleep(500);
    }
}

