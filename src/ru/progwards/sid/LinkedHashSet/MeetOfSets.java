package ru.progwards.sid.LinkedHashSet;
import java.util.*;

public class MeetOfSets {
    public static HashSet meetOfSets(final Set set1, final Set set2) {
//  слайд 8 - Пересечение множеств
//        HashSet result = new HashSet(set1); //  создать копию set1
        LinkedHashSet result = new LinkedHashSet(set1); //  создать копию set1 в LinkedHashSet
        result.retainAll(set2);
        return result;
    }
    public static HashSet unionOfSets(final Set set1, final Set set2) {
//  слайд 9 - Объединение множеств
//        HashSet result = new HashSet(set1);
        LinkedHashSet result = new LinkedHashSet(set1); //  создать копию set1 в LinkedHashSet
        result.addAll(set2);
        return result;
    }
    public static void main(String[] args) {
        Set<Integer> intSet1 = Set.of(3, 4, 5, 6, 7, 8, 9);
        Set<Integer> intSet2 = Set.of(1, 2, 3, 4, 5);

        Set<Integer> meetSet = meetOfSets(intSet1, intSet2);
        Set<Integer> unionSet = unionOfSets(intSet1, intSet2);

        System.out.println(intSet1);
        System.out.println(intSet2);
        System.out.println(meetSet);
        System.out.println(unionSet);
    }
}
