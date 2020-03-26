package ru.progwards.java1.lessons.maps;

import com.sun.source.tree.IfTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class UsageFrequency {
    List<String> lines;
    public UsageFrequency(){
        lines = new ArrayList<>();
    }

//  загрузить содержимое файла
    public void processFile(String fileName){
            File file = new File(fileName);
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                     lines.add(scanner.nextLine()); //  добавить строку в список
                }
            } catch (FileNotFoundException e) {
                System. out.println("Файл не найден");
            }
//        System.out.println(lines);
    }
//  вернуть Map, который содержит все найденные буквы и цифры
    public Map<Character, Integer> getLetters(){
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (String list1 : lines) {
            try(Scanner scanner = new Scanner(list1)) {
                while (scanner.hasNext()) {
                    String word = scanner.next();
//                    System.out.println(word);
                    char[] letter = word.toCharArray(); //  разложить на символы
                    for (char simbol : letter) {
                        if (!(Character.isDigit(simbol) || Character.isLetter(simbol)))
                            continue;
                        Integer value = hashMap.get(simbol);
                        if (value == null)  //  такого слова еще нет
                            hashMap.put(simbol, 1);
                        else
                            hashMap.put(simbol, ++value);
                    }
                }
            }
        }
        return hashMap;
    }
//  вернуть Map, который содержит все найденные слова
    public Map<String, Integer> getWords(){
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String list1 : lines) {
            try(Scanner scanner = new Scanner(list1)) {
                while (scanner.hasNext()) {
                    String word = scanner.next();
//                    System.out.println(word);
                    if (word.length() == 1){
                        char[] letter = word.toCharArray(); //  преобразовать в символ
                        if (!(Character.isDigit(letter[0]) || Character.isLetter(letter[0])))
                            continue;
                    }
                    Integer value = hashMap.get(word);
                    if (value == null)  //  такого слова еще нет
                        hashMap.put(word, 1);
                    else
                        hashMap.put(word, ++value);
                }
            }
        }
        return hashMap;
    }
    public static void main(String[] args) {
        UsageFrequency list0 = new UsageFrequency();
        list0.processFile("C:\\Users\\sidnet1964\\IdeaProjects\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\maps\\file1.txt");
        System.out.println(list0.getWords());
        System.out.println(list0.getLetters());
    }
}
