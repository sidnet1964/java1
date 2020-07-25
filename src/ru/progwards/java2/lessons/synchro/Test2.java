package ru.progwards.java2.lessons.synchro;

public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        MainThraed m = new MainThraed();

        Thread t1 = new Thread(new MyThread(m, 1));
        Thread t2 = new Thread(new MyThread(m, 2));
        Thread t3 = new Thread(new MyThread(m, 3));

        t1.start();
        t2.start();
        t3.start();
        Thread.sleep(500);
        m.setStop();
    }
}
