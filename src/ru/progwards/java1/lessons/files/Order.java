package ru.progwards.java1.lessons.files;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    public String shopId;       // - идентификатор магазина     AAA - обязательные 3 символа
    public String orderId;      // - идентификатор заказа       999999 - обязательные 6 символов
    public String customerId;   // - идентификатор покупателя   ZZZZ - обязательные 4 символа
    public LocalDateTime datetime;  // - дата-время заказа (из атрибутов файла - дата последнего изменения)
    public List<OrderItem> items;   // - список позиций в заказе, отсортированный по наименованию товара
    public double sum;              // - сумма стоимости всех позиций в заказе

    public Order() {
    }

    public Order(String shopId, String orderId, String customerId, LocalDateTime datetime,
                 List<OrderItem> items, double sum) {   //  конструктор
        this.shopId = shopId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.datetime = datetime;
        this.items = items;
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Order{" +
                "shopId='" + shopId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", datetime=" + datetime +
                ", sum=" + sum +
                '}';
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public String getShopId() {
        return shopId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getSum() {
        return sum;
    }

}
