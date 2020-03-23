package ru.progwards.sid.N15;

import java.util.HashMap;

public class Mapper {
    static HashMap<Integer, String> a2map(int[] akey, String[] aval){
        HashMap<Integer, String> hashMap = new HashMap<>();
        for (int i=0; i<akey.length; i++)
            hashMap.put(akey[i], aval[i]);
        return hashMap;
    }

    public static void main(String[] args) {
        int akey1[] = {9, 7, 5};
        String aval1[] = {"Вася", "Петя", "Даша"};
        System.out.println(a2map(akey1, aval1));
    }
}
