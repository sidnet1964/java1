package ru.progwards.sid.N91;   //  пример сравнения фигур по разным критериям

import java.util.Comparator;

//  вариант решения задачи с помощью анонимного класса с интерфейсом
public class FiguresInterComparator extends NestedFigures {
    // потомок NestedFigures, наследует свойства и медоды вложенных классов
    // свойства класса FiguresCompare - старшего в этом примере
    private Figure[] figures;   //  массив фигур класса Figure (Segment, Square, ...)
    public Comparator figuresComparator = new Comparator<Figure>(){
        @Override
        public int compare(Figure o1, Figure o2){
            return o1.compareTo(o2);
        }
    };

    // методы, конструктор основного класса - работает только с массивом
    FiguresInterComparator(Figure[] figures) {
        this.figures = figures;
    }

    FiguresInterComparator(Figure[] figures, Comparator<Figure> fc_New) {
        //  еще один конструктор, в него передается массив фигур и новый компаратор
        this(figures);  //  вызов первого конструктора
        //  fc_Over -> fc_New - объект "потомка" локального класса
        figuresComparator = fc_New;
    }

    Figure getMaxFigure() {
        //  функция, которая будет выбирать максимальную фигуру из массива
        //  figures_Maximator - объект локального класса, содержит метод greater_Two
        Figure result = null;
        for (Figure figure: figures) {  //  цикл по массиву фигур
            if (result == null || figuresComparator.compare(figure, result) > 0)
                result = figure;
        }
        return result;
    }
    //  конец описания класса FiguresCompare, далее исполнение кода

    public static void main(String[] args) {
        //  создание фигур
        Segment segment = new Segment(5);
        Square square = new Square(5);
        Rectangle rectangle = new Rectangle(5, 11);
        Circle circle = new Circle(5);
        Triangle triangle = new Triangle(3, 4, 5);

        //  создание массива фигур
        Figure[] figures = {segment, square, rectangle, circle, triangle};

        for (Figure figure: figures) {
            System.out.println(figure); //  вывод через toString()
        }
        System.out.println();

//        //  сравнение по периметру
//        FiguresInterComparator fcP = new FiguresInterComparator(figures);   // один параметр
//        //  создание объекта основного класса, вызывается только первый конструктор

        //  анонимный класс вместо локального через интерфейс
        FiguresInterComparator fcP = new FiguresInterComparator(figures,
                new Comparator<Figure>(){
                    @Override
                    public int compare(Figure o1, Figure o2) {
                        return Double.compare(o1.perimeter(), o2.perimeter());
                    }
                }
        );  // два параметра

        System.out.println("Фигура с максимальным периметром:");
        printInfo(fcP.getMaxFigure());  //  >> fcP объект основного класса

        //  анонимный класс вместо локального через интерфейс
        FiguresInterComparator fcA = new FiguresInterComparator(figures,
            new Comparator<Figure>(){
                @Override
                public int compare(Figure o1, Figure o2) {
                    return Double.compare(o1.area(), o2.area());
                }
            }
        );  // два параметра
        System.out.println("Фигура с максимальной площадью:");
        printInfo(fcA.getMaxFigure());  //  >> fcA объект основного класса
    }
}
