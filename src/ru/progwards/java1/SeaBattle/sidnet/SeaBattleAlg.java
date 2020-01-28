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
    private int orient;         //  ориентация палуб в текушем раунде 1-гориз, 2-верт
    private int nosX;          //  координаты верхней левой палубы
    private int nosY;          //  координаты верхней левой палубы
    private int[] ship;
    private int counter;        // счетчик выстрелов

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
        SeaBattle.FireResult fireResult = seaBattle.fire(x2, y2);
        this.counter ++;
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

    void analiz(int f1, int x1, int y1) {
        int fire2, x2, y2;
        switch (f1) {
            case 1:     //  ранен
                if (this.orient == 0){
                    //  первое попадание, сохраняем параметры
                    this.nosX = x1;
                    this.nosY = y1;
                }
                this.palubK ++; //  может быть несколько раненных целей
                System.out.println("Ранен - " + x1 + " : " + y1 + " , палуб пока = "+ this.palubK);
                //  начинаем поиск других палуб, вычисляем направление

                if (this.orient != 2 && x1 < this.sizeX - 1) {  //  чтобы не вылезти за гранцу поля
                    //  если ориентация не установлена как вертикальная, то по горизонтали
                    x2 = x1 + 1;
                    y2 = y1;
                    fire2 = fireXY(x2, y2);   // двигаемся вправо
                    if (fire2 > 0) {
                        this.orient = 1;    //  горизонт
                        analiz(fire2, x2, y2);
                        break;
                    }
                }
                //  по вертикали
                x2 = x1;
                y2 = y1 + 1;
                fire2 = fireXY(x2, y2);   // двигаемся вниз
                if (fire2 > 0){
                    this.orient = 2;    //  вертикал
                    analiz(fire2, x2, y2);
                    break;}

                break;
            case 2:     //  потоплен окончательно
                if (this.orient == 0){  //  для однопалубных кораблей
                    //  первое попадание, сохраняем параметры
                    this.nosX = x1;
                    this.nosY = y1;
                }
                this.palubK ++; //  может быть несколько раненных целей
                System.out.println("Потоп - " + this.nosX + " : " + this.nosY + " , палуб всего = "+ this.palubK + " # " + this.orient);
                obvodka();  //  необходимо обвести корабль по периметру
                this.palubK = 0;
                this.orient = 0;
                break;
            default:
                System.out.println("Что-то непонятное в - " + x1 + " : " + y1);
        }
    }

    void obvodka(){     //  необходимо обвести корабль по периметру
        switch (this.orient){
            case 0: //  однопалубный
                for (int i = Math.max(0, this.nosX-1); i < Math.min(this.nosX+this.palubK+1, this.sizeX); i++)
                    for (int j = Math.max(0, this.nosY-1); j < Math.min(this.nosY+this.palubK+1, this.sizeY); j++){
                        if (this.field_my[i][j] == 0)
                            this.field_my[i][j] = -2;    //  но лучше -2
                    }
                break;
            case 1: //  горизонтальный
                for (int i = Math.max(0, this.nosX-1); i < Math.min(this.nosX+this.palubK+1, this.sizeX); i++)
                    for (int j = Math.max(0, this.nosY-1); j < Math.min(this.nosY+1+1, this.sizeY); j++){
//                        System.out.println(field_my[i][j] + " = заполнение в - " + i + " : " + j);
//                        System.out.println(this.field_my[i][j] + " = наполнение в - " + i + " : " + j);
                        if (this.field_my[i][j] == 0)
                            this.field_my[i][j] = -2;    //  но лучше -2
                    }
                break;
            case 2: //  вертикальный
                for (int i = Math.max(0, this.nosX-1); i < Math.min(this.nosX+1+1, this.sizeX); i++)
                    for (int j = Math.max(0, this.nosY-1); j < Math.min(this.nosY+this.palubK+1, this.sizeY); j++){
//                        System.out.println(field_my[i][j] + " = заполнение в - " + i + " : " + j);
//                        System.out.println(this.field_my[i][j] + " = наполнение в - " + i + " : " + j);
                        if (this.field_my[i][j] == 0)
                            this.field_my[i][j] = -2;    //  но лучше -2
                    }
                break;
        }
    }
    //  обработка одного поля (вызов из цикла)
    void field_1(int x, int y){
        int fire1;
        fire1 = fireXY(x, y);
        //  если в поле пусто (fire1 = -1) - продолжаем
        if (fire1 > 0){
//            System.out.println(field_my[x][y] + " = результат в - " + x + " : " + y);
            analiz(fire1, x, y);}
    }

    public void battleAlgorithm(SeaBattle seaBattle) {
        for (int y = 0; y < sizeY; y++) {       //  sizeX
            for (int x = 0; x < sizeX; x++) {   //  sizeY
                if (field_my[x][y] == 0)
                    field_1(x, y);
                }
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
        System.out.println("Sea battle");
        SeaBattle seaBattle = new SeaBattle(true);
        SeaBattleAlg my_game = new SeaBattleAlg(seaBattle, 10, 10);
        my_game.battleAlgorithm(seaBattle);
        System.out.println("Результат = " + seaBattle.getResult());
        System.out.println("Счетчик = " + my_game.counter);
        System.out.println("Поле seaBattle");
        System.out.println(seaBattle);
        System.out.println("Поле my_game");
        System.out.println(my_game);
    }
}
