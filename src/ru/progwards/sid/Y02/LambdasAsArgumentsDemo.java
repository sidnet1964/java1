package ru.progwards.sid.Y02;
//public interface StringFunc {
//    String func(String n);}

public class LambdasAsArgumentsDemo {
    static String stringOp(StringFunc sf, String s){
        //  информация из отладчика
        //  sf = {LambdasAsArgumentsDemo$lambda@833}
        // Class has no fields
        return sf.func(s);
    }
    public static void main(String[] args) {
        String inStr = "Лямбда-выражения повышают эффективность Java";
        String outStr;
        System.out.println("Этo исходная строка: " + inStr);
        // Ниже приведено простое лямбда-выражение, преобразующее в прописные
        // все буквы в исходной строке, передаваемой методу stringOp()
        outStr = stringOp((str) -> str.toUpperCase(), inStr);
        System.out.println("Этo строка прописными буквами: " + outStr);
        // А здесь передается блочное лямбда-выражение,
        // удаляющее пробелы из исходной символьной строки
        outStr = stringOp((str) -> {
            String result = "";
            for(int i = 0; i < str.length(); i++)
                if(str.charAt(i) != ' ')
                    result += str.charAt(i);
            return result;
        } , inStr);
        System.out.println("Этo строка с удаленными пробелами: " + outStr);
// Можно, конечно, передать и экземпляр функционального интерфейса StringFunc,
// созданный в предыдущем лямбда-выражении. Например, после следующего объявления ссылка
// reverse делается на экземпляр интерфейса StringFunc
        StringFunc reverse = (str) -> {
            String result = "";
            for(int i = str.length()-1; i >= 0; i--)
                result += str.charAt(i);
            return result;
        } ;
// А теперь ссылку reverse можно передать в качестве первого параметра методу stringOp(),
// поскольку она делается на объект типа StringFunc
        System.out.println("Этo обращенная строка: " + stringOp(reverse, inStr) );
    }
}
