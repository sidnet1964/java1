package ru.progwards.java2.lessons.synchro;

//  25.07.2020 13:30
public class Philosopher extends Thread{
    String name;
    Fork right; // - вилка справа
    Fork left;  // - вилка слева
    long reflectTime;   // - время, которое философ размышляет в мс
    long eatTime;       // - время, которое философ ест в мс
    long reflectSum;    // - суммарное время, которое философ размышлял в мс
    long eatSum;        // - суммарное время, которое философ ел в мс
    int count;          // - номер подхода к столу

    public Philosopher(String name, Fork right, Fork left, long reflectTime, long eatTime) {
        this.name = name;
        this.right = right;
        this.left = left;
        this.reflectTime = reflectTime;
        this.eatTime = eatTime;
        count = 0;
    }

    @Override
    public void run() {
//        out.println(Simposion.allF(Simposion.forks) + (currentTimeMillis() - Simposion.fullTime) + " " + name + " начинает беседу!");
        while (!Simposion.stop) {
            try {   //  философ проголодается через reflectTime мс
                Thread.sleep(reflectTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            out.println(Simposion.allF(Simposion.forks) + (currentTimeMillis() - Simposion.fullTime) + " " + name + " голоден");
            right.pickUp(name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            left.pickUp(name);
            ++count;
//            out.println(Simposion.allF(Simposion.forks) + (currentTimeMillis() - Simposion.fullTime) + " " + name + " ест " + count);
            try {   //  философ насытится через eatTime мс
                Thread.sleep(eatTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            left.putDown(name);
            right.putDown(name);
        }
//        out.println(Simposion.allF(Simposion.forks) + (currentTimeMillis() - Simposion.fullTime) + " " + name + " завершает " + count);
    }

    @Override
    public String toString() {
        return name + "=" + count;
    }

    //  Выводит "размышляет " + name на консоль с периодичностью 0.5 сек
    void reflect(){

    }
    //  Выводит "ест "+ name на консоль с периодичностью 0.5 сек
    void eat(){

    }
}
