package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrderProcessor {
    public String startPath;    //  начальная папка для хранения файлов
    public List<Order> listO;
    public static double sumOrder;

    public OrderProcessor(String startPath) {   //  конструктор
        this.startPath = startPath;
        listO = new ArrayList<>();
    }

    //  загружает заказы за указанный диапазон дат, с start до finish, обе даты включительно
    public int loadOrders(LocalDate start, LocalDate finish, String shopId) throws IOException {
        //  проверить все три параметра //  shopId (111, 112, 113, 117), имя 111-222222-3333
        int count = 0;  //  количество ошибок
        String pattern;
        if (shopId == null)
            pattern = "glob:**/???-??????-????.csv";
        else
            pattern = "glob:**/" + shopId + "-??????-????.csv";
        List<Path> fList = createList(this.startPath, pattern);    //  получить список всех файлов по шаблону
//        System.out.println(fList);
        Iterator<Path> it1 = fList.iterator();
        while (it1.hasNext()) {
            Path file = it1.next();    //  файл для загрузки
            String name = file.getFileName().toString();    //  имя файла
            String shop = name.substring(0,3);
            String orde = name.substring(4,10);
            String cust = name.substring(11,15);
//            System.out.println(Files.getAttribute(file, "basic:lastModifiedTime").toString());
            String last = Files.getAttribute(file, "basic:lastModifiedTime").toString();
            //  2020-04-12T14:41:06
            //  0123456789012345678
            LocalDate lastDate = checkLastDate(last.substring(0, 10));
            LocalDateTime lastTime = checkLastTime(last.substring(0, 10)+" "+last.substring(11, 19));
            if (checkOrders(start, finish, lastDate)) {
                System.out.println(file + " + " + last);
                sumOrder = 0d;   //  сумма товаров по текущему заказу
                //  загружаем текущий файл
                List<OrderItem> oList = orderItem(file);
                if (oList == null){
                    count++;
                    continue;
                }
                listO.add(new Order(shop, orde, cust, lastTime, oList, sumOrder));
            }
        }
        System.out.println("listO.size() = " + listO.size());
        System.out.println(listO);
        return count;
    }
    //  -----------------------------------
    //  обработка содержимого одного файла
    public List<OrderItem> orderItem(Path file) throws IOException {
        String googsName = "";
        int count = 0;
        double price= 0d;
        List<OrderItem> oList = new ArrayList<>();
        List<String> allLines = Files.readAllLines(file);
        Iterator<String> it1 = allLines.iterator();
        boolean good = true;
        while (it1.hasNext()) {
            //  разобрать строку на части
            try(Scanner scanner = new Scanner(it1.next()).useDelimiter("\\s*,\\s*")) {
//                while (scanner.hasNext()) {
//                if (scanner.hasNext())
                    googsName = scanner.next();
//                if (scanner.hasNextInt())
                    count = scanner.nextInt();
//                if (scanner.hasNextDouble())
                    price = scanner.nextDouble();
            } catch (Exception e) {
                System.out.println("Ошибочка вышла!");
                good = false;
                break;
            }
            System.out.println(googsName + " / " + count + " / " + price);
            oList.add(new OrderItem(googsName, count, price));
            sumOrder += count * price;
        }
        if (good)
            return oList;
        else
            return null;
    }
    //  -----------------------------------
    //  проверить, удовлетворяет ли файл условиям по дате   2020-04-03 - дата из атрибутов файла
    public boolean checkOrders(LocalDate start, LocalDate finish, LocalDate lastDate){
//        System.out.println(ld + " ("+start+" = "+finish+")");
        if (lastDate.compareTo(start) >= 0 && lastDate.compareTo(finish) <= 0)
            return true;
        else
            return false;
    }
    //  -----------------------------------
    //  преобразование строки в дату, 2020-04-03 - дата из атрибутов файла
    public LocalDate checkLastDate(String last){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.from(dtf.parse(last)); //  преобразовать строку в дату
        return localDate;
    }
    public LocalDateTime checkLastTime(String last){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localTime = LocalDateTime.from(dtf.parse(last)); //  преобразовать строку в дату
        return localTime;
    }
    //  -----------------------------------
    public static List<Path> createList(String startPath, String pattern) throws IOException {
        final Path dir = Paths.get(startPath);
        List<Path> fList = new ArrayList<>();
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(pattern);
        Files.walkFileTree(dir, Collections.emptySet(), 2, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                if (pathMatcher.matches(dir.relativize(path)))
                    fList.add(path);
//                    System.out.println(path);
                return FileVisitResult.CONTINUE;
            }
            @Override
            public FileVisitResult visitFileFailed(Path file, IOException e) {
                return FileVisitResult.CONTINUE;
            }
        });
        return fList;
    }
    public static void main(String[] args) throws IOException {
        OrderProcessor ord1 = new OrderProcessor("C:/Projects/Academy/Java1");
        LocalDate data1 = LocalDate.of(2020, 4, 13);
        LocalDate data2 = LocalDate.of(2020, 4, 19);
        ord1.loadOrders(data1, data2, null);
    }
}
