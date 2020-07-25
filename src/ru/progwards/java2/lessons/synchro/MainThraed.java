package ru.progwards.java2.lessons.synchro;

class MainThraed {
    private boolean stop = false;

    public void setStop() {
        stop = true;
    }

    public boolean isStop() {
        return stop;
    }
}
