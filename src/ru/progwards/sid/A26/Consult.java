package ru.progwards.sid.A26;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Consult extends Consultation {
    public String startD;
    public String startT;
    public String durationS;

    @Override
    public String toString() {
        return "Consult{" +
                "startD='" + startD + '\'' +
                ", startT='" + startT + '\'' +
                ", durationS='" + durationS + '\'' +
                ", mentor='" + mentor + '\'' +
                ", start=" + start +
                ", duration=" + duration +
                ", student='" + student + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    public Consult(String mentor, long start, long duration, String student, String comment) {
        super(mentor, start, duration, student, comment);
//        DateTimeFormatter dtf = DateTimeFormatter.ISO_INSTANT;  //  .ISO_LOCAL_TIME
////            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Instant SecondsAfterEpoch = Instant.ofEpochSecond(this.start);
//        startD = dtf.format(SecondsAfterEpoch); // Output: 15-02-2014 10:48:08 AM
//        System.out.println(Instant.now());
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd.MM.yyyy");  //   HH:mm:ss:S
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm");  //   HH:mm:ss:S
//        System.out.println(dtf.format(Instant.now()));
        ZonedDateTime zdt = SecondsAfterEpoch.atZone(ZoneId.of("Europe/Moscow"));   //  Instant.now()
//        System.out.println(dtf1.format(zdt));
//        System.out.println(dtf2.format(zdt));
        startD = dtf1.format(zdt);
        startT = dtf2.format(zdt);
        durationS = longToString(this.duration);
    }

    //  преобразование секунд в строку "15:00"
    public static String longToString (long second) {
        String minutes = String.format("%02d", second / 3600) + ":" +String.format("%02d", (second - (second / 3600) * 3600) / 60);
        return minutes;
    }

    public static void main(String[] args) {
        Consult line1 = new Consult("mazneff",1599394500,900,"login_1", "student");
        System.out.println(line1);
    }
}
