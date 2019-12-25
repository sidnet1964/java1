package ru.progwards.java1.lessons.compare_if_cycles;

public class CyclesGoldenFibo {
    public static void main(String[] args) {
        for (int i=1; i<=15; i++)
            System.out.println(fiboNumber(i));
        float maxJ = 100 / 1.62f;
        for (int i=1; i<=100; i++)
            for (int j=1; j<=maxJ; j++)
                if (isGoldenTriangle(i, i, j))
                    System.out.println("Основание = " + i + ", ребро = " + j);
//                System.out.println(isGoldenTriangle(500, 500, 309));
    }
    public static boolean containsDigit(int number, int digit){
//        возвращать true, если число number содержит цифру digit
        int razI;
        boolean rez = false;
        for (int i=0; ; i++)
        {
            razI = number / (int)Math.pow(10, i) % 10;  // извлекаем i+1-й разряд
            if (razI == digit)  // найдено
            {   rez = true;
                break;  }
            if ((int)Math.pow(10, i+1) > number)    // достигли предела
                break;
        }
        return rez;
    }
    public static int fiboNumber(int n){
        int f_n_2 = 0, f_n_1 = 1, f_n = 1;
        for (int i = 2; i <= n; i++)
        {f_n = f_n_2 + f_n_1;
        f_n_2 = f_n_1;
        f_n_1 = f_n;}
        return f_n;
    }
    public static boolean isGoldenTriangle(int a, int b, int c){
        final double OTN_MIN = 1.61703, OTN_MAX = 1.61903;
        double osn, reb;
        //  определить ребро и основание
        if (a==b){reb = (float)a; osn = (float)c;}
        else if (a==c){reb = (float)a; osn = (float)b;}
        else if (c==b){reb = (float)c; osn = (float)a;}
        else return false;
        if (reb/osn > OTN_MIN && reb/osn < OTN_MAX)
            return true;
        else
            return false;
    }
}
