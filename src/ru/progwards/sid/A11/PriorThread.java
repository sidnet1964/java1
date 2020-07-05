package ru.progwards.sid.A11;

public class PriorThread extends Thread {
    public PriorThread(String name) {
        super(name);
    }
    public void run() {
        for (int i = 0; i < 71; i++) {
            System.out.println(getName() + " " + i + " getPriority() = " + getPriority() + " getThreadGroup()" + getThreadGroup());
            try {
                Thread.sleep(0); // попробовать sleep(0),sleep(10)
            } catch (InterruptedException e) {
                System.err.print(e);
            }
        }
    }
}