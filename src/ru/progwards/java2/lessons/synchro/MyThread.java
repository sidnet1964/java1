package ru.progwards.java2.lessons.synchro;

class MyThread implements Runnable {

    private MainThraed main;
    private int kod;

    public MyThread(MainThraed main, int kod) {
        this.main = main;
        this.kod = kod;
    }

    @Override
    public void run() {
        while (!main.isStop()) {
            System.out.println("running " + kod);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Stop--- " + kod);
    }
}

