package ru.progwards.sid.test;

public class daysRemainder {
    public static void main(String[] args) {
        float DAYS_IN_ASTRO_YEAR = 365.24219878f;
        float DAYS_IN_WEEK = 7;
        float daysRemainder = DAYS_IN_ASTRO_YEAR % DAYS_IN_WEEK;
        double d = 1.2e3;
        float f =1.2f;
        System.out.println(daysRemainder);
//  1.2421875   float % float
//  1.2421875   float % int
    }
}
//  https://github.com/sidnet1964/java1.git}
