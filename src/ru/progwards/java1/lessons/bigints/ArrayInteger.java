package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;
import java.util.Arrays;

public class ArrayInteger {
    byte[] digits;
    ArrayInteger(int n){
        digits = new byte[n];
    }

    void fromInt(BigInteger value){
        String valueString = value.toString();
        for (int i = 0; i < valueString.length(); i++)
            digits[valueString.length()-i-1] = Byte.valueOf(valueString.substring(i, i+1));
    }
    BigInteger toInt(){
        return new BigInteger(digits);
    }
    boolean add(ArrayInteger num){
        final Byte BAZA = 10;
        Byte perenos = 0;
        for (int i=0; i<this.digits.length; i++ ) {
            this.digits[i] += num.digits[i] + perenos;
            if (this.digits[i] > 9) {
                this.digits[i] = (byte) (this.digits[i] - BAZA);
                perenos = 1;
            }
            else
                perenos = 0;
        }
        if (perenos == 1)
            for (int i=0; i<this.digits.length; i++ )
                this.digits[i] = 0;
        else
            return true;
        return false;
    }
    @Override
public String toString() {
    return Arrays.toString(digits);
}

    public static void main(String[] args) {
        ArrayInteger dig1 = new ArrayInteger(5);
        ArrayInteger dig2 = new ArrayInteger(5);
        dig1.fromInt(new BigInteger("12349"));
        dig2.fromInt(new BigInteger("54329"));
//        System.out.println(dig1 + " | " + dig2);
        dig1.add(dig2);
//        System.out.println(dig1);
    }
}
