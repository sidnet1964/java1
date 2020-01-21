package ru.progwards.java1.lessons.interfaces;

public class Food implements CompareWeight{
    int weight;
    public Food(int weight){
        this.weight = weight;
    }   //  конструктор
    public int getWeight(){
        return weight;
    }

    @Override
    public CompareResult compareWeight(CompareWeight smthHasWeigt) {
        return CompareResult.EQUAL;
    }
}
