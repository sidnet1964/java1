package ru.progwards.java2.lessons.reflection;

class Person {
    private String name;
    private int age;
    private boolean sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public boolean getSex() {
        return sex;
    }
    boolean getSex(boolean polit, int child) {
        return sex;
    }
}
