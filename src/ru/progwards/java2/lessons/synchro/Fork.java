package ru.progwards.java2.lessons.synchro;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

//  25.07.2020 13:30
public class Fork {
    int number;     //  # вилки
    volatile boolean free;   //  вилка, которая имеет 2 состояния : может быть свободна или занята
    Lock lock;

    public Fork(int number, boolean free) {
        this.number = number;
        this.free = free;
        lock = new ReentrantLock();
//        Condition isFree = lock.newCondition();
    }
    //  взять вилку
    void pickUp(String philosopher) {
        long t0 = currentTimeMillis();
        out.println(Simposion.allF(Simposion.forks) + (currentTimeMillis() - Simposion.fullTime) + " " + philosopher + " хочет_# " + number + " " + this.free);
        lock.lock();
        this.free = false;
        out.println(Simposion.allF(Simposion.forks) + (currentTimeMillis() - Simposion.fullTime) + " " + philosopher + " берет_# " + number + " " + (currentTimeMillis() - t0));   //   + " " + this.free
    }
    //  положить вилку
    void putDown(String philosopher) {
        lock.unlock();
        this.free = true;
        out.println(Simposion.allF(Simposion.forks) + (currentTimeMillis() - Simposion.fullTime) + " " + philosopher + " кладет_# " + number);
    }

    @Override
    public String toString() {
//        return ((free) ? "+" : "-") + number;
        return ((free) ? "+" : "-") + " ";
    }
}
