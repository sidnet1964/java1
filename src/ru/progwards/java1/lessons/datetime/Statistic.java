package ru.progwards.java1.lessons.datetime;

//  объект для сбора статистики
class Statistic {
    public String sectionName;  //  имя секции
    public long timeIn;         //  начало выполнения
    public long timeOut;        //  конец выполнения (факультативно)
    public int fullTime;        //  полное время выполнения секции в миллисекундах.
    public int subrTime;        //  время выполнения вложенных секций в миллисекундах.
    public int selfTime;        //  чистое время выполнения секции в миллисекундах.
    public int count;           //  количество вызовов. В случае, если вызовов более одного ...

    Statistic(String sectionName){      //, long timeIn
        this.sectionName = sectionName;
//        this.timeIn = 0;
//        this.timeOut = 0;
//        this.count = 1;
    }

    @Override
    public String toString() {
        return "Stat{" +
                "Name='" + sectionName + '\'' +
//                ", timeIn=" + timeIn +
//                ", timeOut=" + timeOut +
                ", fullTime=" + fullTime +
                ", subrTime=" + subrTime +
                ", selfTime=" + selfTime +
                ", count=" + count +
                '}';
    }
//  запомнить начальное время
    public void init(long instantIn){
        this.timeIn = instantIn;
    }
    //  провести расчеты по времени выполнения
    public void update(long instantOut){
        this.timeOut = instantOut;
        this.selfTime = (int)(timeOut - timeIn);
        this.fullTime += (int)(timeOut - timeIn);
//        this.selfTime = this.fullTime;
        this.count ++;
//        System.out.println("update = " + this.fullTime);
    }
    //  обновить вызывающую секцию - this
    public void under(Statistic stat){
//        this.selfTime = this.fullTime - stat.fullTime;
        this.subrTime += stat.selfTime;
    }
}
