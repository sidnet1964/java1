package ru.progwards.sid.N15;

import java.util.HashMap;
import java.util.Map;

public class HashMapSimple {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Ivanov1", "Иванов Иван Иванович");
        hashMap.put("student1", "Студентов Ученик Изучаевич");
        hashMap.put("UmnikRD", "Умников Раз Думович");
        hashMap.put("tormoz_dk", "Тормозов Диск Колодкович");
        hashMap.put("Student2", "Студентов Ученик Изучаевич");
        String key = "Student2";
        hashMap.put("Student2", "Студентов Студент Студентович");   //  замена значения
        String fio = hashMap.get(key);
        System.out.println("По ключу " + key + " найдено значение: " + fio);
        String udal = hashMap.remove("Student2");     //  удаление записей с возвратом
        hashMap.remove("Student3");
        System.out.println(udal);
        for(Map.Entry<String, String> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
//            System.out.println(entry);  //  tormoz_dk=Тормозов Диск Колодкович
        }
    }
}
