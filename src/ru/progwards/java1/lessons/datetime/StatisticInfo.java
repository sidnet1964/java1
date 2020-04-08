package ru.progwards.java1.lessons.datetime;

class StatisticInfo{
    public String sectionName;  //  имя секции
    public int fullTime;        //  полное время выполнения секции в миллисекундах.
    public int selfTime;        //  чистое время выполнения секции в миллисекундах.
    public int count;           //  количество вызовов. В случае, если вызовов более одного ...

    public StatisticInfo(String sectionName, int fullTime, int selfTime, int count) {
        this.sectionName = sectionName;
        this.fullTime = fullTime;
        this.selfTime = selfTime;
        this.count = count;
    }

    @Override
    public String toString() {
        return  "sectionName='" + sectionName + '\'' +
                ", fullTime=" + fullTime +
                ", selfTime=" + selfTime +
                ", count=" + count +
                '}';
    }
}
