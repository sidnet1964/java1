package ru.progwards.java1.SeaBattle.sidnet;

import ru.progwards.java1.SeaBattle.SeaBattle;

public class SeaBattleAlgDemo {
    public static void main(String[] args) {

    }
//    public void battleAlgorithm(SeaBattle seaBattle) {
//        int x, y;
//        // пример алгоритма:
//        //  найти пустое пространство и создать квадрат 4*4
////        for (int y = 0; y < sizeY; y++) {       //  sizeX
////            for (int x = 0; x < sizeX; x++) {   //  sizeY
//        x = 0;  y = 0;
//        if (kvadrat44(x, y))
//        {System.out.println("Квадрат 4*4 пустой - " + x + " : " + y);
//            kvadrat44_PP(x, y);}    //  пробить квадрат по диагонали ++
//        x = 6;  y = 0;
//        if (kvadrat44(x, y))
//        {System.out.println("Квадрат 4*4 пустой - " + x + " : " + y);
//            kvadrat44_PP(x, y);}    //  пробить квадрат по диагонали ++
//        x = 0;  y = 6;
//        if (kvadrat44(x, y))
//        {System.out.println("Квадрат 4*4 пустой - " + x + " : " + y);
//            kvadrat44_PP(x, y);}    //  пробить квадрат по диагонали ++
//        x = 6;  y = 6;
//        if (kvadrat44(x, y))
//        {System.out.println("Квадрат 4*4 пустой - " + x + " : " + y);
//            kvadrat44_PP(x, y);}    //  пробить квадрат по диагонали ++
//
//        // стрельба по всем квадратам поля полным перебором
////        for (int y = 0; y < seaBattle.getSizeX(); y++) {
////            for (int x = 0; x < seaBattle.getSizeY(); x++) {
////                SeaBattle.FireResult fireResult = seaBattle.fire(x, y);
////                System.out.println(x + " - " + y + " - " + fireResult);
////            }
//////            System.out.println(seaBattle);
////            System.out.println(this);
////        }
//    }

//    //  проверить квадрат 4*4 на пустоту
//    boolean kvadrat44 (int x1, int y1){
//        final int KX = 4;
//        final int KY = 4;
//        int sum44 = 0;  // счетчик пустых (непроверенных) полей
//        for (int y = 0; y < KY; y++) {       //  sizeX
//            for (int x = 0; x < KX; x++) {   //  sizeY
//                if (field_my[x + x1][y + y1] != 0) sum44 ++;
//            }
//        }
//        return (sum44 == 0);
//    }

//    //  прострелять квадрат 4*4 по диагонали
//    //  для начала все квадраты будут поверяться одинаково
//    void kvadrat44_PP (int x1, int y1) {
//        final int KX = 4;
//        final int KY = 4;
//        int fire1;
//        for (int y = 0; y < KY; y++) {       //  sizeX
//            for (int x = 0; x < KX; x++) {   //  sizeY
//                if (x == y)   // проход по диагонали вниз - вправо
//                    if (field_my[x + x1][y + y1] == 0) {  //  проверяем неизвестные ячейки
//                        fire1 = fireXY(x + x1, y + y1);
//                        //  если в поле пусто (fire1 = -1) - продолжаем
//                        if (fire1 > 0)
//                            analiz(fire1, x + x1, y + y1);
//                    }
//            }
//        }
//    }

}
