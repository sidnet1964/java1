package ru.progwards.java1.telegrambot;
import org.telegram.telegrambots.ApiContextInitializer;
import ru.progwards.java1.testlesson.ProgwardsTelegramBot;

import java.util.Scanner;

public class PizzaBot extends ProgwardsTelegramBot {
    private final String menu = "У нас есть пицца, напитки и десерт";
    private boolean stop = false;
    @Override
    public String processMessage(String text) {

//        System.out.println(userid);
        checkTags(text);

        if (foundCount() == 1) {
            if (checkLastFound("привет"))
                return "Приветствую тебя, о мой повелитель!\n Что желаешь? " + menu;
            if (checkLastFound("конец"))
                return "Спасибо за заказ.";
            if (checkLastFound("дурак"))
                return "Не надоругаться. Я не волшебник, я только учусь";
            return "Отлично, добавляю в заказ " + getLastFound() + " Желаешь что-то еще?";
        }
        if (foundCount() > 1)
            return "Под твой запрос подходит: \n" + extract() + "Выбери что-то одно, и я добавлю это в заказ.";
        return "Я не понял, возможно у нас этого нет, попробуй сказать по другому. " + menu;
    }
    void test() {
        Scanner in = new Scanner(System.in);
        String input;
        do { input = in.nextLine(); System.out.println(processMessage(input)); }
        while (!stop); in.close(); }

    public static void main(String[] args) {
        System.out.println("Hello bot!");
        ApiContextInitializer.init();

        PizzaBot bot = new PizzaBot();
        bot.username = "Pizza365_bot";
        bot.token = "1043375286:AAFc6J3aGAhkv0RgX7LOs5qO-N0OCixh9L8";

        bot.addTags("привет", "привет, здрасьте, здравствуй, добр, день, вечер, утро, hi, hello");
        bot.addTags("конец", "конец, все, стоп, нет");
        bot.addTags("дурак", "дурак, придурок, идиот, тупой");

        bot.addTags("Пицца гавайская", "гавайск, пицц, ананас, куриц");
        bot.addTags("Пицца маргарита", "маргарит, пицц, моцарелла, сыр, кетчуп, помидор");
        bot.addTags("Пицца пеперони", "пеперони, пицц, салями, колбас, сыр, кетчуп, помидор");

        bot.addTags("Торт тирамису", "десерт, кофе, маскарпоне, бисквит");
        bot.addTags("Торт медовик", "десерт, мед, бисквит");
        bot.addTags("Эклеры", "десерт, заварной, крем, тесто");

        bot.addTags("Кола", "напит, пить, кола");
        bot.addTags("Холодный чай", "напит, пить, чай, липтон, лимон");
        bot.addTags("Сок", "напит, пить, сок, апельсиноый, яблочный, вишневый");

//        bot.start();
        bot.test();

    }
}
