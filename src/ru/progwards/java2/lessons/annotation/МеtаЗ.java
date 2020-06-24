package ru.progwards.java2.lessons.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

//Объявление типа аннотации, включая значения 11 ее членов по умолчанию
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno {
    String str() default "Тестирование";
    int val() default 9000;
}
public class МеtаЗ {
    // аннотировать метод, используя значения по умолчанию
    @MyAnno()
    public static void myMeth() {
        МеtаЗ ob = new МеtаЗ();
        //  получить аннотацию для данного метода и вывести значения ее членов
        try {
            Class<?> с = ob.getClass();
            Method m = с.getMethod("myMeth");
            MyAnno anno = m.getAnnotation(MyAnno.class);
            System.out.println(anno.str() + " " + anno.val());
        } catch (NoSuchMethodException ехс) {
            System.out.println("Meтoд не найден.");
        }
    }

    public static void main(String args[]) {
        myMeth();
    }
}
