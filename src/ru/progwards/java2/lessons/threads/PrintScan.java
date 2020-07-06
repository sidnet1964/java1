package ru.progwards.java2.lessons.threads;

public class PrintScan {
    static Integer count1 = 1;
    static Integer count2 = 2;
    static class DocPrin implements Runnable{
        String name;
        int pages;
        Object obj = new Object();
        DocPrin(String name, int pages) {
            this.name = name;
            this.pages = pages;
        }
        @Override
        public void run() {
            synchronized (count1) {
                print(this.name, this.pages);
            }
        }
    }
    static class DocScan implements Runnable {
        String name;
        int pages;
        Object obj = new Object();
        DocScan(String name, int pages) {
            this.name = name;
            this.pages = pages;
        }
        @Override
        public void run() {
            synchronized (count2) {
                scan(this.name, this.pages);
            }
        }
    }

    static void print(String name, int pages){
        for (int i = 0; i < pages; i++){
            System.out.println("print "+ name + " page " + (i+1));
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
        for (int l = 0; l < count; l++) {
            new Thread(new DocScan("COD_" + l, 5)).start();
            new Thread(new DocPrin("DOC_" + l, 5)).start();
        }
        Thread.sleep(500);
    }
}
