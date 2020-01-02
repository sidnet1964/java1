package ru.progwards.sid;

public class Point3D extends Point2D {
    private int z;
    Point3D(int x, int y, int z){
        super(x, y);
        this.z = z;
    }
    @Override
    public String toString(){
        return super.toString() + "," + this.z;
    }
    public static void main(String[] args) {
        Point3D point = new Point3D(3,4, 5);
        System.out.println(point.toString());
    }
}
