package ru.progwards.java1.lessons.interfaces;

public class Hamster extends Animal {
    public Hamster(double weight){ // конструктор класса
        super(weight);
    }
    @Override
    public Animal.AnimalKind getKind(){    //  возвращает вид животного
        return AnimalKind.HAMSTER;
    }
    @Override
    public Animal.FoodKind getFoodKind(){  //  возвращает вид еды
        return FoodKind.CORN;
    }
    @Override
    public double getFoodCoeff(){
        return 0.03d;
    }

    @Override
    public CompareResult compareWeight(CompareWeight smthHasWeigt) {
        return null;
    }
}
