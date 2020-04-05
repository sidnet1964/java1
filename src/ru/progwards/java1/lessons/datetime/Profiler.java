package ru.progwards.java1.lessons.datetime;

import java.time.Instant;
import java.util.*;

public class Profiler {
    public Deque<Long> stack;
    public List<StatisticInfo> info;
    public Map<String, Long> section;   //  дополнительно для оперативной работы

    Profiler(){     //  конструктор
        stack = new ArrayDeque<>();
        info = new ArrayList<>();
        section = new TreeMap<>();
    }
//  войти в профилировочную секцию, замерить время входа
    public void enterSection(String name) throws InterruptedException {
        System.out.println("enterSection ++++++ -> " + name);
        Long instantIn = Instant.now().toEpochMilli();
        stack.push(instantIn);
        Thread.sleep(500);
    }
//   выйти из профилировочной секции. Замерить время выхода, вычислить
//   промежуток времени между входом и выходом в миллисекундах.
    public void exitSection(String name){
        System.out.println("exitSection ------ -> " + name);
        Long instantOut = Instant.now().toEpochMilli();
        Long instantIn = stack.peek();
//        info.add(new StatisticInfo());
//        section.put(name, instantOut - instantIn);
        Long prev = section.get(name);  //  предыдущее значение времени выполнения
        System.out.println("section.size() = " + section.size());
        if (prev == null)
            section.put(name, instantOut - instantIn);
        else
            section.replace(name, prev + instantOut - instantIn);   //  обновить значение
    }
//  получить профилировочную статистику, отсортировать по наименованию секции
    public List<StatisticInfo> getStatisticInfo(){
//        List<StatisticInfo> info = new ArrayList<>();
        return info;
    }

    public static void main(String[] args) throws InterruptedException {
        final int M_COUNT = 10;
//        Deque<Long> stack = new ArrayDeque<>();    //  для вложенных секций

        Profiler t1 = new Profiler();
//        enterSection("1");
        t1.enterSection("1");
        for(int i = 0; i < M_COUNT; i++) {
            t1.enterSection("2");
            t1.enterSection("3");
            t1.exitSection("3");
            t1.exitSection("2");
        }
        t1.enterSection("4");
        t1.exitSection("4");
        t1.exitSection("1");
        System.out.println(t1.section);
    }
}
