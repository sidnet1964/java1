package ru.progwards.sid.files_copy;

//import java.io.IOException;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class OrderProcessor {
    public String startPath;    //  начальная папка для хранения файлов
    public List<Order> listOrder;
    public static double sumOrder;  //  для временного суммирования по одной накладной

    public OrderProcessor(String startPath) {   //  конструктор
        this.startPath = startPath;
        listOrder = new ArrayList<>();
    }

    //  загружает заказы за указанный диапазон дат, с start до finish, обе даты включительно
    public int loadOrders(LocalDate start, LocalDate finish, String shopId) {
        //  проверить все три параметра //  shopId (111, 112, 113, 117), имя 111-222222-3333
        int count = 0;  //  количество ошибок
        String pattern;
        if (shopId == null)
            pattern = "glob:**/???-??????-????.csv";
        else
            pattern = "glob:**/" + shopId + "-??????-????.csv";
        List<Path> fList = createList(this.startPath, pattern);    //  получить список всех файлов по шаблону
//        System.out.println(fList);
        //  файл для загрузки
        for (Path file : fList) {
            String name = file.getFileName().toString();    //  имя файла
            String shop = name.substring(0, 3);
            String orde = name.substring(4, 10);
            String cust = name.substring(11, 15);
            String last = null;
            try {
                last = Files.getAttribute(file, "basic:lastModifiedTime").toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //  2020-04-12T14:41:06
            //  0123456789012345678
//            LocalDate lastDate = checkLastDate(last.substring(0, 10));
            LocalDateTime lastTime = checkLastTime(last.substring(0, 10) + " " + last.substring(11, 19));
            LocalDate lastDate = lastTime.toLocalDate();
            if (checkOrders(start, finish, lastDate)) {
                sumOrder = 0d;   //  сумма товаров по текущему заказу
                //  загружаем текущий файл
                List<OrderItem> oList = orderItem(file);
                if (oList == null) {
                    count++;
                    continue;
                }
                listOrder.add(new Order(shop, orde, cust, lastTime, oList, sumOrder));
            }
        }
        return count;
    }
    //  -----------------------------------
    //  обработка содержимого одного файла
    public List<OrderItem> orderItem(Path file) {
        String googsName = "";
        int count = 0;
        double price= 0d;
        List<OrderItem> oList = new ArrayList<>();
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Iterator<String> it1 = allLines.iterator();
        boolean good = true;
        while (it1.hasNext()) {
            //  разобрать строку на части
            try(Scanner scanner = new Scanner(it1.next()).useDelimiter("\\s*,\\s*")) {
                googsName = scanner.next();
                count = scanner.nextInt();
                price = scanner.nextDouble();
            } catch (Exception e) {
                System.out.println("Ошибочка вышла!");
                good = false;
                break;
            }
            oList.add(new OrderItem(googsName, count, price));
            sumOrder += count * price;
        }
        if (good) {
            oList.sort((a, b) -> a.getGoogsName().compareTo(b.getGoogsName()));
            return oList;
        }
        else
            return null;
    }
    //  -----------------------------------
    //  проверить, удовлетворяет ли файл условиям по дате   2020-04-03 - дата из атрибутов файла
    public boolean checkOrders(LocalDate start, LocalDate finish, LocalDate lastDate){
        boolean left = false, right = false;
//        System.out.println("(01) " + "checkOrders - " + lastDate + " ("+start+" = "+finish+")");
        if (start == null)
            left = true;
        else
            left = (lastDate.compareTo(start) >= 0);
        if (finish == null)
            right = true;
        else
            right = (lastDate.compareTo(finish) <= 0);
        return left && right;
    }
    //  -----------------------------------
    //  преобразование строки в дату, 2020-04-03 - дата из атрибутов файла
    public LocalDate checkLastDate(String last){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.from(dtf.parse(last));
    }
    public LocalDateTime checkLastTime(String last){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.from(dtf.parse(last));
        ZonedDateTime zdtAtDefault = ldt.atZone( ZoneOffset.ofHours(0) );     //Local time in Asia timezone
        ZoneId zoneId = ZoneId.of( "Europe/Moscow" );        //Zone information
        ZonedDateTime zdtAtCurrent = zdtAtDefault.withZoneSameInstant( zoneId );
//        LocalDateTime ldtX = zdtAtCurrent.toLocalDateTime();
        return zdtAtCurrent.toLocalDateTime();

//        return LocalDateTime.from(dtf.parse(last));
    }
    //  -----------------------------------
    public static List<Path> createList(String startPath, String pattern){
        final Path dir = Paths.get(startPath);
        List<Path> fList = new ArrayList<>();
        try {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(pattern);
//        Files.walkFileTree(dir, Collections.emptySet(), 2, new SimpleFileVisitor<Path>() {
        Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
//            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                if (pathMatcher.matches(dir.relativize(path)))
                    fList.add(path);
                return FileVisitResult.CONTINUE;
            }
            @Override
            public FileVisitResult visitFileFailed(Path file, IOException e) {
                return FileVisitResult.CONTINUE;
            }
        });
        } catch (IOException e){
            System.out.println(e);
        }
        return fList;
    }
    //  3.5 -----------------------------------
    //  выдать список заказов в порядке обработки (отсортированные по дате-времени),
    //  для заданного магазина. Если shopId == null, то для всех
    public List<Order> process(String shopId){
        //  1 - отбор, 2 - сортировка
        //  компаратор для сортировки
        List<Order> processList = new ArrayList<>();
        if (shopId == null)
            processList.addAll(listOrder);
        else
            listOrder.forEach(orders -> {
            if (orders.shopId.equals(shopId))
                processList.add(orders);
            });
        //  работают оба варианта
        processList.sort((a,b)->a.getDatetime().compareTo(b.getDatetime()));
//        Collections.sort(processList, (a,b)->a.getDatetime().compareTo(b.getDatetime()));
//        System.out.println(processList);
        return processList;
    }
    //  3.6 -----------------------------------
    //  выдать информацию по объему продаж по магазинам (отсортированную по ключам)
    public Map<String, Double> statisticsByShop(){
        Map<String, Double> mapByShop =
            listOrder.stream().collect(Collectors.groupingBy(Order::getShopId, Collectors.summingDouble(Order::getSum)));
        Map<String, Double> mapByShopSort = new TreeMap<>(mapByShop);
//        System.out.println(mapByShop);
        return mapByShopSort;
    }
    //  3.7 -----------------------------------
    //   - выдать информацию по объему продаж по товарам (отсортированную по ключам)
    public Map<String, Double> statisticsByGoods(){
        //  собрать все стороки накладных в единый список
        List<OrderItem> listOrderItem = new ArrayList<>();
        for (Order oneOrder : listOrder) {
            listOrderItem.addAll(oneOrder.items);
        }
//        System.out.println(listOrderItem);
        Map<String, Double> mapByGoods =
            listOrderItem.stream().collect(Collectors.groupingBy(OrderItem::getGoogsName, Collectors.summingDouble(OrderItem::getSumma)));
//        System.out.println(mapByGoods);
        Map<String, Double> mapByGoodsSort = new TreeMap<>(mapByGoods);
        return mapByGoodsSort;
    }
    //  3.7 -----------------------------------
    //  выдать информацию по объему продаж по дням (отсортированную по ключам)
    public Map<LocalDate, Double> statisticsByDay(){
        Map<LocalDate, Double> mapByDay =
                listOrder.stream().collect(Collectors.groupingBy(Order::getDate, Collectors.summingDouble(Order::getSum)));
        Map<LocalDate, Double> mapByDaySort = new TreeMap<>(mapByDay);
//        System.out.println(mapByShop);
        return mapByDaySort;
    }

    //  =========================================
    public static void main(String[] args){
        OrderProcessor ord1 = new OrderProcessor("C:/Projects/Academy/Java1");
        LocalDate data1 = LocalDate.of(2020, 4, 13);
        LocalDate data2 = LocalDate.of(2020, 4, 19);
//        ord1.loadOrders(data1, data2, null);    //  загрузить информацию о продажах
        ord1.loadOrders(null, null, null);    //  загрузить информацию о продажах
        //  результат поместить в List<Order> listOrder, содержащий List<OrderItem> items и double sum
        List<Order> listO = ord1.process(null);
        System.out.println(listO);
        WriteExcel book1 = new WriteExcel();    //  новая книга (объект)
        book1.writeSheet(listO);
        //  здесь можно вызвать формирование таблицы для List<Order>
//        System.out.println(ord1.statisticsByShop());
//        System.out.println(ord1.statisticsByGoods());
//        System.out.println(ord1.statisticsByDay());
    }
}
