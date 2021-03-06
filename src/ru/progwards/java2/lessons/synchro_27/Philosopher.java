package ru.progwards.java2.lessons.synchro_27;

import static java.lang.System.*;

//  25.07.2020 13:30
public class Philosopher  extends Thread{
    String name;
    int place;  // - место за столом
    Fork right; // - вилка справа
    Fork left;  // - вилка слева
    long reflectTime;   // - время, которое философ размышляет в мс
    long eatTime;       // - время, которое философ ест в мс
    long reflectSum;    // - суммарное время, которое философ размышлял в мс
    long eatSum;        // - суммарное время, которое философ ел в мс
    int count;          // - номер подхода к столу

    public Philosopher(String name, int place, Fork right, Fork left) {
        this.name = name;
        if (place % 2 == 0) {
            this.right = right;
            this.left = left;
        }
        else {
            this.right = left;
            this.left = right;
        }
        count = 0;
    }

    @Override
    public void run() {
        out.println(Simposion.allF(Simposion.forks) + (currentTimeMillis() - Simposion.fullTime) + " " + name + " начинает беседу!");
        while (!Simposion.stop) {
            try {   //  философ проголодается через reflectTime мс
                Thread.sleep(Simposion.reflectTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            out.println(Simposion.allF(Simposion.forks) + (currentTimeMillis() - Simposion.fullTime) + " " + name + " голоден");
            right.pickUp(name);
            left.pickUp(name);
            out.println(Simposion.allF(Simposion.forks) + (currentTimeMillis() - Simposion.fullTime) + " " + name + " ест " + ++count);
            try {   //  философ насытится через eatTime мс
                Thread.sleep(Simposion.eatTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            left.putDown(name);
            right.putDown(name);
        }
        out.println(Simposion.allF(Simposion.forks) + (currentTimeMillis() - Simposion.fullTime) + " " + name + " завершает " + count);
    }

    @Override
    public String toString() {
        return "Philosopher{" +
                "name='" + name + '\'' +
                ", right=" + right +
                ", left=" + left +
                '}';
    }

    //  Выводит "размышляет " + name на консоль с периодичностью 0.5 сек
    void reflect(){

    }
    //  Выводит "ест "+ name на консоль с периодичностью 0.5 сек
    void eat(){

    }
}
