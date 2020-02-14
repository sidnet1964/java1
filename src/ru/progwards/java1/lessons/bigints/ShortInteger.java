package ru.progwards.java1.lessons.bigints;

public class ShortInteger extends AbsInteger{
    short num;
    ShortInteger(short num){
        this.num = num;
    }
    @Override
    public String toString() {
        return Short.toString(num);
    }
}
