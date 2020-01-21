package ru.progwards.java1.lessons.interfaces;

public class Animal implements FoodCompare, CompareWeight {
    double weight;
    public Animal(double weight){
        this.weight = weight;
    }   //  конструктор
    public AnimalKind getKind(){    //  возвращает вид животного
        return AnimalKind.ANIMAL;
    }
    public FoodKind getFoodKind(){  //  возвращает вид еды
        return FoodKind.UNKNOWN;
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

    @Override
    public boolean equals(Object anObject) {    // новый 1.1
        if (this == anObject) return true;
        if (anObject == null || getClass() != anObject.getClass()) return false;
        Animal animal = (Animal) anObject;
        return Double.compare(animal.weight, weight) == 0;
    }

    public double getFood1kgPrice(){    // новый 1.2
        switch (getFoodKind()){
            case HAY: return 20;
            case CORN: return 50;
            default: return 0;
        }
    }
    public double getFoodPrice(){       // новый 1.3
        return calculateFoodWeight() * getFood1kgPrice();
    }
    @Override
    public int compareFoodPrice(Animal aminal){
        return Double.compare(this.getFoodPrice(), aminal.getFoodPrice());
    }
    //      Задача 3
    @Override
    public CompareResult compareWeight(CompareWeight smthHasWeigt) {
        return CompareResult.EQUAL;
    }
    public static void main(String[] args) {
        Cow burenka = new Cow(100);
        Duck donald = new Duck(10);
        Duck makdak = new Duck(10);
        Hamster tom = new Hamster(10);
//        System.out.println(tom.compareFoodPrice(donald));
//        System.out.println(donald.compareFoodPrice(burenka));
//        System.out.println(burenka.compareFoodPrice(tom));
//        System.out.println(makdak.compareFoodPrice(donald));
//        System.out.println(makdak.equals(donald));
//        System.out.println(makdak.equals(tom));
//      Задача 3
//        System.out.println(burenka.compareWeight(tom));
    }
    enum AnimalKind {ANIMAL, COW, HAMSTER, DUCK};
    enum FoodKind {UNKNOWN, HAY, CORN};
//    public enum CompareResult {LESS, EQUAL, GREATER};
//    //  3.2 В нем создать public enum CompareResult
}
