package ru.progwards.sid.A11;

public class TalkThread extends Thread {
    //  в случае extends - функции getState, getPriority вызываютя напрямую
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("TalkThread  - " + getState());
            System.out.println(getPriority());  //  Получить значение приоритета потока
            System.out.println(getThreadGroup());
            try {
                Thread.sleep(7); // остановка на 7 миллисекунд
            } catch (InterruptedException e) {
                System.err.print(e);
            }
        }
    }

    public static void main(String[] args) {
        TalkThread r1 = new TalkThread();
        r1.run();
    }
}
