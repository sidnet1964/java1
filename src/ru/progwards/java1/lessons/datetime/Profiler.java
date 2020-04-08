package ru.progwards.java1.lessons.datetime;

import java.time.Instant;
import java.util.*;

public class Profiler {
    public static List<StatisticInfo> info;
    public static Deque<String> stackString;            //  стек - для имени секции
    public static Map<String, Statistic> sectionStat;   //  TreeMap для замеров по секциям

    Profiler(){     //  конструктор +++++++++++++++++++++++++++++++++++
        info = new ArrayList<>();
        stackString = new ArrayDeque<>();
        sectionStat = new TreeMap<>();
    }
//  ---------------------------------------------------------------------
//  войти в профилировочную секцию, замерить время входа
    public static void enterSection(String name){
        if (name == null || name.equals(""))
            return;
        else {
            Statistic statistic;
            long instantIn = Instant.now().toEpochMilli();  //  засечь время - старт
            System.out.println("enter ++++++ -> " + name + " | " + instantIn);
            if (sectionStat.containsKey(name))
                statistic = sectionStat.get(name);
            else
//        if (statistic == null) {
                statistic = new Statistic(name);   //  новый набор значений времени выполнения
            sectionStat.put(name, statistic);   //  новая секция в TreeMap
//        }
            statistic.init(instantIn);
            stackString.push(name);     //  записать в стек имя секции - кандидат на выживание
        }
//        Thread.sleep(50);
    }
//  ---------------------------------------------------------------------
//   выйти из профилировочной секции. Замерить время выхода, вычислить
//   промежуток времени между входом и выходом в миллисекундах.
    public static void exitSection(String name){
        if (name == null || name.equals(""))
            return;
        else {
            long instantOut = Instant.now().toEpochMilli();   //  засечь время - финиш
            System.out.println("exit ------- -> " + name + " | " + instantOut);
            if (!stackString.isEmpty()) {
                Statistic stat1 = sectionStat.get(name);    //  взять объект из TreeMap
                String name2 = stackString.pop();  //  взять объект из стека, можно сравнить с name
                System.out.println("1 stat1 - " + stat1);
                stat1.update(instantOut);   //  расчитать время выполнения
                sectionStat.replace(name, stat1);   //  обновить значение ArrayDeque
                System.out.println("2 stat1 - " + stat1);
////////////////    попробовать здесь вычесть чистое время вышестоящей сессии
                if (!stackString.isEmpty()) {
                    String name0 = stackString.peek();          //  прочитать объект из стека (без удаления)
                    Statistic stat0 = sectionStat.get(name0);   //  взять объект из TreeMap
                    System.out.println("3 stat0 - " + stat0);
//                System.out.println("3 stat1 - " + stat1);
                    stat0.under(stat1);
                    System.out.println("4 stat0 - " + stat0);
                    sectionStat.replace(stat0.sectionName, stat0);   //  обновить значение ArrayDeque
//                System.out.println("5 stat0 - " + stat0);
                }
            }
        }
        System.out.println("sectionStat.size() = " + sectionStat.size());
    }
//  ---------------------------------------------------------------------
//  получить профилировочную статистику, отсортировать по наименованию секции
    public static List<StatisticInfo> getStatisticInfo(){
//        List<StatisticInfo> info = new ArrayList<>();
        return info;
    }

    public static void main(String[] args) {
        final int M_COUNT = 2;
        Profiler t1 = new Profiler();
        enterSection("");
//        for(int i = 0; i < M_COUNT; i++) {
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            t1.enterSection("2");
//            t1.enterSection("3");
//            t1.exitSection("3");
//            t1.exitSection("2");
//        }
        enterSection("4");
        exitSection("4");
        exitSection("1");
        System.out.println(t1.sectionStat);
//        System.out.println(t1.stackString);
//        System.out.println(t1.stackStat);
    }
}
