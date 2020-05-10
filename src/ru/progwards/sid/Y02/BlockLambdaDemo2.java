package ru.progwards.sid.Y02;

public class BlockLambdaDemo2 {
    public static void main(String[] args) {
        // Это блочное выражение изменяет на обратный порядок следования символов в строке
        StringFunc reverse = (str) -> {
        String result = "";
        for(int i = str.length()-1; i >= 0; i--)
            result += str.charAt(i);
        return result;
        };
        System.out.println("Лямбдa обращается на "
                + reverse.func("Лямбдa"));
        System.out.println("Bыpaжeниe обращается на "
                + reverse.func("Bыpaжeниe"));
    }
}
