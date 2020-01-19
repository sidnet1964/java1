package ru.progwards.sid.figures_book;

public class Boeing737Demo {
    public static void main(String[] args) {
        for (int i = 1; i < 6; i++) {
            Boeing737.Drawing drawing = new Boeing737.Drawing(i);
            System.out.println(drawing);
        }
    }
}
