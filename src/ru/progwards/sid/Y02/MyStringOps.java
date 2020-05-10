package ru.progwards.sid.Y02;

//  Ссылки на статические методы, страница 478
class MyStringOps {
    // Статический метод, изменяющий порядок следования символов в строке
    String strReverse2(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--)
            result.append(str.charAt(i));
        return result.toString();
    }
    static String strReverse(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--)
            result.append(str.charAt(i));
        return result.toString();
    }
}
