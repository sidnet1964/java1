package ru.progwards.java2.lessons.recursion;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GoodsWithLambda {
    static List<Goods> lst;
    //  1.3 реализовать метод setGoods
    static void setGoods(List<Goods> list) {
        lst = new ArrayList<>(list);    // заполнение списка товаров
    }
    //  1.4 вернуть список, отсортированный по наименованию
    static List<Goods> sortByName() {
        lst.sort((a, b) -> a.name.compareTo(b.name));
        return lst;
    }
    //  1.5 вернуть список, отсортированный по артикулу, без учета регистра
    static List<Goods> sortByNumber(){
        lst.sort((a, b) -> a.number.toUpperCase().compareTo(b.number.toUpperCase()));
        return lst;
    }
    //  -----------------------------------------
    //   1.6 вернуть список, отсортированный по первым 3-м символам артикула, без учета регистра
    static List<Goods> sortByPartNumber(){
        lst.sort((a, b) -> a.number.substring(0,3).toUpperCase().compareTo(b.number.substring(0,3).toUpperCase()));
        return lst;
    }
    //  -----------------------------------------
    //  1.7 вернуть список, отсортированный по количеству, а для одинакового количества,
    //  по артикулу, без учета регистра
    static List<Goods> sortByAvailabilityAndNumber(){
//        //  проверить гипотезу сохранения порядка, вариант рабочий - отключен для поиска других
//        lst.sort((a, b) -> a.number.substring(0,3).toUpperCase().compareTo(b.number.substring(0,3).toUpperCase()));
//        lst.sort((a, b) -> Integer.compare(a.available, b.available));

        List<Goods> sorted = lst.stream().sorted(Comparator.comparing(a -> a.number.toUpperCase())).sorted(Comparator.comparing(a -> a.available)).collect(Collectors.toList());
        return sorted;
    }
    //  -----------------------------------------
    //  1.8 вернуть список, с товаром, который будет просрочен после указанной даты,
    //  отсортированный по дате годности
    static List<Goods> expiredAfter(Instant date){
        // формирование списка по критерию через поток
        List<Goods> filtered = lst.stream().filter(x -> date.compareTo(x.expired)>0).sorted(Comparator.comparing(x -> x.expired)).collect(Collectors.toList());
        return filtered;
    }
    //  1.9 вернуть список, с товаром, количество на складе которого меньше указанного
    static List<Goods> сountLess(int count){
        List<Goods> filtered = lst.stream().filter(x -> x.available < count).collect(Collectors.toList());
        return filtered;
    }
    //  1.10 вернуть список, с товаром, количество на складе которого
    //  больше count1 и меньше count2
    static List<Goods> сountBetween(int count1, int count2){
        List<Goods> filtered = lst.stream().filter(x -> x.available > count1).filter(x -> x.available < count2).collect(Collectors.toList());
        return filtered;
    }

    public static void main(String[] args) {
        setGoods(List.of(
                new Goods("Икра", "a1166", 5, 120, Instant.parse("2020-05-16T10:02:32.959644400Z")),
                new Goods("Лечо", "e222", 6, 130, Instant.parse("2020-05-10T10:02:32.959644400Z")),
                new Goods("Перец", "d333", 7, 125, Instant.parse("2020-05-15T10:02:32.959644400Z")),
                new Goods("Томаты", "C444", 6, 110, Instant.parse("2020-05-10T10:02:32.959644400Z")),
                new Goods("Соль", "a11555", 6, 12, Instant.parse("2020-05-14T10:02:32.959644400Z"))));

        System.out.println(sortByName());                   //  1.4
        System.out.println(sortByNumber());                 //  1.5
        System.out.println(sortByPartNumber());             //  1.6
        System.out.println(sortByAvailabilityAndNumber());  //  1.7
        System.out.println(expiredAfter(Instant.now()));    //  1.8
        System.out.println(сountLess(6));             //  1.9
        System.out.println(сountBetween(5, 7));           //  1.10
    }
}