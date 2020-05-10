package ru.progwards.java2.lessons.recursion;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GoodsWithLambda {
    static List<Goods> lst;
    //  1.3 реализовать метод
    static void setGoods(List<Goods> list) {
        lst = new ArrayList<>(list);
        lst.forEach(element -> System.out.println("element = " + element));
        System.out.println();
    }
    //  вернуть список, отсортированный по наименованию
    static List<Goods> sortByName() {
        lst.sort((a, b) -> a.name.compareTo(b.name));
        lst.forEach(element -> System.out.println(element));
        return null;
    }
    //  вернуть список, отсортированный по артикулу, без учета регистра
    static List<Goods> sortByNumber(){
        lst.sort((a, b) -> a.number.toUpperCase().compareTo(b.number.toUpperCase()));
        lst.forEach(element -> System.out.println(element));
        return null;
    }
    //  -----------------------------------------
    //   вернуть список, отсортированный по первым 3-м символам артикула, без учета регистра
    static List<Goods> sortByPartNumber(){
        lst.sort((a, b) -> a.number.substring(0,3).toUpperCase().compareTo(b.number.substring(0,3).toUpperCase()));
        lst.forEach(element -> System.out.println(element));
        return null;
    }
    //  -----------------------------------------
    //  вернуть список, отсортированный по количеству, а для одинакового количества,
    //  по артикулу, без учета регистра
    static List<Goods> sortByAvailabilityAndNumber(){
//        //  проверить гипотезу сохранения порядка, вариант рабочий - отключен для поиска других
//        lst.sort((a, b) -> a.number.substring(0,3).toUpperCase().compareTo(b.number.substring(0,3).toUpperCase()));
//        lst.sort((a, b) -> Integer.compare(a.available, b.available));
//        lst.forEach(System.out::println);

        //        list.sort(Comparator.comparing(a -> a.name)); //  проверить вариант
//      вариант ниже не работает, хотя каждый отдельный sort правильный
//        lst.sort(Comparator.comparing(a -> a.number.toUpperCase())).sort(Comparator.comparing(a -> a.available));
// формирование списка по критерию через поток
//        List<Integer> filtered = lst.stream().sorted().collect(Collectors.toList());
    // вывод на консоль
//        System.out.println(filtered);
        List<Goods> sorted = lst.stream().sorted(Comparator.comparing(a -> a.number.toUpperCase())).sorted(Comparator.comparing(a -> a.available)).collect(Collectors.toList());
        sorted.forEach(System.out::println);

        return null;
    }
    //  -----------------------------------------
    //  вернуть список, с товаром, который будет просрочен после указанной даты, отсортированный по дате годности
    static List<Goods> expiredAfter(Instant date){
        // формирование списка по критерию через поток
        List<Goods> filtered = lst.stream().filter(x -> date.compareTo(x.expired)>0).sorted(Comparator.comparing(x -> x.expired)).collect(Collectors.toList());
        filtered.forEach(System.out::println);
//        Instant goden = lst.get(1).expired;
//        System.out.println(date);
//        System.out.println(date.compareTo(goden));

        return null;
    }
    //  вернуть список, с товаром, количество на складе которого меньше указанного
    static List<Goods> сountLess(int count){
        List<Goods> filtered = lst.stream().filter(x -> x.available < count).collect(Collectors.toList());
        filtered.forEach(System.out::println);

        return null;
    }
    //  вернуть список, с товаром, количество на складе которого
    //  больше count1 и меньше count2
    static List<Goods> сountBetween(int count1, int count2){
        List<Goods> filtered = lst.stream().filter(x -> x.available > count1).filter(x -> x.available < count2).collect(Collectors.toList());
        filtered.forEach(System.out::println);
        return null;
    }

    public static void main(String[] args) {
//        List<Goods> list = new ArrayList<>(List.of(
//                new Goods("Икра", "111", 5, 120),
//                new Goods("Лечо", "222", 6, 130),
//                new Goods("Перец", "333", 7, 125),
//                new Goods("Томаты", "444", 6, 110),
//                new Goods("Соль", "555", 10, 12)));
        setGoods(List.of(
                new Goods("Икра", "a1166", 5, 120, Instant.parse("2020-05-06T10:02:32.959644400Z")),
                new Goods("Лечо", "e222", 6, 130, Instant.parse("2020-05-10T10:02:32.959644400Z")),
                new Goods("Перец", "d333", 7, 125, Instant.parse("2020-05-05T10:02:32.959644400Z")),
                new Goods("Томаты", "C444", 6, 110, Instant.parse("2020-05-10T10:02:32.959644400Z")),
                new Goods("Соль", "a11555", 6, 12, Instant.parse("2020-05-04T10:02:32.959644400Z"))));
//        System.out.println(sortByName());
//        System.out.println(sortByNumber());
//        System.out.println(sortByPartNumber());
//        System.out.println(sortByAvailabilityAndNumber());
//        expiredAfter(Instant.now());
//        System.out.println(сountLess(6));
        сountBetween(15, 17);
    }
}