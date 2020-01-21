package ru.progwards.java1.lessons.interfaces;

public class Cow extends Animal {
    public Cow(double weight){ // конструктор класса
        super(weight);
    }
    @Override
    public AnimalKind getKind(){    //  возвращает вид животного
        return AnimalKind.COW;
    }
    @Override
    public FoodKind getFoodKind(){  //  возвращает вид еды
        return FoodKind.HAY;
    }
    @Override
    public double getFoodCoeff(){
        return 0.05d;
    }
}
