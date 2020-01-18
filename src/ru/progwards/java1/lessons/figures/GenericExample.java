package ru.progwards.java1.lessons.figures;

public class GenericExample<T> {
    T object;
    public GenericExample (T object){
        this.object = object;
    }
    public String getObjectInfo(){
        return object.getClass().getName();
    }
    public static void main(String[] args) {
        System.out.println(new GenericExample<Integer>(5).getObjectInfo());
    }
}
