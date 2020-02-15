package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;
import java.util.Arrays;

public class ArrayInteger {
    byte[] digits;
    ArrayInteger(int n){
        digits = new byte[n];
    }
//  .........................
    void fromInt(BigInteger value){
        String valueString = value.toString();
        for (int i = 0; i < valueString.length(); i++)
            digits[valueString.length()-i-1] = Byte.valueOf(valueString.substring(i, i+1));
    }
    //  .........................
    BigInteger toInt(){
        String rezult = "";
        for (int i=0; i < this.digits.length; i++ ) {
            rezult += Byte.toString(digits[digits.length-i-1]);
        }
            return new BigInteger(rezult);
    }
    //  .........................
    boolean add(ArrayInteger num){
        final byte BAZA = 10;
        byte perenos = 0;
        byte[] num_copy = new byte[0];
        int max_len = this.digits.length;
        if (num.digits.length <= max_len) {
            num_copy = Arrays.copyOf(num.digits, max_len);
            for (int i=0; i<this.digits.length; i++ ) {
                this.digits[i] += num_copy[i] + perenos;
                if (this.digits[i] > 9) {
                    this.digits[i] = (byte) (this.digits[i] - BAZA);
                    perenos = 1;
                }
                else
                    perenos = 0;
            }
        }
        else
            perenos = 1;

        if (perenos == 1)
            Arrays.fill(this.digits, (byte) 0);
        else
            return true;
        return false;
    }
    //  .........................
    @Override
public String toString() {
    return Arrays.toString(digits);
}

    public static void main(String[] args) {
        ArrayInteger dig1 = new ArrayInteger(5);
        ArrayInteger dig2 = new ArrayInteger(4);
        dig1.fromInt(new BigInteger("12349"));
        dig2.fromInt(new BigInteger("4329"));
//        System.out.println(dig1 + " | " + dig2);
        dig1.add(dig2);
        System.out.println(dig1);
        BigInteger dig0 = dig1.toInt();
        System.out.println(dig0);
    }
}
