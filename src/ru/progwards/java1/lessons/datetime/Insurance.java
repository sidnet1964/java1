package ru.progwards.java1.lessons.datetime;

import com.sun.source.tree.IfTree;

import java.time.*;
import java.time.format.DateTimeFormatter;

import static ru.progwards.java1.lessons.datetime.Insurance.FormatStyle.*;

//  H16 Домашнее задание
//  начало 30.03.2020 00:50
public class Insurance {
//  Класс должен проверять валидность страховок, например для выезжающих за рубеж.
//  Каждая страховка имеет дату-время начала, и продолжительность.
    private ZonedDateTime start;    //  дата-время начала действия страховки
    private Duration duration;      //  продолжительность действия
    public Insurance(ZonedDateTime start){
        this.start = start;
    }
    public Insurance(String strStart, FormatStyle style){
//  установить дату-время начала действия страховки
//  SHORT соответствует ISO_LOCAL_DATE
//  LONG  - ISO_LOCAL_DATE_TIME
//  FULL - ISO_ZONED_DATE_TIME
//  Для вариантов, когда не задан явно часовой пояс использовать таковой по умолчанию.
        ZonedDateTime zonedDateTime;
        String sStyle;
    switch (style){
        case FULL:  //  2007-12-03T10:15:30+01:00[Europe/Paris]
//            sStyle = "DateTimeFormatter.ISO_ZONED_DATE_TIME";   /// временно
//            ZonedDateTime zdt = Instant.now().atZone(ZoneId.of("Europe/Moscow"));
//            //System.out.println(DateTimeFormatter.ISO_ZONED_DATE_TIME.format(zdt));
//          2019-12-31T08:04:43.9316671+03:00[Europe/Moscow]
            zonedDateTime = ZonedDateTime.parse(strStart);
            this.start = zonedDateTime;
            //System.out.println("Z = " + zonedDateTime);
            break;
        case LONG:  //  ISO_LOCAL_DATE_TIME "2007-12-03T10:15:30"
            LocalDateTime localDateTime = LocalDateTime.parse(strStart, DateTimeFormatter.ISO_LOCAL_DATE_TIME); //  BASIC_ISO_DATE
//  мой вариант преобразования LocalDateTime в ZonedDateTime через зону по умолчанию.
            zonedDateTime = ZonedDateTime.of(localDateTime, Clock.systemDefaultZone().getZone());
            this.start = zonedDateTime;
            //System.out.println("L = " + zonedDateTime);
            break;
        case SHORT: //  ISO_LOCAL_DATE  "2007-12-03"
            LocalDate localDate = LocalDate.parse(strStart, DateTimeFormatter.ISO_LOCAL_DATE);
//  мой вариант преобразования LocalDate в ZonedDateTime через зону по умолчанию и нулевое время.
            zonedDateTime = ZonedDateTime.of(localDate, LocalTime.of(0,0), Clock.systemDefaultZone().getZone());
            this.start = zonedDateTime;
            //System.out.println("T = " + zonedDateTime);
            break;
        }
    }
//  вернуть строку формата "Insurance issued on " + start + validStr,
//  где validStr = " is valid", если страховка действительна на данный момент
//  и " is not valid", если она недействительна.
    @Override
    public String toString() {
        if (checkValid(ZonedDateTime.now()))
            return "Insurance issued on " + start + " is valid";
        else
            return "Insurance issued on " + start + " is not valid";
    }

    public static enum FormatStyle {SHORT, LONG, FULL};
//  установить продолжительность действия страховки
    public void setDuration(Duration duration){
        this.duration = duration;
        //System.out.println(duration);
    }
//   - установить продолжительность действия страховки, задав дату-время окончания.
    public void setDuration(ZonedDateTime expiration){
        this.duration = Duration.between(this.start, expiration);
        //System.out.println(duration);
    }
//  установить продолжительность действия страховки, задав целыми числами количество месяцев, дней и часов.
    public void setDuration(int months, int days, int hours){
        this.duration = Duration.between(this.start, this.start.plusMonths(months).plusDays(days).plusHours(hours));
        //System.out.println(duration);
    }
//  установить продолжительность действия страховки
    public void setDuration(String strDuration, FormatStyle style){
    }
//  проверить действительна ли страховка на указанную дату-время
    public boolean checkValid(ZonedDateTime dateTime){
        //System.out.println();
        if (this.start.compareTo(dateTime) > 0 )    //  начало еще не наступило
            return false;
        if (this.duration == null)
            return true;
        //System.out.println(this.start);
        //System.out.println(dateTime);
        //System.out.println(this.duration);
        Duration durCurrent = Duration.between(this.start, dateTime);
        if (this.duration.compareTo(durCurrent) > 0)
            return true;
        return false;
    }
    public static void main(String[] args) {
//        Insurance str1 = new Insurance("2020-02-29T10:15:30+01:00[Europe/Paris]", FULL);
//        Insurance str2 = new Insurance("2020-02-29T10:10:43.9316671+03:00[Europe/Moscow]", FULL);
        Insurance str3 = new Insurance("2020-04-01T10:00:00", LONG);
//        Insurance str4 = new Insurance("2020-02-29", SHORT);

//        str1.setDuration(1,0,0);
//        str2.setDuration(1,15,0);
        str3.setDuration(Duration.ofHours(48));    //  750 часов
//        str4.setDuration(ZonedDateTime.now());      //  на текущий момент - PT744H32M53

//        System.out.println(str1.checkValid(ZonedDateTime.now()));
//        System.out.println(str2.checkValid(ZonedDateTime.now()));
//        System.out.println(str3.checkValid(ZonedDateTime.now()));
//        System.out.println(str4.checkValid(ZonedDateTime.now()));
//        System.out.println(str1);
//        System.out.println(str2);
        System.out.println(str3);
//        System.out.println(str4);
    }
}
//  2019-12-31T08:04:43.9316671+03:00[Europe/Moscow]