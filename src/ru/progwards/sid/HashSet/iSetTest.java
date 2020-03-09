package ru.progwards.sid.HashSet;
import java.util.*;

public class iSetTest {
//    public static Set<Integer> a2set(int[] a){
//        List<Integer> intList = new ArrayList<Integer>(a.length);
//        for (int i : a)
//            intList.add(i);
//        Set<Integer> nList = new HashSet<>(intList);
//        return nList;
//    }
public static Set<Integer> a2set(int[] a){
    Set<Integer> nList = new HashSet<>();
    for (int i : a)
        nList.add(i);
    return nList;
}

    public static void main(String[] args) {
//        String TEXT = "на дворе трава на траве дрова не руби дрова на траве двора";
//        Set<String> wordSet = new HashSet<>(Arrays.asList(TEXT.split(" ")));
//
//        Iterator<String> iter = wordSet.iterator();
//        while (iter.hasNext())
//            if (iter.next().contains("ра"))
//                iter.remove();
//
//        System.out.println(wordSet.size());
//        Set<Integer>  fiboSet1000 =
//                Set.of(0, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987);
//        int sum = 0;
//        for (int i = 2; i < 10; i++)
//            sum += fiboSet1000.contains(i) ? 1 : 0;
//        System.out.println(sum);
    int[] fiboSet1000 = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987};
        System.out.println(a2set(fiboSet1000));
    }
}
