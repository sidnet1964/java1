package ru.progwards.sid.test;

public class Point2D {
    private int x;
    private int y;
    Point2D(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString(){
        return x + "," + y;
    }
    public static void main(String[] args) {
        Point2D point = new Point2D(3,4);
        System.out.println(point.toString());
    }
}
