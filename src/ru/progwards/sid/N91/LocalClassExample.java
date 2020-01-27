package ru.progwards.sid.N91;
//  http://easy-code.ru/lesson/local-anonymous-nested-classes-java
public class LocalClassExample {
    static String regularExpression = "[^0-9]"; // поле класса

    public static void validatePhoneNumber(String phoneNumber1, String phoneNumber2) {
        int numberLength = 10;

        //  класс PhoneNumber, который объявлен в статическом методе vaidatePhoneNumber,
        //  может обращаться только к статическим полям внешнего класса
        //  ------------------------------------------------
        class PhoneNumber {
            //  Локальные классы не могут содержать статические поля или методы
            //  нельзя объявить интерфейс внутри метода — интерфейсы статичны
            //  Локальный класс может содержать статические поля, только если они константы
            String formattedPhoneNumber = null;

            PhoneNumber(String phoneNumber){
                // numberLength = 7;
                //  конструктор обращается к полю LocalClassExample.regularExpression (static)
                String currentNumber = phoneNumber.replaceAll(regularExpression, "");
                //  конструктор имеет доступ к локальной переменной numberLength (final)
                if (currentNumber.length() == numberLength)
                    formattedPhoneNumber = currentNumber;
                else
                    formattedPhoneNumber = null;
            }

            public String getNumber() {
                return formattedPhoneNumber;
            }

            //  если вы объявляете локальный класс в теле метода, он имеет доступ к параметрам этого метода
            //  Метод ниже обращается к параметрам phoneNumber1 и phoneNumber2 функции validatePhoneNumber
            public void printOriginalNumbers() {
                System.out.println("Original nubmers are " + phoneNumber1 + " and " + phoneNumber2);
            }
        }
        //  ------------------------------------------------

        PhoneNumber myNumber1 = new PhoneNumber(phoneNumber1);
        PhoneNumber myNumber2 = new PhoneNumber(phoneNumber2);

        // Valid in JDK 8 and later:
        myNumber1.printOriginalNumbers();

        if (myNumber1.getNumber() == null)
            System.out.println("First number is invalid");
        else
            System.out.println("First number is " + myNumber1.getNumber());
        if (myNumber2.getNumber() == null)
            System.out.println("Second number is invalid");
        else
            System.out.println("Second number is " + myNumber2.getNumber());
    }

    public static void main(String... args) {
        validatePhoneNumber("978-828-60-24", "456-7890");
    }
}