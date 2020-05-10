package ru.progwards.sid.Y02;

public class GenericFunctionalinterfaceDemo {
    public static void main(String[] args) {
        // использовать строковый вариант функционального интерфейса SomeFunc
        SomeFunc<String> reverse = (str) -> {
        String result = "";
        for(int i = str.length()-1; i >=0; i--)
            result += str.charAt(i);
        return result;
        } ;
        System.out.println("Лямбдa на " + reverse.func("Лямбдa") );
        System.out.println("Bыpaжeниe на " + reverse. func ( "Выражение" ) ) ;

        // а теперь использовать целочисленный вариант функционального интерфейса SomeFunc
        SomeFunc<Integer> factorial = (n) -> {
        int result = 1;
        for(int i=1; i <= n; i++)
            result = i * result;
        return result;
        } ;
        System.out.println("Фaктopиaл числа 3 равен " + factorial.func(3));
    }
}
