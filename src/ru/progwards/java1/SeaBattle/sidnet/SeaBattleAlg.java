package ru.progwards.java1.SeaBattle.sidnet;
import ru.progwards.java1.SeaBattle.SeaBattle;
import ru.progwards.java1.SeaBattle.SeaBattle.FireResult;
import java.util.List;

public class SeaBattleAlg {
    private SeaBattle seaBattle;
    private int sizeX;
    private int sizeY;
    private int[][] field_my;
    private int palubK;         //  количество палуб в текушем раунде
    private int[] ship;

    //  конструктор основного класса
    SeaBattleAlg(SeaBattle seaBattle, int sizeX, int sizeY) {
        this.seaBattle = seaBattle;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        field_my = new int[sizeX][sizeY];   //  мое рабочее поле
        int[] ship = {0, 4, 3, 2, 1};       //  ship[i] - количество оставшихся i-палубных кораблей
    }
    //  методы основного класса --------------------------------------
    //  проверить квадрат 4*4 на пустоту
    boolean kvadrat44 (int x1, int y1){
        final int KX = 4;
        final int KY = 4;
        int sum44 = 0;  // счетчик пустых (непроверенных) полей
        for (int y = 0; y < KY; y++) {       //  sizeX
            for (int x = 0; x < KX; x++) {   //  sizeY
                if (field_my[x + x1][y + y1] != 0) sum44 ++;
            }
        }
        return (sum44 == 0);
    }
    //  сделать выстрел и обработать результат
    int fireXY(int x2, int y2){
        SeaBattle.FireResult fireResult = seaBattle.fire(x2, y2);
        switch (fireResult) {
            case MISS:
                field_my[x2][y2] = -1;
                break;
            case HIT:
                field_my[x2][y2] = 1;
                break;
            case DESTROYED:
                field_my[x2][y2] = 2;
                break;
            default:
                field_my[x2][y2] = -99;  // неопределенное значение
        }
        return field_my[x2][y2];
    }
    //  прострелять квадрат 4*4 по диагонали
    //  для начала все квадраты будут поверяться одинаково
    void kvadrat44_PP (int x1, int y1) {
        final int KX = 4;
        final int KY = 4;
        int fire1;
        for (int y = 0; y < KY; y++) {       //  sizeX
            for (int x = 0; x < KX; x++) {   //  sizeY
                if (x == y)   // проход по диагонали вниз - вправо
                    if (field_my[x + x1][y + y1] == 0) {  //  проверяем неизвестные ячейки
                        fire1 = fireXY(x + x1, y + y1);
                        //  если в поле пусто (fire1 = -1) - продолжаем
                        if (fire1 > 0)
                            analiz(fire1, x + x1, y + y1);
                    }
            }
        }
    }
    void analiz(int f1, int x1, int y1) {
        int fireX, fireY;
        switch (f1) {
            case 1:     //  ранен
                this.palubK ++; //  может быть несколько раненных целей
                System.out.println("Ранен - " + x1 + " : " + y1 + " , палуб пока = "+ this.palubK);
                //  начинаем поиск других палуб, вычисляем направление
                //  если найденная палуба первая и примыкает к стене, то продолжаем перпендикулярно
                if (this.palubK > 0)
                    if (x1 == 0)
                        fireX = fireXY(x1+1, y1);   // двигаемся вправо
                    else if (x1 == this.sizeX - 1)
                        fireX = fireXY(x1-1, y1);   // двигаемся влево
                    else if (y1 == 0)
                        fireY = fireXY(x1, y1+1);   // двигаемся вниз
                    else if (y1 == this.sizeY - 1)
                        fireY = fireXY(x1, y1-1);   // двигаемся влево
                break;
            case 2:     //  потоплен окончательно
                this.palubK ++; //  может быть несколько раненных целей
                System.out.println("Потоплен окончательно - " + x1 + " : " + y1 + " , палуб всего= "+ this.palubK);
                this.palubK = 0;
                break;
            default:
                System.out.println("Что-то непонятное в - " + x1 + " : " + y1);
        }
    }
    public void battleAlgorithm(SeaBattle seaBattle) {
        int x, y;
        // пример алгоритма:
        //  найти пустое пространство и создать квадрат 4*4
//        for (int y = 0; y < sizeY; y++) {       //  sizeX
//            for (int x = 0; x < sizeX; x++) {   //  sizeY
        x = 0;  y = 0;
        if (kvadrat44(x, y))
            {System.out.println("Квадрат 4*4 пустой - " + x + " : " + y);
            kvadrat44_PP(x, y);}    //  пробить квадрат по диагонали ++
        x = 6;  y = 0;
        if (kvadrat44(x, y))
            {System.out.println("Квадрат 4*4 пустой - " + x + " : " + y);
            kvadrat44_PP(x, y);}    //  пробить квадрат по диагонали ++
        x = 0;  y = 6;
        if (kvadrat44(x, y))
            {System.out.println("Квадрат 4*4 пустой - " + x + " : " + y);
            kvadrat44_PP(x, y);}    //  пробить квадрат по диагонали ++
        x = 6;  y = 6;
        if (kvadrat44(x, y))
            {System.out.println("Квадрат 4*4 пустой - " + x + " : " + y);
            kvadrat44_PP(x, y);}    //  пробить квадрат по диагонали ++

        // стрельба по всем квадратам поля полным перебором
//        for (int y = 0; y < seaBattle.getSizeX(); y++) {
//            for (int x = 0; x < seaBattle.getSizeY(); x++) {
//                SeaBattle.FireResult fireResult = seaBattle.fire(x, y);
//                System.out.println(x + " - " + y + " - " + fireResult);
//            }
////            System.out.println(seaBattle);
//            System.out.println(this);
//        }
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
                        :(this.field_my[x][y] == -1 ? "-"
                        :(this.field_my[x][y] == 1 ? "+"
                        :(this.field_my[x][y] == 2 ? "X"
                        : "?")))).append("|");
            }

            result.append("\n");
        }

        return result.toString();
    }

    // функция для отладки
    public static void main(String[] args) {
        System.out.println("Sea battle");
        SeaBattle seaBattle = new SeaBattle(true);
        SeaBattleAlg my_game = new SeaBattleAlg(seaBattle, 10, 10);
        my_game.battleAlgorithm(seaBattle);
        System.out.println("Результат = " + seaBattle.getResult());
        System.out.println("Поле seaBattle");
        System.out.println(seaBattle);
        System.out.println("Поле my_game");
        System.out.println(my_game);
    }
}
