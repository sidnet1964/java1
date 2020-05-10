package ru.progwards.sid.Y02;

//  public interface MyFunc<T> {
//      boolean func(T vl, T v2);}
//  Класс для хранения максимальной температуры за день
public class HighTemp {
    private int hTemp;
    HighTemp(int ht) {
        hTemp = ht;
    }
//  возвратить логическое значение true, если вызывающий объект типа HighTemp
//  содержит такую же температуру, как и у объекта ht2
        boolean sameTemp(HighTemp ht2) {
//            System.out.println(hTemp + " | " + ht2.hTemp);
            return hTemp == ht2.hTemp;
        }

//  возвратить логическое значение true, если вызывающий объект типа HighTemp
//  содержит температуру ниже, чем у объекта ht2
        boolean lessThanTemp(HighTemp ht2) {
        return hTemp < ht2.hTemp;
        }
//  каждый из методов совместим с функциональным интерфейсом MyFunc, поскольку
//  тип вызывающего объекта может быть приведен к типу первого параметра метода
//  func (), а тип ero аргумента к типу второго параметра этого метода.
}
