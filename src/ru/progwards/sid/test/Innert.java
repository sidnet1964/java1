package ru.progwards.sid.test;

// Продемонстрировать применение внутреннего класса
class Innert {
    int Innert_х;
    Innert(int Innert_х){
        this.Innert_х = Innert_х;
    }
    void test() {
        Inner inner1 = new Inner(Innert_х, 11);
        inner1.display();
        Inner inner2 = new Inner(Innert_х, -11);
        inner2.display();
    }
    // это внутренний класс
    class Inner {
        int inner_х;
        int inner_y;
        Inner(int inner_х, int inner_y){
            this.inner_х = inner_х;
            this.inner_y = inner_y;
        }
        void display() {
            System.out.println("вывод: Innert_x = " + inner_х +" | "+ inner_y);
        }
    }
}

class InnertClassDemo {
    public static void main(String args[]) {
        Innert Innert1 = new Innert(10);
        Innert Innert2 = new Innert(20);
        Innert1.test();
        Innert2.test();
    }
}