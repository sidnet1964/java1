package ru.progwards.java1.SeaBattle.sidnet;
import ru.progwards.java1.SeaBattle.SeaBattle;
import ru.progwards.java1.SeaBattle.SeaBattle.FireResult;
import java.util.List;

public class SeaBattleAlg {
    final int FIRE_FREE = -2;       //  пустое поле вокруг цели
    final int FIRE_MISS = -1;       //  пустое поле после проверки
    final int FIRE_DEF = 0;         //  поле не проверялось
    final int FIRE_HIT = 1;         //  цель ранена
    final int FIRE_DEST = 2;        //  цель уничтожена
    final int FIRE_UNDEF = -9;      //  непонятный ответ
    final int DIRECT_R = 1;         //  движение вправо
    final int DIRECT_L = -1;        //  движение влево
    final int ORIENT_0 = 0;         //  ориентация не установлена
    final int ORIENT_G = 1;         //  горизонтальная ориентация
    final int ORIENT_V = 2;         //  вертикальная ориентация

    private SeaBattle seaBattle;
    private int sizeX;
    private int sizeY;
    private int[][] field_my;
    private int palubK;         //  количество палуб в текушем раунде
    private int orient;         //  ориентация палуб в текушем раунде 1-гориз, 2-верт
    private int nosX;          //  координаты верхней левой палубы
    private int nosY;          //  координаты верхней левой палубы
    private int[] ship;
    private static int counter;        // счетчик выстрелов
    private static int direct = 1;     //  направление движения
    private static int hits = 0;


    //  конструктор основного класса
    SeaBattleAlg(SeaBattle seaBattle, int sizeX, int sizeY) {
        this.seaBattle = seaBattle;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        field_my = new int[sizeX][sizeY];   //  мое рабочее поле
        int[] ship = {0, 4, 3, 2, 1};       //  ship[i] - количество оставшихся i-палубных кораблей
    }
    //  методы основного класса --------------------------------------
    //  сделать выстрел и обработать результат
    int fireXY(int x2, int y2){
//        if (x2 < 0 || x2 >= sizeX || y2 < 0 || y2 >= sizeY ){
//            System.out.println(seaBattle);
//            System.out.println(this);
//            System.out.println("Выход в точке - " + x2 + " : " + y2 + " , направление = " + direct);}
        SeaBattle.FireResult fireResult = seaBattle.fire(x2, y2);
        switch (fireResult) {
            case MISS:      field_my[x2][y2] = FIRE_MISS;   break;
            case HIT:       field_my[x2][y2] = FIRE_HIT;    hits++; break;
            case DESTROYED: field_my[x2][y2] = FIRE_DEST;   hits++; break;
            default:        field_my[x2][y2] = FIRE_UNDEF;
        }
        this.counter ++;
        return field_my[x2][y2];
    }
    //  -----------------------------------------------------------------
    boolean analiz(int f1, int x1, int y1) {
        int fire2, x2, y2;
        boolean graniza = false;
//        if (x1 == 9 && y1 == 9){
//            System.out.println(seaBattle);
//            System.out.println(this);
//            System.out.println("Выход в точке - " + x1 + " : " + y1 + " , ориентация = " + orient);}

        switch (f1) {

            case FIRE_HIT:     //  ранен
                this.palubK ++;     //  увеличиваем число обнаруженных палуб
                switch (this.orient) {
                    case ORIENT_0: //  первое попадание, сохраняем параметры носа корабля
                        this.nosX = x1;
                        this.nosY = y1;

                    case ORIENT_G:  //  начинаем поиск других палуб, вычисляем направление
                        if (direct == DIRECT_R) graniza = x1 < this.sizeX - 1;
                        if (direct == DIRECT_L) graniza = x1 > 0;
                        if (graniza) {          // если достигли границы
                            x2 = x1 + direct;
                            y2 = y1;
                            fire2 = fireXY(x2, y2);   // двигаемся в направлении direct
                            if (fire2 > FIRE_DEF) {
                                this.orient = 1;    //  горизонт
                                analiz(fire2, x2, y2);
                                break;
                            }
                        }
                    case ORIENT_V:  //  начинаем поиск других палуб, вычисляем направление
                        //  по вертикали
                        x2 = x1;
                        y2 = y1 + 1;
                        fire2 = fireXY(x2, y2);   // двигаемся вниз
                        if (fire2 > FIRE_DEF){
                            this.orient = 2;    //  вертикал
                            analiz(fire2, x2, y2);
                            break;
                        }
                }
                break;

            case FIRE_DEST:     //  потоплен окончательно
                if (this.orient == 0){  //  для однопалубных кораблей
                    //  первое попадание, сохраняем параметры
                    this.nosX = x1;
                    this.nosY = y1;
                }
                this.palubK ++; //  может быть несколько раненных целей
//                System.out.println("Потоп - " + this.nosX + " : " + this.nosY + " , палуб всего = "+ this.palubK + " # " + this.orient);
                obvodka();  //  необходимо обвести корабль по периметру
                this.palubK = 0;
                this.orient = 0;
//  вывод для отладки
//                System.out.println(seaBattle);
//                System.out.println(this);
                break;
            default:
                System.out.println("Что-то непонятное в - " + x1 + " : " + y1);
        }
        return false;
    }

    //  -----------------------------------------------------------------
    void obvodka(){     //  необходимо обвести корабль по периметру
        int i1 = 0; int i2 = 0; int j1 = 0; int j2 = 0;
        switch (this.orient){
            case 0: //  однопалубный
                i1 = Math.max(0, this.nosX - 1);    i2 = Math.min(this.nosX + this.palubK + 1, this.sizeX);
                j1 = Math.max(0, this.nosY - 1);    j2 = Math.min(this.nosY + this.palubK + 1, this.sizeY);
                break;
            case 1: //  горизонтальный
                i1 = Math.max(0, this.nosX - 1);    i2 = Math.min(this.nosX + 1 + this.palubK, this.sizeX);
                j1 = Math.max(0, this.nosY - 1);    j2 = Math.min(this.nosY + 1 + 1, this.sizeY);
                break;
            case 2: //  вертикальный
                i1 = Math.max(0, this.nosX - 1);    i2 = Math.min(this.nosX + 1 + 1, this.sizeX);
                j1 = Math.max(0, this.nosY - 1);    j2 = Math.min(this.nosY + 1 + this.palubK , this.sizeY);
                break;
            }
        for (int i = i1; i < i2; i++)
            for (int j = j1; j < j2; j++) {
                if (this.field_my[i][j] == FIRE_DEF) {
                    this.field_my[i][j] = FIRE_FREE;
                }
            }
    }
    //  -----------------------------------------------------------------
    //  обработка одного поля (вызов из цикла)
    void field_1(int x, int y){
        int fire1;
        fire1 = fireXY(x, y);
        //  если в поле пусто (fire1 = -1) - продолжаем
        if (fire1 > FIRE_DEF)
            analiz(fire1, x, y);
    }

    //  -----------------------------------------------------------------
    public void battleAlgorithm(SeaBattle seaBattle) {
        for (int y = 0; y < sizeY; y++) {       //  sizeX
            for (int x = 0; x < sizeX; x++) {   //  sizeY
                switch (direct){
                    case DIRECT_R:
                        if (field_my[x][y] == 0)
                            field_1(x, y);
                        break;
                    case DIRECT_L:
                        int x0 = sizeX - x - 1;     //  обратный индекс
                        if (field_my[x0][y] == 0)
                            field_1(x0, y);
                        break;
                    default:
                }
                if (hits >= 20)
                    return;
            }
//            direct = - direct;  //  можно отключить
        }
    }

//    public String toString() {
////        assert this.sizeX > 0 && this.sizeY > 0;
//
//        StringBuilder result = new StringBuilder(" ");
//
//        int y;
//        for(y = 0; y < this.sizeX; ++y) {
//            result.append("-").append(y % 10);
//        }
//
////        result.append("-   координата x");
//        result.append("\n");
//
//        for(y = 0; y < this.sizeY; ++y) {
//            result.append(y).append("|");
//
//            for(int x = 0; x < this.sizeX; ++x) {
//                result.append(this.field_my[x][y] == 0 ? " "
//                        :(this.field_my[x][y] == -2 ? "~"
//                        :(this.field_my[x][y] == -1 ? "-"
//                        :(this.field_my[x][y] == 1 ? "+"
//                        :(this.field_my[x][y] == 2 ? "X"
//                        : "?"))))).append("|");
//            }
//
//            result.append("\n");
//        }
//
//        return result.toString();
//    }

    // функция для отладки
    public static void main(String[] args) {
//        System.out.println("Sea battle");
//        SeaBattle seaBattle = new SeaBattle();
//        SeaBattleAlg my_game = new SeaBattleAlg(seaBattle, 10, 10);
//        my_game.battleAlgorithm(seaBattle);
//        System.out.println("Результат = " + seaBattle.getResult());
//        System.out.println("Счетчик = " + my_game.counter);
//        System.out.println("Поле seaBattle");
//        System.out.println(seaBattle);
//        System.out.println("Поле my_game");
//        System.out.println(my_game);
        TestFull();
    }
    static void TestFull() {
        System.out.println("Sea battle");
        double result = 0;
        for (int i=0; i<1000; i++) {
            SeaBattle seaBattle = new SeaBattle();
            SeaBattleAlg my_game = new SeaBattleAlg(seaBattle, 10, 10);
            my_game.battleAlgorithm(seaBattle);
            result += seaBattle.getResult();
            hits = 0;   //  обнулить
        }
        System.out.println("Результат = " + result / 1000);
    }
}
