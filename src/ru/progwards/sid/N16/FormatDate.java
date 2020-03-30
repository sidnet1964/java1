package ru.progwards.sid.N16;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;

public class FormatDate {
    static ZonedDateTime parseZDT(String str){
        String pattern = "dd.MM.yyyy HH:mm:ss.SSS Z zzzz";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern, Locale.US);
        TemporalAccessor ta = dtf.parse(str);
        LocalDateTime ldt = LocalDateTime.from(ta);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("Europe/Moscow"));
        return zdt;
    }
    public static void main(String[] args) {
        System.out.println(parseZDT("01.01.2020 16:27:14.444 +0300 Moscow Standard Time"));
//  2020-01-01T16:27:14.444+03:00[Europe/Moscow]
//  2020-01-01T16:27:14.444+03:00[Europe/Moscow]
//        System.out.println(parseZDT("01.01.2020 16:27:14.444 +0300 Moscow Standard Time"));
        /// S       fraction-of-second
        /// Z       zone-offset     +0000
        /// z       time-zone name  Pacific Standard Time; PST
////  слайд 4
//    SimpleDateFormat format = new SimpleDateFormat("'отъезд' EEEE dd MMMM 'в' ha");
//    System.out.println(format.format(new Date()));
////        DateTimeFormatter dtf =
////                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss:S");
//        OffsetDateTime odt = Instant.now().atOffset(ZoneOffset.ofHours(3));
////        System.out.println(dtf.format(odt));
//        System.out.println(odt);
    }
}
