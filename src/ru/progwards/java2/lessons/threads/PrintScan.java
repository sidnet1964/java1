package ru.progwards.java2.lessons.threads;

public class PrintScan {
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
//            System.out.println(this.name + "/" + this.pages);
            print(this.name, this.pages);
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
            scan(this.name, this.pages);
        }
    }

    static synchronized void print(String name, int pages){
        for (int i = 0; i < pages; i++){
            System.out.println("print "+ name + " page " + (i+1));
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static synchronized void scan(String name, int pages){
        for (int i = 0; i < pages; i++){
            System.out.println("scan "+ name + " page " + (i+1));
            try {
                Thread.sleep(75);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final int count = 10;
        for (int l = 0; l < count; l++) {
            new Thread(new DocScan("COD_" + l, 5)).start();
            new Thread(new DocPrin("DOC_" + l, 5)).start();
        }
        Thread.sleep(500);
    }
}
