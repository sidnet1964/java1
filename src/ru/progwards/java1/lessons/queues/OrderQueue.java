package ru.progwards.java1.lessons.queues;

import java.util.Comparator;
import java.util.PriorityQueue;

public class OrderQueue {
    //  PriorityQueue - сортированная очередь
    private PriorityQueue<Order> priQueue;

    //  конструктор
    public OrderQueue() {
        PriorityQueue<Order> priQueue = new PriorityQueue<Order>(comparator);
    }
    //  компаратор для очереди
    Comparator<Order> comparator = new Comparator<Order>() {
        @Override
        public int compare(Order o1, Order o2) {
            return o1.getLevel() - o2.getLevel();
        }
    };

    //  размещающий заказы в очередь с приоритетом, разбивая их по 3-м классам
    public void add(Order order) {
        try {
            priQueue.offer(order);
        } catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

//  возвращающий первый заказ в очереди для обслуживания
    public Order get() {
        if  (priQueue.isEmpty())
            return null;
        else
            return priQueue.poll();
    }

    public static void main(String[] args) {

        int sum_zak = 4000;  //  сумма заказа
        OrderQueue orderQueue = new OrderQueue();   //  создать объект основного класса
        Order order1 = new Order(1000);
        orderQueue.add(order1);
        orderQueue.add(order1);
//        orderQueue.add(new Order(sum_zak += 4000));
//        orderQueue.add(new Order(sum_zak += 4000));
//        orderQueue.add(new Order(sum_zak += 4000));
//        orderQueue.add(new Order(sum_zak += 4000));
//        orderQueue.add(new Order(sum_zak += 4000));
//        orderQueue.add(new Order(sum_zak += 4000));

        Order ord;
        while ((ord = orderQueue.get()) != null) {
            System.out.println(ord.getNum());
        }
    }
}
