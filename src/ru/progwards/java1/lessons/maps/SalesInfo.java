package ru.progwards.java1.lessons.maps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SalesInfo {
    List<String> lines;
    List<Client> customers;     //  список клиентов
    public SalesInfo(){
        lines = new ArrayList<>();
        customers = new ArrayList<>();
    }
//  вернуть количество успешно загруженных строк
    class Client{   //  внутренний класс - клиент
        String name;
        String product;
        int qty;        //  количество
        double sum;     //  сумма

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", product='" + product + '\'' +
                ", qty=" + qty +
                ", sum=" + sum +
                '}';
    }

    Client(String name, String product, int qty, double sum){   //  конструктор
        this.name = name;
        this.product = product;
        this.qty = qty;
        this.sum = sum;
    }
}
    public int loadOrders(String fileName){
        String name;    //  повторение структуры класса Client
        String product;
        int qty;        //  количество
        double sum;     //  сумма
        int countLine = 0;  //  счетчик строк
        boolean fullLine;   //  признак полноты
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                fullLine = false;
                String subL = scanner.nextLine();
//                System.out.println(subL);
                try (Scanner scan1 = new Scanner(subL).useDelimiter("\\s*,\\s*")) {
                    if (scan1.hasNext())
                        name = scan1.next();  //  первое поле
                    else continue;   //  пропускаем текущую строку
                    if (scan1.hasNext())
                        product = scan1.next();  //  второе поле
                    else continue;   //  пропускаем текущую строку
                    if (scan1.hasNextInt())
                        qty = scan1.nextInt();  //  3-е поле
                    else continue;   //  пропускаем текущую строку
                    if (scan1.hasNextInt())
                        sum = scan1.nextDouble();  //  4-е поле
                    else continue;   //  пропускаем текущую строку
//  теперь все хорошо - четыре поля сформированы
                    customers.add(new Client(name, product, qty, sum));
                    countLine++;
                }
            }
        } catch (FileNotFoundException e) {
            System. out.println("Файл не найден");
        }
//        System.out.println(customers);
        return countLine;
    }
//  вернуть список товаров, отсортированный по наименованию товара
    public Map<String, Double> getGoods(){
        Map<String, Double> treeMap = new TreeMap<>();
        for (Client c1 : customers){
            Double value = treeMap.get(c1.product);
            if (value == null)  //  такого слова еще нет
                treeMap.put(c1.product, c1.sum);
            else
                treeMap.put(c1.product, value + c1.sum);
        }
        return treeMap;
    }
//  вернуть список покупателей, отсортированный по алфавиту
    public Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers(){
        Map<String, AbstractMap.SimpleEntry<Double, Integer>> treeMap = new TreeMap<>();
        for (Client c1 : customers){
            var value = treeMap.get(c1.name);
//            var entries = hashMap.entrySet();
//            Set<Map.Entry<Integer, String>> entries = hashMap.entrySet();
            if (value == null) {  //  такого слова еще нет
                AbstractMap.SimpleEntry<Double, Integer> order = new AbstractMap.SimpleEntry<Double, Integer>(c1.sum, c1.qty);
                treeMap.put(c1.name, order);
            }
            else{
                Double sum = value.getKey() + c1.sum;
                Integer qty = value.getValue() + c1.qty;
                AbstractMap.SimpleEntry<Double, Integer> order = new AbstractMap.SimpleEntry<Double, Integer>(sum, qty);
                treeMap.put(c1.name, order);
            }
//                treeMap.put(c1.name, value + c1.sum);
        }
        return treeMap;
    }
    public static void main(String[] args) {
        SalesInfo list0 = new SalesInfo();
        int countLine = list0.loadOrders("C:\\Users\\sidnet1964\\IdeaProjects\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\maps\\file1.txt");
        System.out.println(countLine);
        System.out.println(list0.getGoods());
        System.out.println(list0.getCustomers());
    }
}
