package ru.progwards.sid.A11;

public class C1 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    if(Thread.interrupted()) {
                        System.out.println("thread interrupted");
                        return;
                    }
                    System.out.println("thread started");
                }
            }});
        thread.start();
        Thread.sleep(1);
        thread.interrupt();
    }
}
