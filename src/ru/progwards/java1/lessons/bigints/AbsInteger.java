package ru.progwards.java1.lessons.bigints;
import java.math.BigInteger;

//  H9 Домашнее задание
//  Задача 2. Класс AbsInteger и потомки
public abstract class AbsInteger {
    static AbsInteger add(AbsInteger num1, AbsInteger num2){
//        Integer ret = (Integer)num1 + (int)num2;
        BigInteger n1 = new BigInteger(num1.toString());
        BigInteger n2 = new BigInteger(num2.toString());
        BigInteger n0 = n1.add(n2);
//        String n0s = n0.toString();
//        System.out.println(n0 + "|" + n0s);
        BigInteger min_byte = new BigInteger(Byte.toString(Byte.MIN_VALUE));
        BigInteger max_byte = new BigInteger(Byte.toString(Byte.MAX_VALUE));
        BigInteger min_short = new BigInteger(Short.toString(Short.MIN_VALUE));
        BigInteger max_short = new BigInteger(Short.toString(Short.MAX_VALUE));
        if (n0.compareTo(min_byte) > 0 && n0.compareTo(max_byte) < 0)
        {
            System.out.println("Это - Byte - " + n0.byteValue());
            ByteInteger r0 = new ByteInteger(n0.byteValue());
            return r0;
        }
        else if (n0.compareTo(min_short) > 0 && n0.compareTo(max_short) < 0)
        {
            System.out.println("Это - Short - " + n0.shortValue());
            ShortInteger r0 = new ShortInteger(n0.shortValue());
            return r0;
        }
            else
            {
                System.out.println("Это - Short - " + n0.intValue());
                IntInteger r0 = new IntInteger(n0.intValue());
                return r0;
            }
//        AbsInteger ret = null;
//        return null;
    }
    public static void main(String[] args) {
        ByteInteger b1 = new ByteInteger((byte)50);
        System.out.println(b1);
        ShortInteger s1 = new ShortInteger((short)-60 );
        System.out.println(s1);
        AbsInteger a1 = add(b1, s1);
        System.out.println(a1);
    }
}
//  Поскольку этот класс расширяет класс Number, следовательно, в нем переопределены методы
//  doubleValue(), floatValue(), intValue(), longValue().
//  Методы byteValue() и shortValue() не переопределены, а прямо наследуются от класса Number.