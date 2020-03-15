package ru.progwards.java1.lessons.queues;
import java.util.*;

public class CollectionsSort {

    //  переделать алгоритм из класса ArraySort из ДЗ про массивы, на коллекции
    public static void mySort(Collection<Integer> data){
        long start0 = System. currentTimeMillis();
        int iSize = data.size();    //  размер коллекции
        ArrayList<Integer> arrayList = new ArrayList<>(data);
        long start1 = System. currentTimeMillis();
        for (int i = 0; i < iSize; i++)         //  цикл по всем элементам массива
            for (int j = i+1; j < iSize; j++)   //  цикл по элементам справа от текущего
                if (arrayList.get(i) > arrayList.get(j))   //  сравнить элементы из разных циклов
                    Collections.swap(arrayList, i, j);
        long start2 = System. currentTimeMillis();
//        System. out.println( "Сортировка # 0 - " + iSize + " = " + (start1 - start0) + " : " + (start2 - start1));
//        System.out.println(arrayList);
    }

//  переделать алгоритм из класса ArraySort из ДЗ про массивы, на коллекции
//  вариант работы с итератором, самый медленный     
    public static void mySortIt(Collection<Integer> data){
        long start0 = System. currentTimeMillis();
        int iSize = data.size();    //  размер коллекции
        ArrayList<Integer> arrayList = new ArrayList<>(data);
        long start1 = System. currentTimeMillis();
        ListIterator<Integer> list0 = arrayList.listIterator(0);
        while (list0.hasNext()) {
            Integer Obj0 = list0.next();   //  элемент слева
            ListIterator<Integer> list1 = arrayList.listIterator(list0.nextIndex());
            while (list1.hasNext()){
                Integer Obj1 = list1.next();   //  элемент справа
//                System.out.println(arrayList);
                if (Obj0 > Obj1){
                    list0.set(Obj1);
                    list1.set(Obj0);
                    Obj0 = Obj1;
//                    System.out.println(list0.nextIndex() + "|" + Obj0 + " + "+ list1.nextIndex() + "|" + Obj1);
                }
            }
        }
        long start2 = System. currentTimeMillis();
//        System. out.println( "Сортировка # 1 - " + iSize + " = " + (start1 - start0) + " : " + (start2 - start1));
//        System.out.println(arrayList);
    }
//  - найти минимальный элемент с использованием функции min()
//  - переместить его в новую коллекцию
    public static void minSort(Collection<Integer> data){
        long start0 = System. currentTimeMillis();
        int iSize = data.size();    //  размер коллекции
        ArrayList<Integer> arrayList = new ArrayList<>(data);   //  исходный список
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>(data.size()); //  новый список
        long start1 = System. currentTimeMillis();
        while (arrayList.size() > 0){
            Integer intObj = Collections.min(arrayList);    //  найти минимальный элемент
            arrayList.remove(intObj);                       //  удалить его из исходной коллекции
            arrayDeque.add(intObj);                         //  добавить элемент в новую коллекцию
        }
        arrayList.addAll(arrayDeque);
        long start2 = System. currentTimeMillis();
//        System. out.println( "Сортировка # 2 - " + iSize + " = " + (start1 - start0) + " : " + (start2 - start1));
//        System.out.println(arrayList);
    }
//   использовать метод sort из Collections
    static void collSort(Collection<Integer> data){
        long start0 = System. currentTimeMillis();
        int iSize = data.size();    //  размер коллекции
        ArrayList<Integer> arrayList = new ArrayList<>(data);
        long start1 = System. currentTimeMillis();
        arrayList.sort(null);
        long start2 = System. currentTimeMillis();
//        System. out.println( "Сортировка # 3 - " + iSize + " = " + (start1 - start0) + " : " + (start2 - start1));
    }
    public static Collection<String> compareSort(){
        ArrayList<String> arrayList = new ArrayList<>();    //  для возврата
        Collection<Integer> list1 = fillFull(1_000);
        Collections.shuffle((List<?>) list1);

        Comparator<CompMetod.Metod> metСomp = new CompMetod.TimeComparator().thenComparing(new CompMetod.NameComparator());
        TreeSet<CompMetod.Metod> metСoll = new TreeSet<CompMetod.Metod>(metСomp);

        long start0 = System.currentTimeMillis();
        mySort(list1);
        long start1 = System.currentTimeMillis();
        metСoll.add(new CompMetod.Metod("mySort", start1-start0));

        start0 = System.currentTimeMillis();
        minSort(list1);
        start1 = System.currentTimeMillis();
        metСoll.add(new CompMetod.Metod("minSort", start1-start0));

        start0 = System.currentTimeMillis();
        collSort(list1);
        start1 = System.currentTimeMillis();
        metСoll.add(new CompMetod.Metod("collSort", start1-start0));

        for (CompMetod.Metod mets : metСoll) {
            arrayList.add(mets.getName());
        }
        System.out.println(arrayList);
        return arrayList;
    }
    //  подготовка коллекции для работы
    public static Collection<Integer> fillFull(int n){
        Collection<Integer> list1 = new ArrayList<>();
        for (int i = 1; i < n+1; i++)
            list1.add(1_000 - i % 1_000); //
        return list1;
    }

    public static void main(String[] args) {
//        Collection<Integer> list1 = fillFull(1_000);
//        Collections.shuffle((List<?>) list1);
//        System.out.println(list1);
//        mySort(list1);
//        mySortIt(list1);
//        minSort(list1);
//        collSort(list1);
        compareSort();
    }
}
//  числа в диапазоне до 10_000 по 10 повторов
//Сортировка # 0 - 100_000 = 0 : 37863
//Сортировка # 1 - 100_000 = 0 : 44188
//Сортировка # 2 - 100_000 = 0 : 29735
//Сортировка # 3 - 100_000 = 0 : 92

//  числа в диапазоне до 100_000 без повторов
//Сортировка # 0 - 100_000 = 0 : 66512
//Сортировка # 1 - 100_000 = 1 : 90833   !!! 1 мс на загрузку коллекции
//Сортировка # 2 - 100_000 = 0 : 30324   !!! почти не изменилось время
//Сортировка # 3 - 100_000 = 1 : 75

//Сортировка # 3 - 1 000 000 = 2 : 845    !!! до 10_000 по 100 повторов
//Сортировка # 3 - 1 000 000 = 2 : 965    !!! до 100_000 по 10 повторов
//Сортировка # 3 - 1 000 000 = 3 : 969    !!! без повторов
