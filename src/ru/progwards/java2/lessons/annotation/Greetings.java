package ru.progwards.java2.lessons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

//  Аннотация @Retention
//Предназначена для применения только в качестве аннотации к другим аннотациям.
//Она определяет правило удержания, как пояснялось ранее в этой главе.
//Прежде чем продолжить рассмотрение аннотаций, следует обсудить правила
//удержания аннотачий. Правила удержания определяют момент, когда аннотация
//отбрасывается. В Java определены три таких правила, инкапсулированные в перечисление
//java. lang. annotation. RetentionPolicy. Это правила SOURCE, CLASS и RUNTIME.
//Аннотации по правилу удержания RUNTIME сохраняются в файле с расширением .class во время
//компиляции и остаются доступными для виртуальной машины JVM во время выполнения.
@Retention(RetentionPolicy.RUNTIME)
//Аннотация @Target
//Задает типы элементов, к которым можно применять аннотацию. Она предназначена
//для применения только в качестве аннотации к другим аннотациям. Аннотация @Target
// принимает один аргумент, который должен быть константой из перечисления ElementType.
// Этот аргумент задает типы объявляемых элементов, к которым можно применять аннотацию.
@Target(ElementType.METHOD)
@interface AnnotationTest {
    String text() default "Всегда говори привет";
}
public class Greetings {
    void hello() {}
    void goodday() {}
    void goodnight() {}
    void hi() {}

    @AnnotationTest
    public static void printAnnotation() {
        Greetings ob = new Greetings();
        //  получить аннотацию для данного метода и вывести значения ее членов
        try {
            Class<?> obClass = ob.getClass();
            Method classMethod = obClass.getMethod("hello");
            AnnotationTest anno = classMethod.getAnnotation(AnnotationTest.class);
            System.out.println(anno.text());
        } catch (NoSuchMethodException ехс) {
            System.out.println("Meтoд не найден.");
        }
    }
    public static void main(String[] args) {
        printAnnotation();
    }
}
