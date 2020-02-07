package ru.progwards.java1.SeaBattle.sidnet;
import ru.progwards.java1.SeaBattle.SeaBattle;
import ru.progwards.java1.SeaBattle.SeaBattle.FireResult;

import java.util.Arrays;
import java.util.Comparator;

public class SeaBattleAlg {

    public static final int FIRE_FREE = -2;       //  пустое поле вокруг цели
    public static final int FIRE_MISS = -1;       //  пустое поле после проверки
    public static final int FIRE_DEF = 0;         //  поле не проверялось //  public убрал
    public static final int FIRE_HIT = 1;         //  цель ранена
    public static final int FIRE_DEST = 2;        //  цель уничтожена
    public static final int FIRE_UNDEF = -9;      //  непонятный ответ
    public static int[] flot;  //  массив всех судов 1*4 + 2*3 +3*2 +4*1 = 10 судов, 20 клеток

    public static int[] stat;  //  массив статистики с индексами (ниже)
    public static final int I_DEF = 0;    //  поле не проверялось //  public убрал
    public static final int I_FREE = 1;   //  пустое поле вокруг цели    =
    public static final int I_MISS = 2;   //  пустое поле после проверки -
    public static final int I_HIT = 3;    //  цель ранена
    public static final int I_DEST = 4;   //  цель уничтожена
    public static final int I_UNDEF = 5;  //  непонятный ответ

    //  :::
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
    int counter;        // счетчик выстрелов
    private static int direct = 1;     //  направление движения
    private static int hits = 0;
    private static int palubKor = 0;

    //  -----------------------------------------------------------------
    //  конструктор основного класса
    SeaBattleAlg(SeaBattle seaBattle, int sizeX, int sizeY) {
        this.seaBattle = seaBattle;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        field_my = new int[sizeX][sizeY];   //  мое рабочее поле
        flot = new int [5];     //{0, 4, 3, 2, 1};       //  flot[i] - количество оставшихся i-палубных кораблей
        stat = new int [5];     //  stat[i] - статистика результатов стрельбы
    }
    //  -----------------------------------------------------------------
    //  методы основного класса --------------------------------------
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
                            fire2 = fire_XY(x2, y2);   // двигаемся в направлении direct
//                            fire2 = check_field(x2, y2);    /// замена на предварительную проверку
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
                            fire2 = fire_XY(x2, y2);   // двигаемся вниз
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
                ///=///System.out.println("Что-то непонятное в - " + x1 + " : " + y1);
        }
        return false;
    }

    //  методы основного класса --------------------------------------
    public void obvodka(int nosX1, int nosY1, int pKor, int oKor){     //  необходимо обвести корабль по периметру
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
                    stat[I_FREE]++;
                }
            }
    }
    //  ----------------------------------------------------------
    //  обработка одного поля (вызов из цикла)
    void field_1(int x, int y){
        int fire1;
        fire1 = fire_XY(x, y);
        //  если в поле пусто (fire1 = -1) - продолжаем
        if (fire1 > FIRE_DEF)
            analiz(fire1, x, y);
    }

    //  ----------------------------------------------------------
    //  сделать выстрел и обработать результат
    public int fire_XY(int x2, int y2){
        /// предварительная проверка
        if (field_my[x2][y2] == FIRE_DEF) {
            /// при отладке - F8
            SeaBattle.FireResult fireResult = seaBattle.fire(x2, y2);
            switch (fireResult) {
                case MISS:
                    field_my[x2][y2] = FIRE_MISS;
                    stat[I_MISS]++;
                    break;
                case HIT:
                    field_my[x2][y2] = FIRE_HIT;
//**                    ///=///System.out.println(")))))))))))))))))))");
                    stat[I_HIT]++;
                    palubKor++;
                    hits++;
                    break;
                case DESTROYED:
                    field_my[x2][y2] = FIRE_DEST;
                    stat[I_DEST]++;
                    palubKor++;
                    flot[palubKor]++;
                    palubKor = 0;   //  обнуление для следующего корабля
                    hits++;
                    assert(hits >= 20);
                    break;
                default:
                    field_my[x2][y2] = FIRE_UNDEF;
                    stat[I_UNDEF]++;
            }
            this.counter++;
        }
        return field_my[x2][y2];
    }
    //  ----------------------------------------------------------
    int check_field(int x, int y){
        //  проверить поле, для пустого - сделать выстрел
        if (field_my[x][y] == FIRE_DEF)
            return fire_XY(x, y);
        else
            return field_my[x][y];
    }
    //  ----------------------------------------------------------
    void free_All(){   //  просчитать остаток

        stat[I_DEF] = 100 - stat[I_FREE] - stat[I_MISS] - stat[I_HIT] - stat[I_DEST];   // остаток пустых полей
        ///=///System.out.println();
        ///=///System.out.println("free_All = * "+  SeaBattleAlg.this.counter + " поле my_game");
        ///=///System.out.println(" >> stat = " + Arrays.toString(stat) + " - flot = " + Arrays.toString(flot));
        ///=///System.out.println(this);

        for (int m = 0; ; m++) {

        Free[] single = new Free[50];   //  массив однопалубных объектов
        int sizeArray = 0;
        for (int y = 0; y < sizeY; y++) {       //  sizeX
            for (int x = 0; x < sizeX; x++) {   //  sizeY
                if (field_my[x][y] == 0) {
                    Free zona = new Free(x, y);
                    int vokrug = zona.Obzor(x, y);
                    single[sizeArray] = zona;
//                    ///=///System.out.println("vokrug = " + vokrug + " в - " + x + " : " + y);
                    sizeArray++;
                }
            }
        }
        ///=///System.out.print("free_All - R - " + m + " дырок = " + sizeArray);

        for (int k = sizeArray; k < single.length; k++)
            single[k] = new Free(0, 0);     // заполнение массива пустыми значениями?
        Arrays.sort(single);
        if (single[0].f_count < 2) break;
        ///=///System.out.println(" + Max = " + single[0].f_count + " в - " + single[0].f_aX + " : " + single[0].f_aY);
        Scan_1point(single[0].f_aX, single[0].f_aY);
        }
        ///=///System.out.println();   //  пустая строка
    }

    //  ----------------------------------------------------------
    void clean_All(){   //  зачистить остаток
        for (int y = 0; y < sizeY; y++) {       //  sizeX
            for (int x = 0; x < sizeX; x++) {   //  sizeY
                switch (direct){
                    case DIRECT_R:
                        if (field_my[x][y] == 0)
                            field_1(x, y);
                        break;
//                    case DIRECT_L:
//                        int x0 = sizeX - x - 1;     //  обратный индекс
//                        if (field_my[x0][y] == 0)
//                            field_1(x0, y);
//                        break;
                    default:
                }
                if (hits >= 20){
                    hits = 0;
                    return;
                }
            }
//            direct = - direct;  //  можно отключить
        }
    }

    //  ----------------------------------------------------------
    void Scan_1point(int x, int y){     //  сканирование одной точки
        int check;
        if (field_my[x][y] == 0){
            check = fire_XY(x, y);   // стреляем и проверяем
            switch (check) {
                case FIRE_DEST:
                    obvodka(x, y, 1, 0);   //  выполнить обводку
                    break;  //  switch (check)
                case FIRE_HIT:
                    Boat clipper = new Boat(x, y);
                    clipper.Build(x, y);
                    clipper.Dost(x, y);
//                                ///=///System.out.println(palubKor + " палуб в - " + x + " : " + y);
                    break;  //  switch (check)
                default:
            }
        }
    }

    //  ----------------------------------------------------------
    void Scan_List(int[][] listIk){
        int x, y, check;
        for (int i = 0; i < listIk.length; i++){
            if (listIk[i][0] > 0) {
                x = listIk[i][1];
                y = listIk[i][2];
                Scan_1point(x, y);
            }
//            else
//                break;
        }
    }

    //  ----------------------------------------------------------
    void Fill_List(int[][] listIk, int[] descr, boolean napravlenie, boolean allInclude) {
        //  пройтись по диагонали сверху направо
        boolean confirm = false;
        for (int de : descr)
            for (int y = 0; y < sizeY; y++) {       //  sizeX
                for (int x = 0; x < sizeX; x++) {   //  sizeY
                    if (napravlenie)
                        confirm = allInclude || (x == y + de);
                    else
                        confirm = allInclude || (x + y == sizeY - 1 + de);
                    if (confirm)
                        for (int i = 0; i < listIk.length; i++)
                            //  найти не-нулевое значение счетчика точек
                            if (listIk[i][0] == 0) {
                                listIk[i][0] = 1;
                                listIk[i][1] = x;
                                listIk[i][2] = y;
                                break;
                            }
                    }
                }
//        ///=///System.out.println(Arrays.deepToString(listIk));
    }
    //  -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=-
    public void battleAlgorithm(SeaBattle seaBattle) {
//        int check;
        int[][] listInt = new int[300][3];    //  план наступления
        int[] descr1 = {2, -2, 6, -6, 0, 4, -4, 8, -8};   //  массив отклонений от диагонали
        Fill_List(listInt, descr1, true, false); //  запонить список диапазоном точек
//        int[] descr2 = {0, 4, -4, 8, -8};   //  массив отклонений от диагонали
//        Fill_List(listInt, descr2, true, false); //  запонить список диапазоном точек
//        ///=///System.out.println(Arrays.deepToString(listInt));

        Scan_List(listInt); //  -- НЕ ТРОГАТЬ -- обработать список точек
        //  проанализировать статистику по кораблям

//        /// блок для отладки
//        ///=///System.out.println("battleAlgorithm - поле my_game");
//        ///=///System.out.println(this);   /// посмотреть, что осталось
//        stat[I_DEF] = 100 - stat[I_FREE] - stat[I_MISS] - stat[I_HIT] - stat[I_DEST];   // остаток пустых полей
//        ///=///System.out.println("stat = " + Arrays.toString(stat));
//        ///=///System.out.println("flot = " + Arrays.toString(flot));
//        ///=///System.out.println("Выстрелов = " + this.counter);


        free_All();
        clean_All();    //  зачистить остаток

//  вывод для отладки
//        ///=///System.out.println(seaBattle);
//        ///=///System.out.println(this);

//  ==========================================================
    }
    @Override
    public String toString() {
//        assert this.sizeX > 0 && this.sizeY > 0;

        StringBuilder result = new StringBuilder("@");

        int y;
        for(y = 0; y < this.sizeX; ++y) {
            result.append("|").append(y % 10);
        }

//        result.append("-   координата x");
        result.append("\n");

        for(y = 0; y < this.sizeY; ++y) {
            result.append(y).append("|");

            for(int x = 0; x < this.sizeX; ++x) {
                result.append(this.field_my[x][y] == 0 ? "."
                        :(this.field_my[x][y] == -2 ? "="
                        :(this.field_my[x][y] == -1 ? "\\"
                        :(this.field_my[x][y] == 1 ? "+"
                        :(this.field_my[x][y] == 2 ? "X"
                        : "?"))))).append("|");
            }

////    отображение оставшихся точек
//            for(int x = 0; x < this.sizeX; ++x) {
//                result.append(this.field_my[x][y] == 0 ? "#"
//                        :(this.field_my[x][y] == -2 ? "."
//                        :(this.field_my[x][y] == -1 ? "-"
//                        :(this.field_my[x][y] == 1 ? "."
//                        :(this.field_my[x][y] == 2 ? "."
//                        : "?"))))).append("|");
//            }

            result.append("\n");
        }

        return result.toString();
    }

//  ========================================================
public class Free implements Comparable<Free>{
    //  класс - свободные клетки
    //  содержит методы для расчета связности
    int f_aX, f_aY;     //  якорь, первое значение
    int f_count;        //  количество соседей

@Override
public int compareTo(Free o){
    return Integer.compare(o.quantity(), this.quantity()); //  обратный порядок
}
    int quantity() {return f_count;}  // метод интерфейса
    //  --------------------------------------------------------
    Free(int f_aX, int f_aY) {  //  конструктор
        this.f_aX = f_aX;
        this.f_aY = f_aY;
        this.f_count = 0;
    }
    //  --------------------------------------------------------
    int Obzor(int f_aX, int f_aY){
        int f_vsego = 0;
        int i1 = 0; int i2 = 0; int j1 = 0; int j2 = 0;
        i1 = Math.max(0, f_aX - 1);    i2 = Math.min(f_aX + 2, sizeX);
        j1 = Math.max(0, f_aY - 1);    j2 = Math.min(f_aY + 2, sizeY);
        for (int i = i1; i < i2; i++)
            for (int j = j1; j < j2; j++) {
                if (field_my[i][j] == FIRE_DEF) {
                    f_vsego++;
                }
            }
        this.f_count = f_vsego-1;   //  отнимаем точку обзора
        return f_vsego-1;
    }
}
    //  ========================================================
public class Boat {
    //  класс - корабль 1-многопалубный создается при первом попадаии
    //  содержит методы для построения полных координат и обводки (?)
    int b_aX, b_aY; //  якорь, первое значение
    int b_nX, b_nY; //  нос, крайнее левое/верхнее значение (для обводки)
    int b_orient;   //  ориентация палуб в объекте = 1-гориз, 2-верт
    int b_palubK;   //  количество палуб в текушем раунде
    int b_check;
    boolean b_lim;

        //  --------------------------------------------------------
    Boat(int b_aX, int b_aY) {
        this.b_aX = b_aX;
        this.b_aY = b_aY;
        this.b_nX = b_aX;       //  фиксируем для начала строительства
        this.b_nY = b_aY;       //
        this.b_palubK = 1;      //  если создаем объект, то одна палуба уже есть ?
    }

    //  --------------------------------------------------------
    private void Dost(int b_x1_, int b_y1_) {   //  достройка корабля
        obvodka(b_nX, b_nY, b_palubK, b_orient);
    }

    //  --------------------------------------------------------
    private int[][] rozaVetrov(int b_x1_, int b_y1_) {  //  поиск попутного ветра
        int delta = 0;
        int[][] retu = new int[4][2];   //  возвращаемый массив
        int[][] roza = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0: delta =  sizeX - 1 - b_x1_; break;  //  право
                case 1: delta =  sizeY - 1 - b_y1_; break;  //  вниз
                case 2: delta = b_x1_;  break;              //  влево
                case 3: delta = b_y1_;  break;              //  вверх
            }
            int dX = roza[i][0];
            int dY = roza[i][1];
            retu[i][1] = i;
            for (int j = 0; j < delta; j++){
                if (field_my[b_x1_ + dX][b_y1_ + dY] == FIRE_DEF) {
                    retu[i][0]++;
                    dX += roza[i][0];
                    dY += roza[i][1];
                }
                else
                    break;
            }
        }
        //  подрезать до максимальной длины корабля
        for (int i = 0; i < 4; i++)
            retu[i][0] = Math.min(retu[i][0], 3);   //  3 - остаток палуб

        Arrays.sort(retu, Comparator.comparingInt(arr -> -arr[0]));  //  по индексу 0 - количество убывает
//        ///=///System.out.println(Arrays.deepToString(retu));
        return retu;
    }
    //  --------------------------------------------------------
    private void Build(int b_x1_, int b_y1_) {   //  достройка корабля

        /// добавить выбор направления, варианты:
        //  если движение по диагонали, то сохранять направление
        int[][] poisk = rozaVetrov(b_x1_, b_y1_);   //  массив направлений
        if (SeaBattleAlg.this.counter > 20) {
            ///=///System.out.println();
            ///=///System.out.println("Build * " +  SeaBattleAlg.this.counter + " - поле my_game в - " + b_x1_ + " : " + b_y1_);
            ///=///System.out.println(SeaBattleAlg.this);  ///  my_game
            ///=///System.out.println("Build - роза ветров - " + Arrays.deepToString(poisk));
        }
        int ind = poisk[0][1];  //  напраление первого удара (право, низ, лево, верх)
        switch (ind){
            case 0:     //  вправо
                if (Build_R(b_x1_, b_y1_)) return;
                if (b_orient == 1)  //  надо продолжить в обратном направлении
                    if (Build_L(b_x1_, b_y1_))  return;
                if (Build_D(b_x1_, b_y1_))      return;
                if (Build_U(b_x1_, b_y1_))      return;
                if (Build_L(b_x1_, b_y1_))      return;
            case 1:     //  вниз
                if (Build_D(b_x1_, b_y1_))      return;
                if (b_orient == 2)  //  надо продолжить в обратном направлении
                    if (Build_U(b_x1_, b_y1_))  return;
                if (Build_R(b_x1_, b_y1_))      return;
                if (Build_L(b_x1_, b_y1_))      return;
                if (Build_U(b_x1_, b_y1_))      return;
            case 2:     //  влево
                if (Build_L(b_x1_, b_y1_)) return;
                if (b_orient == 1)  //  надо продолжить в обратном направлении
                    if (Build_R(b_x1_, b_y1_))  return;
                if (Build_D(b_x1_, b_y1_))      return;
                if (Build_U(b_x1_, b_y1_))      return;
                if (Build_R(b_x1_, b_y1_))      return;
            case 3:     //  вниз
                if (Build_U(b_x1_, b_y1_))      return;
                if (b_orient == 2)  //  надо продолжить в обратном направлении
                    if (Build_D(b_x1_, b_y1_))  return;
                if (Build_R(b_x1_, b_y1_))      return;
                if (Build_L(b_x1_, b_y1_))      return;
                if (Build_D(b_x1_, b_y1_))      return;
        }
//  :::::::::::::::::::::::::::::::
        return;
    }
    //  --------------------------------------------------------
    private boolean Build_R(int b_x1_, int b_y1_) {   //  достройка корабля вправо
        int b_x2, b_y2;
        for (int i = 1; ; i++) {
            b_lim = (b_x1_ + i <= sizeX - 1);
            if (b_lim) {          // если НЕ достигли границы
                b_x2 = b_x1_ + i;        //  точка справа
                b_y2 = b_y1_;
                b_check = fire_XY(b_x2, b_y2);   // стреляем и проверяем
                ///=///System.out.print(" B_R "+ i + " -> " + b_check);
                if (b_check == FIRE_DEST) {
                    b_palubK++;
                    b_orient = 1;    //  горизонт
                    return true;
                } else if (b_check == FIRE_HIT) {
                    b_palubK++;
                    b_orient = 1;    //  горизонт
                } else
                    break;
            } else
                break;
        }
        return false;
    }
    //  --------------------------------------------------------
    private boolean Build_L(int b_x1_, int b_y1_) {   //  достройка корабля влево
        int b_x2, b_y2;
        for (int i = 1; ; i++) {
            b_lim = (b_x1_ - i >= 0);
            if (b_lim) {          // если НЕ достигли границы
                b_x2 = b_x1_ - i;        //  точка слева
                b_y2 = b_y1_;
                b_check = fire_XY(b_x2, b_y2);   // стреляем и проверяем
                ///=///System.out.print(" B_L "+ i + " -> " + b_check);
                if (b_check == FIRE_DEST) {
                    b_nX = b_x2;        // обновление координат
                    b_palubK++;
                    b_orient = 1;    //  горизонт
                    return true;
                } else if (b_check == FIRE_HIT) {
                    b_nX = b_x2;        // обновление координат
                    b_palubK++;
                    b_orient = 1;       //  горизонт
                } else
                    break;
            } else
                break;
        }
        return false;
    }
        //  --------------------------------------------------------
        private boolean Build_D(int b_x1_, int b_y1_) {   //  достройка корабля вниз
            int b_x2, b_y2;
            for (int i = 1; ; i++) {
                b_lim = (b_y1_ + i <= sizeY - 1);
                if (b_lim) {          // если НЕ достигли границы
                    b_x2 = b_x1_;            //  точка снизу
                    b_y2 = b_y1_ + i;
                    b_check = fire_XY(b_x2, b_y2);   // стреляем и проверяем
                    ///=///System.out.println(" B_D "+ i + " -> " + b_check);
                    if (b_check == FIRE_DEST) {
                        b_palubK++;
                        b_orient = 2;
                        return true;
                    } else if (b_check == FIRE_HIT) {
                        b_palubK++;
                        b_orient = 2;
                    } else
                        break;
                } else
                    break;
            }
            return false;
        }
        //  --------------------------------------------------------
        private boolean Build_U(int b_x1_, int b_y1_) {   //  достройка корабля вверх
            int b_x2, b_y2;
            for (int i = 1; ; i++) {
                b_lim = (b_y1_ - i >= 0);
                if (b_lim) {          // если НЕ достигли границы
                    b_x2 = b_x1_;            //  точка сверху
                    b_y2 = b_y1_ - i;
                    b_check = fire_XY(b_x2, b_y2);   // стреляем и проверяем
                    ///=///System.out.println(" B_U "+ i + " -> " + b_check);
                    if (b_check == FIRE_DEST) {
                        b_nY = b_y2;        // обновление координат
                        b_palubK++;
                        b_orient = 2;
                        return true;
                    } else if (b_check == FIRE_HIT) {
                        b_nY = b_y2;        // обновление координат
                        b_palubK++;
                        b_orient = 2;
                    } else
                        break;
                } else
                    break;
            }
            return false;
        }
    //  --------------------------------------------------------
    }
//  ========================================================

    // функция для отладки
    public static void main(String[] args) {
//        ///=///System.out.println("Sea battle");
//        SeaBattle seaBattle = new SeaBattle(true);
//        SeaBattleAlg my_game = new SeaBattleAlg(seaBattle, 10, 10);
//        my_game.battleAlgorithm(seaBattle);
//        ///=///System.out.println("Результат = " + seaBattle.getResult());
//        ///=///System.out.println("Счетчик = " + my_game.counter);
//        ///=///System.out.println("Поле seaBattle");
//        ///=///System.out.println(seaBattle);
//        ///=///System.out.println("Поле my_game");
//        ///=///System.out.println(my_game);
//        ///=///System.out.println("stat = " + Arrays.toString(stat));
//        ///=///System.out.println("flot = " + Arrays.toString(flot));
        TestFull();
    }
    static void TestFull() {
        ///  для анализа статистики
        int[] flot_;  //  массив всех судов 1*4 + 2*3 +3*2 +4*1 = 10 судов, 20 клеток
        int[] stat_;  //  массив статистики с индексами (ниже)
        ///  для анализа статистики
        ///=///System.out.println("Sea battle");
        ///  для анализа статистики
        flot_ = new int [5];     //{0, 4, 3, 2, 1};       //  flot[i] - количество оставшихся i-палубных кораблей
        stat_ = new int [5];     //  stat[i] - статистика результатов стрельбы
        ///  для анализа статистики
        double result = 0, fullCounter = 0;
        int retry = 1000;   /// 1000
        for (int i = 0; i < retry; i++) {
//            ///=///System.out.println("Счетчик цикла = " + i);
            SeaBattle seaBattle = new SeaBattle();
            SeaBattleAlg my_game = new SeaBattleAlg(seaBattle, 10, 10);
            my_game.battleAlgorithm(seaBattle);
            result += seaBattle.getResult();
            fullCounter += my_game.counter;

//            if  (retry == 1){
//                ///=///System.out.println("TestFull - поле my_game");
//                ///=///System.out.println(my_game);
//            }

            ///  для анализа статистики
            stat[I_DEF] = 100 - stat[I_FREE] - stat[I_MISS] - stat[I_HIT] - stat[I_DEST];   // остаток пустых полей
            for (int j = 0; j < stat.length; j++) {
                stat_[j] += stat[j];
                flot_[j] += flot[j];
            }
            ///=///System.out.println(i + " >> stat = " + Arrays.toString(stat) + " - flot = " + Arrays.toString(flot));
            ///=///System.out.println(i + "|||||||||||" + (int)seaBattle.getResult() + "|" + my_game.counter + "|>>>");
//            ///=///System.out.println(my_game.counter+","+stat[I_DEF]+","+stat[I_FREE]+","+stat[I_MISS]+","+stat[I_HIT]+","+stat[I_DEST]+","+flot[1]+","+flot[2]+","+flot[3]+","+flot[4]);
            ///  для анализа статистики
        }
        ///=///System.out.println();
        System.out.println("Результат = " + result / retry);
        ///=///System.out.println("Счетчик = " +  fullCounter / retry);
        ///=///System.out.println(retry + " >> stat = " + Arrays.toString(stat_) + " - flot = " + Arrays.toString(flot_));
    }
}
//
//  sort(ark, Comparator.comparingInt(arr -> arr[1]));
