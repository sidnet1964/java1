package ru.progwards.sid.A26;

public class Consultation {
    public final String mentor;
    public final long start;
    public final long duration;
    public final String student;
    public final String comment;

    public Consultation(String mentor, long start, long duration, String student, String comment) {
        this.mentor = mentor;
        this.start = start;
        this.duration = duration;
        this.student = student;
        this.comment = comment;
    }

    public String getMentor() {
        return mentor;
    }

    public long getStart() {
        return start;
    }

    public long getDuration() {
        return duration;
    }

    public String getStudent() {
        return student;
    }

    public String getComment() {
        return comment;
    }
}
