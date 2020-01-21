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
        Food foodOther = (Food)smthHasWeigt;
        int Ves = Integer.compare(this.weight, foodOther.weight);
        if (Ves > 0) return CompareResult.GREATER;
        if (Ves < 0) return CompareResult.LESS;
        return CompareResult.EQUAL;
    }
}
