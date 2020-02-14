package ru.progwards.java1.lessons.bigints;

public class IntInteger extends AbsInteger{
    int num;
    IntInteger(int num){
        this.num = num;
    }
    @Override
    public String toString() {
        return Integer.toString(num);
    }
}
