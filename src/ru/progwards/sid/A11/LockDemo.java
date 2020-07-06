package ru.progwards.sid.A11;

//  Простой пример блокировки
import java.util.concurrent.locks.*;
class LockDemo {
    public static void main(String args[]) {
        ReentrantLock lock = new ReentrantLock();
        new Thread(new LockThread(lock, "А")).start();
        new Thread(new LockThread(lock, "В")).start();
    }
}
//  Общий ресурс
    class Shared {
    static int count = 0;
}

//Поток исполнения, инкрементирующий значение счетчика
class LockThread implements Runnable {
    String name;
    ReentrantLock lock;

    LockThread(ReentrantLock lk, String n) {
        lock = lk;
        name = n;
    }
    @Override
    public void run() {
        System.out.println("Зaпycк потока - " + name);
        try {
            // сначала заблокировать счетчик
            System.out.println("Пoтoк " + name + " ожидает блокировки счетчика");
            lock.lock();
            System.out.println("Пoтoк " + name + " блокирует счетчик.");
            Shared.count++;
            System.out.println("Пoтoк " + name + " : " + Shared.count);
            // а теперь переключить контекст, если это возможно
            System.out.println("Пoтoк " + name + " ожидает");
            Thread.sleep(1000);
        } catch (InterruptedException exc) {
            System.out.println(exc);
        } finally {
            // снять блокировку
            System.out.println("Пoтoк " + name + " разблокирует счетчик");
            lock.unlock();
        }
    }
}
