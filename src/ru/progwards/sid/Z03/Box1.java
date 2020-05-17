package ru.progwards.sid.Z03;

//  Прохоренок Н. А. Основы Java. <321>
//  Здесь мы просто заменили класс Object обобщенным типом T
//  и добавили объявление обобщенного типа в заголовок класса.
class Box1<T> {
    private T obj;
    public Box1(T obj) {
        this.setObj(obj);
    }
    public T getObj() {
        return obj;
    }
    public void setObj(T obj) {
        this.obj = obj;
    }
//  Таким образом мы можем хранить данные любого типа, но одновременно только
//  одного. При этом компилятор контролирует типы и не позволит положить на
//  хранение объект другого типа. Лучше уж получить ошибку на этапе компиляции,
//  чем в процессе выполнения программы.
    public static void main(String[] args) {
//        Box<String> box = new Box<String>("Строка");
//        box.setObj("Строка 2"); // OK
//// box.setObj(10); // Ошибка
//        String s = box.getObj();
//        System.out.println(s); // Строка 2
        Box1<Integer> box = new Box1<Integer>(20);
// box.setObj("Строка 2"); // Ошибка
        box.setObj(10); // OK
        int x = box.getObj();
        System.out.println(x); // 10
    }
}
