package ru.progwards.java2.lessons.reflection;

//  класс для хранения информации о методах исследуемого класса
class Metods {
    private String modifiers;   //  модификатор доступа
    private String returned;    //  тип возвращаемого значения
    private String name;        //  имя метода
    private String param;       //  список типов параметров
    private int nParam;         //  количество параметров

    public Metods(String modifiers, String returned, String name, String param, int nParam) {
        this.modifiers = modifiers;
        this.returned = returned;
        this.name = name;
        this.param = param;
        this.nParam = nParam;
    }

    @Override
    public String toString() {
        return modifiers + ", " + returned + ", " + name + ", " + param + ", " + nParam;
    }
    //  вывод в заданном формате
    public String totoString() {
        if (nParam == 0)
            return modifiers + " " + returned + " " + name + "()";
        else
            return modifiers + " " + returned + " " + name + "(" + param + " " + firstLowerCase(name) + ")";
    }
    //  ------------------------------------
    static String firstLowerCase(String word){
        if(word == null || word.isEmpty())
            return ""; //или return word;
        return word.substring(3, 4).toLowerCase() + word.substring(4);
    }

}
