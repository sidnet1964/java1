package ru.progwards.sid.HashSet;

public class ArbitraryNumberOfParameters {
    public static void threeDots(String ... strings) {
        System. out.println("\nПередано параметров: " + strings.length);
        for (int i = 0; i < strings.length; i++)
            System. out.println(strings[i]);
    }
    public static void main(String[] args) {
//  слайд 5 - Произвольное число параметров
        threeDots();
        threeDots("Три точки после типа,",
                "В том месте где параметры,",
                "Нам говорит, что их число,",
                "Неведомо заранее."
        );
        String[] arrayOfString =
                { "+", "Можно передать", "массив"};
        threeDots(arrayOfString);
    }
}
