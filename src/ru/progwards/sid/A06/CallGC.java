package ru.progwards.sid.A06;

import java.util.ArrayList;
import java.util.Date;

public class CallGC {
    static void printMemInfo(String info) {
        System. out.println(String. format("%1$tI:%1$tM:%1$tS.%1$tN", new Date()));
        System. out.println(info);
        Runtime runtime = Runtime. getRuntime();
        System. out.println( "всего: " + runtime.totalMemory());
        System. out.println( "максимально: " + runtime.maxMemory());
        System. out.println( "свободно: " + runtime.freeMemory());
    }
    public static void main(String[] args) {
        printMemInfo("Памяти на старте:");
        ArrayList<Integer> list = new ArrayList<>();
        for (int k = 0; k < 3; k++) {
            for (int i = 0; i < 35_000_000 ; i++) list.add(i);
            System. out.println();
            printMemInfo("После создания объектов:");
            list.clear();
            System. gc();
            printMemInfo("После сборки мусора:");
        }
    }
}
//  04:51:47.960000000
//  Памяти на старте:
//  всего:          67_108_864
//  максимально: 1_073_741_824
//  свободно:       61_381_544
//
//  04:51:48.714000000
//  После создания объектов:
//  всего:          484_442_112
//  максимально:  1_073_741_824
//  свободно:       152_448_112
//
//  04:51:48.824000000
//  После сборки мусора:
//  всего:          206_569_472
//  максимально:  1_073_741_824
//  свободно:       146_115_424
