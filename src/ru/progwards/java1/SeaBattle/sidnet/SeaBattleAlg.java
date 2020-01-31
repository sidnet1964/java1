package ru.progwards.java1.SeaBattle.sidnet;
import ru.progwards.java1.SeaBattle.SeaBattle;
import ru.progwards.java1.SeaBattle.SeaBattle.FireResult;
import java.util.List;

public class SeaBattleAlg {
    final int FIRE_FREE = -2;       //  пустое поле вокруг цели
    final int FIRE_MISS = -1;       //  пустое поле после проверки
    public final int FIRE_DEF = 0;         //  поле не проверялось
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
    public int[][] field_my;
    private int palubK;         //  количество палуб в текушем раунде
    private int orient;         //  ориентация палуб в текушем раунде 1-гориз, 2-верт
    private int nosX;          //  координаты верхней левой палубы
    private int nosY;          //  координаты верхней левой палубы
    private int[] ship;
    int counter;        // счетчик выстрелов
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
    public int fireXY(int x2, int y2){
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
                        graniza = y1 < this.sizeY - 1;
                        if (graniza) {          // если достигли границы по вертикали
                            x2 = x1;
                            y2 = y1 + 1;
                            fire2 = fireXY(x2, y2);   // двигаемся вниз
                            if (fire2 > FIRE_DEF){
                                this.orient = 2;    //  вертикал
                                analiz(fire2, x2, y2);
                                break;
                            }
                        }
                }
                break;

            case FIRE_DEST:     //  потоплен окончательно
                if (this.orient == 0){  //  для однопалубных кораблей
                    //  первое попадание, сохраняем параметры
                    this.nosX = x1;
                    this.nosY = y1;
                }
                this.palubK ++;
                obvodka(this.nosX, this.nosY, this.palubK, this.orient);  //  необходимо обвести корабль по периметру
                this.palubK = 0;
                this.orient = 0;
                break;
            default:
                System.out.println("Что-то непонятное в - " + x1 + " : " + y1);
        }
        return false;
    }

    //  -----------------------------------------------------------------
    void obvodka(int nosX1, int nosY1, int pKor, int oKor){     //  необходимо обвести корабль по периметру
        int i1 = 0; int i2 = 0; int j1 = 0; int j2 = 0;
        switch (oKor){
            case 0: //  однопалубный
                i1 = Math.max(0, nosX1 - 1);    i2 = Math.min(nosX1 + pKor + 1, this.sizeX);
                j1 = Math.max(0, nosY1 - 1);    j2 = Math.min(nosY1 + pKor + 1, this.sizeY);
                break;
            case 1: //  горизонтальный
                i1 = Math.max(0, nosX1 - 1);    i2 = Math.min(nosX1 + 1 + pKor, this.sizeX);
                j1 = Math.max(0, nosY1 - 1);    j2 = Math.min(nosY1 + 1 + 1, this.sizeY);
                break;
            case 2: //  вертикальный
                i1 = Math.max(0, nosX1 - 1);    i2 = Math.min(nosX1 + 1 + 1, this.sizeX);
                j1 = Math.max(0, nosY1 - 1);    j2 = Math.min(nosY1 + 1 + pKor , this.sizeY);
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

    int check_field(int x, int y){
        //  проверить поле, для пустого - сделать выстрел
//        System.out.println("Что-то непонятное в - " + x + " : " + y);
        if (field_my[x][y] == FIRE_DEF)
            return fireXY(x, y);
        else
            return field_my[x][y];
    }

    //  -----------------------------------------------------------------
    public void battleAlgorithm(SeaBattle seaBattle) {

//  ==========================================================
        int check, palubKor;
        //  пройтись по диагонали сверху направо
        for (int y = 0; y < sizeY; y++) {       //  sizeX
            for (int x = 0; x < sizeX; x++) {   //  sizeY
//                if (x == y || x == y - 2 || x == y + 2 || x == y - 4 || x == y + 4 || x == y - 6 || x == y + 6 || x == y - 8 || x == y + 8)   //   || x == y - 2 || x == y + 2
                if (x == y || x == y - 2 || x == y + 2 || x == y - 4 || x == y + 4 || x == y - 6 || x == y + 6)     //   || x == y - 8 || x == y + 8
                    if (field_my[x][y] == 0){
                        check = check_field(x, y);
                        switch (check) {
                            case FIRE_DEST:
                                obvodka(x, y, 1, 0);   //  выполнить обводку
                                break;
                            case FIRE_HIT:
                                Ship clipper = new Ship(x, y);
                                palubKor = clipper.Build(x, y);
                                clipper.Dost(x, y);
//                                System.out.println(palubKor + " палуб в - " + x + " : " + y);
                                break;
                            default:
                        }
                    }
            }
        }
//  ==========================================================
        //  пройтись по диагонали сверху направо
        for (int y = 0; y < sizeY; y++) {       //  sizeX
            for (int x = 0; x < sizeX; x++) {   //  sizeY
                if (x == sizeY - y - 1 || x == sizeY - y - 3 || x == sizeY - y + 1)
                    if (field_my[x][y] == 0){
                        check = check_field(x, y);
                        switch (check) {
                            case FIRE_DEST:
                                obvodka(x, y, 1, 0);   //  выполнить обводку
                                break;
                            case FIRE_HIT:
                                Ship clipper = new Ship(x, y);
                                palubKor = clipper.Build(x, y);
                                clipper.Dost(x, y);
//                                System.out.println(palubKor + " палуб в - " + x + " : " + y);
                                break;
                            default:
                        }
                    }
            }
        }

//  ==========================================================

//  вывод для отладки
//        System.out.println(seaBattle);
//        System.out.println(this);
//  ==========================================================


//  ==========================================================
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
                if (hits >= 20){
                    hits = 0;
                    return;
                }
            }
//            direct = - direct;  //  можно отключить
        }
//  ==========================================================
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

//  -----------------------------------------------------------------
class Ship {
    //  класс - корабль 1-многопалубный создается при первом попадаии
    //  содержит методы для построения полных координат и обводки (?)
    int aX, aY;  //  якорь, первое значение
    int nX, nY;  //  нос, крайнее левое/верхнее значение (для обводки)
    int orient;  //  ориентация палуб в объекте = 1-гориз, 2-верт
    private int palubK;         //  количество палуб в текушем раунде

    Ship(int aX, int aY) {
        this.aX = aX;
        this.aY = aY;
        this.nX = aX;       //  фиксируем для начала строительства
        this.nY = aY;       //
        this.palubK = 1;    //
    }

    void Dost(int x1, int y1) {   //  достройка корабля
        obvodka(nX, nY, palubK, orient);
    }

    int Build(int x1, int y1) {   //  достройка корабля
        int fire2, x2, y2, check;
        boolean graniza = false;

//  ................................
        orient = 1;
        //  сначала двигаемся вправо
        for (int i = 1; ; i++) {
            graniza = x1 - i >= 0;
            if (graniza) {          // если достигли границы
                x2 = x1 - i;        //  точка слева
                y2 = y1;
                check = check_field(x2, y2);
                if (check == FIRE_DEST) {
                    nX = x2;        // обновление координат
                    palubK++;
                    return palubK;
                } else if (check == FIRE_HIT) {
                    palubK++;
                    nX = x2;        // обновление координат
                    orient = 1;     //  горизонт
                } else
                    break;
            } else break;
        }
//  ................................
        for (int i = 1; ; i++) {
            graniza = x1 + i <= sizeX - 1;
            if (graniza) {          // если достигли границы
                x2 = x1 + i;        //  точка справа
                y2 = y1;
                check = check_field(x2, y2);
                if (check == FIRE_DEST) {
                    palubK++;
                    return palubK;
                } else if (check == FIRE_HIT) {
                    palubK++;
                    orient = 1;    //  горизонт
                } else
                    break;
            } else break;
        }
//  ................................
        orient = 2;
        //  сначала двигаемся вверх
        for (int i = 1; ; i++) {
            graniza = y1 - i >= 0;
            if (graniza) {          // если достигли границы
                x2 = x1;            //  точка сверху
                y2 = y1 - i;
                check = check_field(x2, y2);
                if (check == FIRE_DEST) {
                    nY = y2;        // обновление координат
                    palubK++;
                    return palubK;
                } else if (check == FIRE_HIT) {
                    nY = y2;        // обновление координат
                    palubK++;
                    orient = 2;
                } else
                    break;
            } else break;
        }
//  ................................
        for (int i = 1; ; i++) {
            graniza = y1 + i <= sizeY - 1;
            if (graniza) {          // если достигли границы
                x2 = x1;            //  точка снизу
                y2 = y1 + i;
                check = check_field(x2, y2);
                if (check == FIRE_DEST) {
                    palubK++;
                    return palubK;
                } else if (check == FIRE_HIT) {
                    palubK++;
                    orient = 2;
                } else
                    break;
            } else break;
        }
//  ................................
        return 0;
    }
}
//  -----------------------------------------------------------------

    // функция для отладки
    public static void main(String[] args) {
//        System.out.println("Sea battle");
//        SeaBattle seaBattle = new SeaBattle(true);
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
        double result = 0, fullCounter = 0;
        for (int i=0; i<1000; i++) {
//            System.out.println("Счетчик цикла = " + i);
            SeaBattle seaBattle = new SeaBattle();
            SeaBattleAlg my_game = new SeaBattleAlg(seaBattle, 10, 10);
            my_game.battleAlgorithm(seaBattle);
            result += seaBattle.getResult();
            fullCounter += my_game.counter;
        }
        System.out.println("Результат = " + result / 1000);
//        System.out.println("Счетчик = " +  fullCounter);
    }
}
