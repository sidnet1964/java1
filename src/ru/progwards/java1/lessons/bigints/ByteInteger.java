package ru.progwards.java1.lessons.bigints;

public class ByteInteger extends AbsInteger {
    byte num;
    ByteInteger(byte num){
        this.num = num;
    }
    @Override
    public String toString() {
        return Byte.toString(num);
    }
    String max_value(){
        return Byte.toString(Byte.MAX_VALUE);
    }

}
