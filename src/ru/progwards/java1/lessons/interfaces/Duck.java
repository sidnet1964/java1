package ru.progwards.java1.lessons.interfaces;

public class Duck extends Animal{
    public Duck(double weight){ // конструктор класса
        super(weight);
    }
    @Override
    public Animal.AnimalKind getKind(){    //  возвращает вид животного
        return AnimalKind.DUCK;
    }
    @Override
    public Animal.FoodKind getFoodKind(){  //  возвращает вид еды
        return Animal.FoodKind.CORN;
    }
    @Override
    public double getFoodCoeff(){
        return 0.04d;
    }

    @Override
    public CompareResult compareWeight(CompareWeight smthHasWeigt) {
        return null;
    }
}
