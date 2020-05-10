package ru.progwards.sid.Y02;

//  Ссылки на статические методы, страница 478
class MethodRefDemo {
// В этом методе функциональный интерфейс указывается в качестве типа первого его
// параметра. Следовательно, ему может быть передан любой экземпляр данного интерфейса,
// включая и ссылку на метод
    static String stringOp(StringFunc sf, String s) {
        //      String func(String n);
        return sf.func(s);
    }
    public static void main(String[] args) {
        String inStr = "Лямбда-выражения повышают эффективность Java";
        String outStr;
// Здесь ссылка на метод strReverse() передается методу stringOp()
        outStr = stringOp(MyStringOps::strReverse, inStr);
        //  MyStringOps - класс
        //  strReverse - статический метод
        System.out.println("Иcxoднaя строка: " + inStr);
        System.out.println("Oбpaщeннaя строка: " + outStr);
    }
}
