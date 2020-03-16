package ru.progwards.java1.lessons.queues;

import java.util.Comparator;
import java.util.TreeSet;

public class CompMetod {
    public static void main(String[] args) {
        Comparator<CompMetod.Metod> metСomp = new CompMetod.TimeComparator().thenComparing(new CompMetod.NameComparator());
        TreeSet<CompMetod.Metod> metСoll = new TreeSet<CompMetod.Metod>(metСomp);
        metСoll.add(new CompMetod.Metod("zcollSort", 1));
        metСoll.add(new CompMetod.Metod("minSort", 31));
        metСoll.add(new CompMetod.Metod("mySort", 31));
        for (CompMetod.Metod mets : metСoll) {
            System.out.printf("%s: %d\n", mets.getName(), mets.getTime());
        }
    }

    static class Metod {
        private String name;
        private long time;

        public Metod(String name, long time) {
            this.name = name;
            this.time = time;
        }
        String getName() {
            return name;
        }
        long getTime() {
            return time;
        }
    }

    static class NameComparator implements Comparator<CompMetod.Metod> {
        public int compare(CompMetod.Metod met1, CompMetod.Metod met2) {
            return met1.getName().compareTo(met2.getName());
        }
    }

    static class TimeComparator implements Comparator<CompMetod.Metod> {
        public int compare(Metod met1, Metod met2) {
//            return (met1.getTime().compareTo(met2.getTime());
//  вариант с compareTo не работает             
            if (met1.getTime() > met2.getTime()) {
                return 1;
            } else if (met1.getTime() < met2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}

//  List<Worker> workers = new ArrayList<>();
//  workers.sort(Comparator.comparing(Worker::getName).thenComparing(Worker::getAge));
//  public static Comparator<Worker> SORT_BY_NAME_AGE = new Comparator<Worker>() {
//        @Override
//        public int compare(Worker w1, Worker w2) {
//            int i = w1.getName().compareTo(w2.getName());
//            if (i == 0) {
//                i = w1.getAge() - w2.getAge();
//            }
//            return i;
//        }
//
//
//    };
