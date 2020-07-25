package ru.progwards.java2.lessons.synchro;

import java.util.LinkedList;
import java.util.List;

//  25.07.2020 13:30
public class Simposion {
    public static long fullTime;    //  шкала времени в мс
    public static boolean stop = false;
    static List<String> names = List.of("Aristotle", "Kant", "Spinoza", "Marx", "Russell");
//    static List<String> names = List.of("Aristotle", "Kant", "Spinoza", "Marx");
    public static long reflectTime;   //  время в мс, через которое философ проголодается
    public static long eatTime;       //  время в мс, через которое получив 2 вилки философ наестся и положит вилки на место
    static LinkedList<Fork> forks;
    LinkedList<Philosopher> list;

    public Simposion(long reflectTime, long eatTime) {
        this.reflectTime = reflectTime;
        this.eatTime = eatTime;
        list = new LinkedList<>();
        forks = new LinkedList<>() {
            {
                this.add(new Fork(1, true));
                this.add(new Fork(2, true));
                this.add(new Fork(3, true));
                this.add(new Fork(4, true));
                this.add(new Fork(5, true));
            }
        };
    }
    //  запускает философскую беседу
    void start(){
        fullTime = System.currentTimeMillis();
        int i = 0;
        for (String name : names){
            int i1 = i;     int i2 = (i + 1) % names.size();
            if (i2 < i1) {
                i1 = i2;    i2 = i;
            }
            list.add(new Philosopher(name, forks.get(i1), forks.get(i2)));
            list.get(i).start();
            i++;
        }
    }
    //  завершает философскую беседу
    void stop(){
        stop = true;
    }
    //  печатает результаты беседы в формате
    void print(){

    }
    //  состояние всех вилок
    public static String allF(LinkedList<Fork> forks) {
        StringBuilder builder = new StringBuilder();
        for (Fork value : forks) {
            builder.append(value.toString());
        }
        return builder.toString();  //   + "X ";
    }
    //  "Aristotle", "Kant", "Spinoza", "Marx", "Russell"
    public static void main(String[] args) {
        Simposion simposion1 = new Simposion(101, 101);
        simposion1.start();
//        System.out.println(list);
        try {   //  продолжительность беседы
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        simposion1.stop();
//        ChannelPool<AudioChannel> pool = new ChannelPool<>(list);
    }
}

