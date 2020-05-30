package ru.progwards.java2.lessons.basetypes;

public class Test {
    public static void main(String[] args) {
        OList<Integer> list = new OList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(list);
        OList<Integer>.ListItem<Integer> current = list.getHead();
        while(current != null) {
            System.out.println(current.getItem());
            current = current.getNext();
        }
    }
}
//OList{
// head={item=1, next={item=2, next={item=3, next={item=4, next={item=5, next=null}}}}},
// tail={item=5, next=null}}

//        long t1 = System.currentTimeMillis();
//        ArrayDeque<Integer> ad = new ArrayDeque<>();
//        List linkedList = new LinkedList();
////        for(int i=0; i<MILLION; i++)
////            ad.offer(i);
//        linkedList.add(1);  //  добавление в конец
//        linkedList.add(2);
//        linkedList.add(0, 3);
////        linkedList.remove(-1);
//        linkedList.get(-1);
//
//        long t2 = System.currentTimeMillis();
//        System.out.println("--- test add ---");
//        System.out.println("ArrayDeque = "+(t2-t1));
//        System.out.println(linkedList);
