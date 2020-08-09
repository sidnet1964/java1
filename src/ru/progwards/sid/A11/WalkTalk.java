package ru.progwards.sid.A11;

public class WalkTalk {
    public static void main(String[ ] args) {
// новые объекты потоков
        TalkThread talk = new TalkThread();
        Thread walk = new Thread(new WalkRunnable());
// запуск потоков
//        talk.start();
        walk.start();
// WalkRunnable w = new WalkRunnable(); // просто объект, не поток
// w.run(); или talk.run(); // выполнится метод, но поток не запустится!
    }
}