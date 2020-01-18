package ru.progwards.java1.lessons.figures;

//  примеры из лекции 503
//  public abstract class Figure implements AreaComparable {
public abstract class Figure implements Comparable<Figure> {
    @Override
//    public int compareArea(Figure figure) {
    public int compareTo(Figure figure) {
        return Double.compare(this.area(), figure.area());
    }
    abstract double perimeter();
    double area() {
        return 0d;
    }
    public static void printInfo(Figure figure){
    System.out.println(figure);
    System.out.println("периметр = " + figure.perimeter());
    System.out.println("площадь  = " + figure.area());
    System.out.println("----");
}
    public static void main(String[] args) {
        Segment segment = new Segment(5);
        Square square = new Square(5);
        Rectangle rectangle = new Rectangle(5,11);
        Circle circle = new Circle(5);
        Triangle triangle = new Triangle(3, 4, 5);

        Figure[] figures = {segment, square, rectangle, circle, triangle};
        for (var figure: figures){
            printInfo(figure);
        }
        System.out.println("====");
//        System.out.println(square.compareArea(square));
//        System.out.println(square.compareArea(triangle));
//        System.out.println(rectangle.compareArea(circle));
        System.out.println(square.compareTo(square));
        System.out.println(square.compareTo(triangle));
        System.out.println(rectangle.compareTo(circle));
    }
}
