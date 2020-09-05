package ru.progwards.sid.A26;

public class Consult extends Consultation {
    public String startS;
    public String durationS;
    public Consult(String mentor, long start, long duration, String student, String comment) {
        super(mentor, start, duration, student, comment);
    }

    public static void main(String[] args) {
        Consultation line1 = new Consultation("mazneff",65700,900,"login_1", "student");
//        Consult line2 = (Consult) line1;
        System.out.println(line1);
//        System.out.println(line2);
    }
}
