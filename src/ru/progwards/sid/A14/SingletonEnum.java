package ru.progwards.sid.A14;

public enum SingletonEnum {
    INSTANCE(true, 1, "Какая-то строка");
    private boolean boolValue;
    private int intValue;
    public String strValue = "Какая-то строка";
    private SingletonEnum( boolean b, int i, String s) {
        boolValue = b;
        intValue = i;
        strValue = s;
    }

    // переопределяем toString и создаём требуемые геттеры и сеттеры...
@Override
public String toString() {
    return "SingletonEnum{" +
            "boolValue=" + boolValue +
            ", intValue=" + intValue +
            ", strValue='" + strValue + '\'' +
            '}';
}
    public void setBoolValue(boolean boolValue) {
        this.boolValue = boolValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }
    public static void main(String[] args) {
        SingletonEnum singleton1 = SingletonEnum. INSTANCE;
        SingletonEnum singleton2 = SingletonEnum. INSTANCE;
        System. out.println("singleton1: " + singleton1);
        System. out.println("singleton2: " + singleton2);
        singleton1.setStrValue( "Другая строка");
        singleton1.setBoolValue( true);
        singleton2.setIntValue( 55555);
        System. out.println("singleton1: " + singleton1);
        System. out.println("singleton2: " + singleton2);
    }
}