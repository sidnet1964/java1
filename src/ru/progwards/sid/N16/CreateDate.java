package ru.progwards.sid.N16;

import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.lang.System.*;

public class CreateDate {
//  Напишите метод с сигнатурой Date createDate(), который возвращает дату
//  - 28 февраля 1986, 0 часов, 0 минут, 0 секунд, 0 миллисекунд
    static Date createDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1986, 1, 28, 0,0,0);
        return calendar.getTime();
    }
    static void createTimeDate() {
        Instant instant = Instant.now();
        out.println(instant.getEpochSecond());   //  Получает количество секунд из эпохи Java
        out.println(instant.toEpochMilli());     //  - то же, но в милисекундах
        out.println(instant.getNano());          //  Получает число наносекунд от начала секунды.
//        out.println(instant.atZone(ZoneId.of("UTC+4")));
        out.println(instant.toString());
        out.println(instant);
//        NANO_OF_SECOND
//        MICRO_OF_SECOND
//        MILLI_OF_SECOND
//        INSTANT_SECONDS
//        TemporalField temporalField = ChronoField.NANO_OF_SECOND;
//        out.println(instant.get(temporalField));
//  ZoneId - часовой пояс
//        ZoneId zid1 = ZoneId.of("Europe/Moscow");
//        out.println(zid1.getRules().getOffset(Instant.now()));
//        out.println(zid1.getRules().isDaylightSavings(Instant.now()));
//        ZoneId zid2 = ZoneId.of("UTC+4");
//        out.println(zid2.getDisplayName(TextStyle.FULL, Locale.getDefault()));
//  ZonedDateTime
//        Instant instant = Instant.now();
        ZonedDateTime zdt = instant.atZone(ZoneId.of("Europe/Moscow"));
        out.println(zdt);
        DayOfWeek dow = zdt.getDayOfWeek();
        out.println(dow);
        out.println(zdt.getYear());
//
        LocalDateTime ldt1= LocalDateTime.now();
        LocalDateTime ldt2= ldt1.plusDays(4);
        Duration duration = Duration.between(ldt1,ldt2);
        System.out.println(duration.toHours());

        ldt2= LocalDateTime.of(2019, 05, 05, 22, 24);
        System.out.println(ldt2);

        ZoneId zid1 = ZoneId.of("Europe/Moscow");
        System.out.println(zid1.getRules().getOffset(Instant.now()));
    }
//  соответствующий 1 января 2020 года, 15 часов и одна наносекунда по Московскому времени
    static Instant createInstant(){
        LocalDateTime ldt = LocalDateTime.of(2020, 01, 01, 15, 0,0,1);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("Europe/Moscow"));
//        System.out.println(zdt);
        Instant i1 = Instant.from(zdt);
        return i1;
    }
    public static void main(String[] args) {
//        out.println(createDate());
//        createTimeDate();
        out.println(createInstant());
    }
}
