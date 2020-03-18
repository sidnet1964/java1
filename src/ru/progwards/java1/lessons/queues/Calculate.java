package ru.progwards.java1.lessons.queues;

public class Calculate {
//  2.2*(3+12.1)
    public static double calculation1(){
        StackCalc x = new StackCalc();
        double rez = 0;
        x.push(3d);
        x.push(12.1);
        x.add();
        x.push(2.2);
        x.mul();
        rez = x.pop();
        return rez;
    }
//  (737.22+24)/(55.6-12.1)+(19-3.33)*(87+2*(13.001-9.2))
    public static double calculation2(){
//        (737.22+24) /
//        (55.6-12.1) +
//        (19-3.33) *
//        (87 +
//        2 *
//        (13.001-9.2))
        StackCalc z = new StackCalc();
        double rez = 0;
        z.push(13.001);
        z.push(9.2);
        z.sub();
        z.push(2d);
        z.mul();
        z.push(87d);
        z.add();
        z.push(19d);
        z.push(3.33);
        z.sub();
        z.mul();    //  2-е слагаемое
        z.push(737.22);
        z.push(24d);
        z.add();
        z.push(55.6);
        z.push(12.1);
        z.sub();
        z.div();
        z.add();
        rez = z.pop();
        return rez;
    }
    public static void main(String[] args) {
        System.out.println(calculation1());
//        System.out.println(calculation2());
    }

}
