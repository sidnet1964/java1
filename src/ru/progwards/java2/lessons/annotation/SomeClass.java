package ru.progwards.java2.lessons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//  Источник https://geekbrains.ru/posts/java_annotations с доработкой
public class SomeClass {
    @Target(ElementType.TYPE)   //  Аннотация применима только к классам
    @Retention(RetentionPolicy.RUNTIME) //  Применяется во время выполнения программы

//  Это немного искусственный, но зато простой и наглядный пример аннотации на Java.
//  Мы добавили два атрибута, которые выглядят как методы. Отличие в том,
//  что при объявлении атрибутов никогда не используют оператор throws и не назначают параметров.
    public @interface Version {
        float v(); // номер версии
        String author() default  "Аноним"; // автор
    }
    // остальное содержимое класса
    private String name;

    public SomeClass(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        @Version(v = 1.1f)
        // автор остаётся “Анонимом”, а "f" после числа ставим для явного указания на тип float
        //  только как аннотировать SomeClass?
        class Cat{}
        Cat cat = new Cat();

        Class cl = cat.getClass();
        Version an = (Version)cl.getAnnotation(Version.class);

        System.out.println(an.v());

    }
}
