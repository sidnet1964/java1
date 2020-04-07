package ru.progwards.java1.lessons.datetime;

import java.time.Instant;
import java.util.*;

public class Profiler {
//    private static StackCalc stackCalc;
    public static Deque<Long> stackLong;        //  старый стек - для времени
    public static Deque<String> stackString;    //  новый стек - для секции
//    public static Deque<Statistic> stackStat;    //  новый стек - для секции
    public static List<StatisticInfo> info;
    public static Map<String, Long> sectionLong;            //  дополнительно для оперативной работы
    public static Map<String, Statistic> sectionStat;       //  дополнительно для оперативной работы

    Profiler(){     //  конструктор
        stackLong = new ArrayDeque<>();
        stackString = new ArrayDeque<>();
//        stackStat = new ArrayDeque<>();
        info = new ArrayList<>();
        sectionLong = new TreeMap<>();
        sectionStat = new TreeMap<>();  //  решение через объект Statistic
    }
//  ---------------------------------------------------------------------
//  войти в профилировочную секцию, замерить время входа
    public static void enterSection(String name){
        long instantIn = Instant.now().toEpochMilli();  //  засечь время - старт
        System.out.println("enter ++++++ -> " + name + " | " + instantIn);
//        Statistic statistic = new Statistic(name, instantIn);   //  новый набор значений времени выполнения
        //  получается, что один объект добавляем в разные коллекции - TreeMap и ArrayDeque
        Statistic statistic = sectionStat.get(name);
        if (statistic == null) {
            statistic = new Statistic(name);   //  новый набор значений времени выполнения
            sectionStat.put(name, statistic);   //  новая секция в TreeMap
        }
        statistic.init(instantIn);
        stackLong.push(instantIn);
        stackString.push(name);     //  записать в стек имя секции - кандидат на выживание
//        stackStat.push(statistic);  //  записать в стек блок статистики

//        Thread.sleep(50);
    }
//  ---------------------------------------------------------------------
//   выйти из профилировочной секции. Замерить время выхода, вычислить
//   промежуток времени между входом и выходом в миллисекундах.
    public static void exitSection(String name){
        long instantOut = Instant.now().toEpochMilli();   //  засечь время - финиш
        System.out.println("exit ------- -> " + name + " | " + instantOut);
        if (!stackString.isEmpty()){
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

        //        Long instantIn = stackLong.peek();    //  прочитать
        Long instantIn = stackLong.pop();       //  удалить
        Long prev = sectionLong.get(name);  //  предыдущее значение времени выполнения
        System.out.println("sectionStat.size() = " + sectionStat.size());
//        System.out.println(stackString);
        if (prev == null)
            sectionLong.put(name, instantOut - instantIn);
        else
            sectionLong.replace(name, prev + instantOut - instantIn);   //  обновить значение
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
//        enterSection("1");
        enterSection("1");
        for(int i = 0; i < M_COUNT; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            t1.enterSection("2");
            t1.enterSection("3");
            t1.exitSection("3");
            t1.exitSection("2");
        }
        t1.enterSection("4");
        t1.exitSection("4");
        t1.exitSection("1");
        System.out.println(t1.sectionLong);
        System.out.println(t1.stackLong);
        System.out.println(t1.sectionStat);
//        System.out.println(t1.stackString);
//        System.out.println(t1.stackStat);
    }
}
