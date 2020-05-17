package ru.progwards.sid.Z03;

//  Прохоренок Н. А. Основы Java. <326>
//  Листинг 13.2. Статические обобщенные методы
public class MyClass1 {
    public static void main(String[] args) {
        MyClass1.<Integer>print(10);
        MyClass1.<String>print("Строка");
        MyClass1.print(10);
        MyClass1.print("Строка");
        A obj = new A();
        MyClass1.<A>echo(obj);
        MyClass1.echo(obj);
    }
    public static <T> void print(T obj) {
        System.out.println(obj.toString());
    }
    // Ограничение интерфейсом ITest
    public static <T extends ITest> void echo(T obj) {
        obj.print(); // Вызов метода из интерфейса
    }
}
interface ITest {
    void print();
}
class A implements ITest {
    private int x = 20;
    @Override
    public void print() {
        System.out.println("x = " + this.x);
    }
}
