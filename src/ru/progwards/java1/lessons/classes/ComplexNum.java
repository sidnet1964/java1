package ru.progwards.java1.lessons.classes;

public class ComplexNum {
    int a;
    int b;
    public ComplexNum(int a, int b){
        this.a = a;
        this.b = b;
    }
    public String toString(){
        return a + "+" + b + "i";
    }
    public ComplexNum add(ComplexNum num){
        //  (a + bi) + (c + di) = (a + c) + (b + d)i
        ComplexNum z0 = new ComplexNum(this.a + num.a, this.b + num.b);
        return z0;
    }
    public ComplexNum sub(ComplexNum num){
        //  (a + bi) - (c + di) = (a - c) + (b - d)i
        ComplexNum z0 = new ComplexNum(this.a - num.a, this.b - num.b);
        return z0;
    }
    public ComplexNum mul(ComplexNum num){
        //  (a + bi) * (c + di) = (a*c - b*d) + (b*c + a*d)i
        int a = this.a;
        int b = this.b;
        int c = num.a;
        int d = num.b;
        ComplexNum z0 = new ComplexNum(a*c - b*d, b*c + a*d);
        return z0;
    }
    public ComplexNum div(ComplexNum num){
        //  (a + bi) / (c + di) = (a*c + b*d)/(c*c+d*d) + ((b*c - a*d)/(c*c+d*d))i
        int a = this.a;
        int b = this.b;
        int c = num.a;
        int d = num.b;
        ComplexNum z0 = new ComplexNum((a*c + b*d)/(c*c + d*d), (b*c - a*d)/(c*c + d*d));
        return z0;
    }
    public static void main(String[] args) {
        ComplexNum x = new ComplexNum(30,40);
        ComplexNum y = new ComplexNum(1,2);
        System.out.println(x.add(y).toString());
        System.out.println(x.sub(y).toString());
        System.out.println(x.mul(y).toString());
        System.out.println(x.div(y).toString());
    }
 }
