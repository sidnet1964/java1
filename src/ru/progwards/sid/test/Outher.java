package ru.progwards.sid.test;

// Продемонстрировать применение внутреннего класса
class Outer {
    int outer_х;
    Outer(int outer_х){
        this.outer_х = outer_х;
    }
    void test() {
        Inner inner1 = new Inner(outer_х, 11);
        inner1.display();
        Inner inner2 = new Inner(outer_х, -11);
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
            System.out.println("вывод: outer_x = " + inner_х +" | "+ inner_y);
        }
    }
}
class InnerClassDemo {
    public static void main(String args[]) {
        Outer outer1 = new Outer(10);
        Outer outer2 = new Outer(20);
        outer1.test();
        outer2.test();
    }
}