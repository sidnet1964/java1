package ru.progwards.sid.N18;

import java.util.StringTokenizer;

public class Tokenizer {
    static String swapWords(String sentance){
        StringTokenizer tokenizer = new StringTokenizer(sentance, " .,-!\n");
        String buffer1 = "", buffer2 = "", rezult = "";
        int all = tokenizer.countTokens();  //  всего элементов
        int beg = all % 2;  // четный или нет
        while (tokenizer.hasMoreTokens()) {
            if (tokenizer.countTokens() % 2 == beg) {
                buffer1 = tokenizer.nextToken();
            }
            else {
                buffer2 = tokenizer.nextToken();
                rezult += buffer2 + " " + buffer1 + " ";
                buffer1 = "";
            }
        }
        rezult += buffer1;
        return rezult.trim();
    }
    public static void main(String[] args) {
//        String txt = "StringTokenizer - этот класс, нам строку разобьёт на раз.";
//        StringTokenizer tokenizer = new StringTokenizer(txt, " .,");
//        while (tokenizer.hasMoreTokens())
//            System.out.print(tokenizer.nextToken());
        System.out.println(swapWords("Убитых словом, добивают молчанием. (c) Уильям Шекспир."));
        System.out.format("|%04d|%#x|%2.1f|", 2, 15, 3.25);
    }
}
