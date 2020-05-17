package ru.progwards.java2.lessons.generics;

import java.util.ArrayList;
import java.util.List;

public class FruitBox<T extends Fruit> {
    public List<T> box;  //  ящик для хранения
    //  ------------------------------------
    public FruitBox() {
        box = new ArrayList<>();
    }
    //  ------------------------------------
    //  Добавление фрукта в коробку. Каждая коробка может содержать фрукты
    //  только одного типа, либо я блоки, либо апельсины.
    public void add(T o) {
        if (box.isEmpty()) {
            box.add(o);
        } else {
            if (box.get(0).getClass() == o.getClass()) {
                box.add(o);
            } else
                throw new UnsupportedOperationException(" смешивать запрещено ");
        }
    }
    //  ------------------------------------
    //  пересыпать фрукты из текущей коробки в другую, переданную в качестве параметра
    public void moveTo(FruitBox o) {
        if (this.box.get(0).getClass() == o.box.get(0).getClass()) {
            o.box.addAll(0, this.box);
        } else
            throw new UnsupportedOperationException(" смешивать запрещено ");
    }
    //  ------------------------------------
    public int getCount() {
        return box.size();
    }
    //  ------------------------------------
    //  высчитывает вес коробки
    public float getWeight() {
        //  1 - что храниться в коробке
        if (box.isEmpty())
            return 0;
        else {
            float oneWeight = box.get(0).weight;
            //  2 - сколько?
            int count = box.size();
            return oneWeight * count;
        }
    }
    //  ------------------------------------
    //  Реализовать метод сompareTo, который позволяет сравнить текущую коробку с переданной
    //  в качестве параметра. Вернуть -1 – если их масса текущей меньше массы переданной
    //  параметром, 0 если их массы равны и 1 в противоположном случае.
    //  Коробки с яблоками и апельсинами тоже можно сравнивать.
    public int сompareTo(FruitBox o) {
        return Float.compare(this.getWeight(), o.getWeight());
    }
    //  ------------------------------------
    public static void main(String[] args) {
        FruitBox box1 = new FruitBox(); //  коробка #1
        box1.add(new Apple(false));
        box1.add(new Apple(true));
//        box1.add(new Orange(true));
        System.out.println(box1.box);
        System.out.println(box1.getWeight());
        FruitBox box2 = new FruitBox(); //  коробка #2
        box2.add(new Orange(false));
        box2.add(new Orange(false));
        box2.add(new Orange(false));
        System.out.println();
        System.out.println(box2.box);
        System.out.println(box2.getWeight());

        FruitBox box3 = new FruitBox(); //  коробка #3
        box3.add(new Orange(true));
        box3.add(new Orange(true));
        System.out.println();
        System.out.println(box3.box);
        System.out.println(box3.getWeight());

//            box3.moveTo(box3);
//            System.out.println();
//            System.out.println(box3.box);
//            System.out.println(box3.getWeight());

        System.out.println();
        System.out.println(box3.сompareTo(box1));
        System.out.println(box3.сompareTo(box2));
    }
}
