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
        if (x2 < 0 || x2 >= sizeX || y2 < 0 || y2 >= sizeY ){
            System.out.println(seaBattle);
            System.out.println("Выход в точке - " + x2 + " : " + y2 + " , направление = " + direct);}
        SeaBattle.FireResult fireResult = seaBattle.fire(x2, y2);
        switch (fireResult) {
            case MISS:      field_my[x2][y2] = FIRE_MISS;   break;
            case HIT:       field_my[x2][y2] = FIRE_HIT;    break;
            case DESTROYED: field_my[x2][y2] = FIRE_DEST;   break;
            default:        field_my[x2][y2] = FIRE_UNDEF;
        }
        this.counter ++;
        return field_my[x2][y2];
    }
    //  -----------------------------------------------------------------
    boolean analiz(int f1, int x1, int y1) {
        int fire2, x2, y2;
        boolean graniza = false;
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
                break;
            default:
                System.out.println("Что-то непонятное в - " + x1 + " : " + y1);
        }
        return false;
    }

    //  -----------------------------------------------------------------
    void obvodka(){     //  необходимо обвести корабль по периметру
        switch (this.orient){
            case 0: //  однопалубный
//                System.out.println(this.palubK + " =K1= " + this.nosX + ":" + this.nosY);
                for (int i = Math.max(0, this.nosX-1); i < Math.min(this.nosX+this.palubK+1, this.sizeX); i++)
                    for (int j = Math.max(0, this.nosY-1); j < Math.min(this.nosY+this.palubK+1, this.sizeY); j++) {
                        if (this.field_my[i][j] == FIRE_DEF) {
                            this.field_my[i][j] = FIRE_FREE;
//                            System.out.println(field_my[i][j] + " =K= " + i + ":" + j);
                        }
                    }
                break;
            case 1: //  горизонтальный
                for (int i = Math.max(0, this.nosX-1); i < Math.min(this.nosX+this.palubK+1, this.sizeX); i++)
                    for (int j = Math.max(0, this.nosY-1); j < Math.min(this.nosY+1+1, this.sizeY); j++) {
//                        System.out.println(field_my[i][j] + " = заполнение в - " + i + " : " + j);
                        if (this.field_my[i][j] == FIRE_DEF) {
                            this.field_my[i][j] = FIRE_FREE;
//                            System.out.println(field_my[i][j] + " =G= " + i + ":" + j);
                        }
                    }
                break;
            case 2: //  вертикальный
                for (int i = Math.max(0, this.nosX-1); i < Math.min(this.nosX+1+1, this.sizeX); i++)
                    for (int j = Math.max(0, this.nosY-1); j < Math.min(this.nosY+this.palubK+1, this.sizeY); j++) {
//                        System.out.println(field_my[i][j] + " = заполнение в - " + i + " : " + j);
                        if (this.field_my[i][j] == FIRE_DEF) {
                            this.field_my[i][j] = FIRE_FREE;
//                            System.out.println(field_my[i][j] + " =V= " + i + ":" + j);
                        }
                    }
                break;
            }
    }
    //  -----------------------------------------------------------------
    //  обработка одного поля (вызов из цикла)
    void field_1(int x, int y){
        int fire1;
        fire1 = fireXY(x, y);
        //  если в поле пусто (fire1 = -1) - продолжаем
        if (fire1 > FIRE_DEF){
//            System.out.println(field_my[x][y] + " = результат в - " + x + " : " + y);
            analiz(fire1, x, y);}
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
                        if (field_my[sizeX - x - 1][y] == 0)
                            field_1(sizeX - x - 1, y);
                        break;
                    default:
                }
            }
//            direct = - direct;  //  можно отключить
        }
    }

    public String toString() {
//        assert this.sizeX > 0 && this.sizeY > 0;

        StringBuilder result = new StringBuilder(" ");

        int y;
        for(y = 0; y < this.sizeX; ++y) {
            result.append("-").append(y % 10);
        }

//        result.append("-   координата x");
        result.append("\n");

        for(y = 0; y < this.sizeY; ++y) {
            result.append(y).append("|");

            for(int x = 0; x < this.sizeX; ++x) {
                result.append(this.field_my[x][y] == 0 ? " "
                        :(this.field_my[x][y] == -2 ? "~"
                        :(this.field_my[x][y] == -1 ? "-"
                        :(this.field_my[x][y] == 1 ? "+"
                        :(this.field_my[x][y] == 2 ? "X"
                        : "?"))))).append("|");
            }

            result.append("\n");
        }

        return result.toString();
    }

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
        for (int i=0; i<100; i++) {
            SeaBattle seaBattle = new SeaBattle();
            SeaBattleAlg my_game = new SeaBattleAlg(seaBattle, 10, 10);
            my_game.battleAlgorithm(seaBattle);
            result += seaBattle.getResult();
        }
        System.out.println("Результат = " + result / 100);
    }
}
