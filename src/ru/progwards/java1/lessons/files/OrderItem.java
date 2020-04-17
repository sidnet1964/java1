package ru.progwards.java1.lessons.files;

public class OrderItem {
    public String googsName;    // - наименование товара
    public int count;           // - количество
    public double price;        // - цена за единицу

    public OrderItem() {
    }

    public OrderItem(String googsName, int count, double price) {   //  конструктор
        this.googsName = googsName;
        this.count = count;
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "googsName='" + googsName + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }

    public String getGoogsName() {
        return googsName;
    }

    public int getCount() {
        return count;
    }

    public double getPrice() {
        return price;
    }
    public double getSumma() {
        return price * count;
    }
}
