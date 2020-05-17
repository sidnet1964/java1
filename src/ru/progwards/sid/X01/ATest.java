package ru.progwards.sid.X01;

public class ATest {
    static final int count1 = 100000;
    static final int count2 = 100000;

    public static void main(String[] args) {
        PIntArray a1 = new PIntArray(100);                 //  PIntArray add time =7
        BIntArray a2 = new BIntArray(100, 100);     //  BIntArray add time =81
//        DIntArray a3 = new DIntArray();

        long start = System.currentTimeMillis();
        for(int i=0; i<count1; i++)
            a1.add(i);

        long middle = System.currentTimeMillis();
        for(int i=0; i<count1; i++)
            a2.add(i);

//        long middle = System.currentTimeMillis();
//        for(int i=0; i<count1; i++)
//            a3.add(i);

        long stop = System.currentTimeMillis();

//        for(int i=0; i<count1; i++)
//            System.out.println(i+" "+a2.get(i)+" "+a1.get(i));
        System.out.println("PIntArray add time ="+(middle-start));
        System.out.println("BIntArray add time ="+(stop-middle));
//        System.out.println("DIntArray add time ="+(stop-middle));
    }

}
