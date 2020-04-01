package ru.progwards.java1.lessons.datetime;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Profiler {
//    Profiler(){
//        stack = new ArrayDeque<>();
//    }
    public static void enterSection(String name){
        Instant instant = Instant.now();

    }
    public static void exitSection(String name){

    }
    public static List<StatisticInfo> getStatisticInfo(){
        List<StatisticInfo> info = new ArrayList<>();

        return info;
    }
    class StatisticInfo{
        public String sectionName;  //  имя секции
        public int fullTime;        //  полное время выполнения секции в миллисекундах.
        public int selfTime;        //  чистое время выполнения секции в миллисекундах.
        public int count;           //  количество вызовов. В случае, если вызовов более одного ...
    }

    public static void main(String[] args) {
        final int M_COUNT = 1_000;
        Deque<Long> stack = new ArrayDeque<>();    //  для вложенных секций

        Profiler t1 = new Profiler();
        enterSection("1");
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
    }
}
