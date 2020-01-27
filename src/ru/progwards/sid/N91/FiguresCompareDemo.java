package ru.progwards.sid.N91;   //  пример сравнения фигур по разным критериям

//  вариант решения задачи с помощью анонимного класса с наследованием
public class FiguresCompareDemo extends NestedFigures {
    // потомок NestedFigures, наследует свойства и медоды вложенных классов
    static class Compare_Default {
        //  ВЛОЖЕННЫЙ класс с одним методом для сравнения фигур
        boolean greater_Two(Figure figure1, Figure figure2) {
            //  метод сравнивает фигуры по периметру, может быть переопределен
            //  для сравнения по другим критериям
            return figure1.perimeter() > figure2.perimeter();
        }
    }

    // свойства класса FiguresCompare - старшего в этом примере
    private Figure[] figures;   //  массив фигур класса Figure (Segment, Square, ...)
    public Compare_Default figures_Maximator = new Compare_Default();
    //  компаратор по умолчанию, содержит метод greater_Two нашего "героя"
    //  figures_Maximator - объект локального класса или его "потомка"

    // методы, конструктор основного класса - работает только с массивом
    FiguresCompareDemo(Figure[] figures) {
        this.figures = figures;
    }

    FiguresCompareDemo(Figure[] figures, Compare_Default fc_New) {
        //  еще один конструктор, в него передается массив фигур и новый компаратор
        this(figures);  //  вызов первого конструктора
        //  fc_Over -> fc_New - объект "потомка" локального класса
        figures_Maximator = fc_New;
    }

    Figure getMaxFigure() {
        //  функция, которая будет выбирать максимальную фигуру из массива
        //  figures_Maximator - объект локального класса, содержит метод greater_Two
        Figure result = null;
        for (Figure figure: figures) {  //  цикл по массиву фигур
            if (result == null || figures_Maximator.greater_Two(figure, result))
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

        //  сравнение по периметру
        FiguresCompareDemo fcP = new FiguresCompareDemo(figures);   // один параметр
        //  создание объекта основного класса, вызывается только первый конструктор
        System.out.println("Фигура с максимальным периметром:");
        printInfo(fcP.getMaxFigure());  //  >> fcP объект основного класса

        //  анонимный класс вместо локального, но все равно потомок вложенного класса
        FiguresCompareDemo fcA = new FiguresCompareDemo(figures,
            new Compare_Default(){
                @Override
                boolean greater_Two(Figure figure1, Figure figure2) {
                    return figure1.area() > figure2.area();
                }
            }
        );  // два параметра
        //  создание объекта основного класса, вызывается второй конструктор + первый
        System.out.println("Фигура с максимальной площадью:");
        printInfo(fcA.getMaxFigure());  //  >> fcA объект основного класса
    }
}
