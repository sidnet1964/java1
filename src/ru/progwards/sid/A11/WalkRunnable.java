package ru.progwards.sid.A11;

//  Интерфейс Runnable не имеет метода start(), а только единственный метод run()
public class WalkRunnable implements Runnable {
    //  в случае implements - функции getState, getPriority вызываютя через Thread.currentThread().
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("WalkRunnable - " + Thread.currentThread().getState());
            System.out.println(Thread.currentThread().getPriority());  //  Получить значение приоритета потока
            System.out.println(Thread.currentThread().getThreadGroup());
            try {
                Thread.sleep(7);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
    }
}
