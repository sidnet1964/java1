package ru.progwards.java1.lessons.queues;

public class Order {
    private double sum;
    private int num;
    public int level;  //  приоритет заказа
    public static int max_num = 0;  //  автонумерация

    public Order(double sum) { //  Alt+Insert
        this.sum = sum;
        this.num = ++ max_num;
        if (sum < 10_000)
            level = 3;
        else if (sum < 20_000)
            level = 2;
        else
            level = 3;
    }

    public double getSum() {
        return sum;
    }
    public int getNum() {
        return num;
    }
    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "{" +
                "sum=" + sum +
                ", num=" + num +
                '}';
    }
}
