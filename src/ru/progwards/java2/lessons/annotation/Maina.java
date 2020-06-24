package ru.progwards.java2.lessons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//  Источник http://blog.harrix.org/article/7231
public class Maina {
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Animal {
        int massa() default 3;
    }

    public static void main(String[] args) {

        @Animal()
        class Cat {
        }

        Cat a = new Cat();

        Class cl = a.getClass();
        Animal an = (Animal)cl.getAnnotation(Animal.class);

        System.out.println(an.massa());
    }
}
