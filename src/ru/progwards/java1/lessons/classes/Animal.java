package ru.progwards.java1.lessons.classes;

//  H5 Домашнее задание
//  Задача 1. Классы Animal, Cow, Hamster, Duck
//  Задача 2. метод calculateFoodWeight
public class Animal {   //  конструктор
    double weight;
    public Animal(double weight){
        this.weight = weight;
    }
    public AnimalKind getKind(){    //  возвращает вид животного
        return AnimalKind.ANIMAL;
    }
    public FoodKind getFoodKind(){  //  возвращает вид еды
        return FoodKind.UNKNOWN;
    }
    @Override
    public String toString(){
        return "I am "+getKind()+", eat "+getFoodKind();
    }
    public double getWeight(){
        return weight;
    }
    public double getFoodCoeff(){
        return 0.02d;
    }
    public double calculateFoodWeight(){
        return weight * getFoodCoeff();
    }
    public String toStringFull(){
        return toString() + " " +calculateFoodWeight();
    }

    public static void main(String[] args) {
        Cow burenka = new Cow(100);
        Duck donald = new Duck(10);
        Hamster tom = new Hamster(1);
        //  печать рациона
        System.out.println(burenka.toStringFull());
        System.out.println(donald.toStringFull());
        System.out.println(tom.toStringFull());
    }
    enum AnimalKind {ANIMAL, COW, HAMSTER, DUCK};
    enum FoodKind {UNKNOWN, HAY, CORN};
}
