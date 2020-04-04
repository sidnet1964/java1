package ru.progwards.java1.lessons.datetime;

import java.time.Instant;
import java.util.Random;

class UserSession {
    //  ----------------------------------------------
    private int sessionHandle;
    private String userName;
//    private ZonedDateTime lastAccess;
    private Instant lastAccess;

    //  конструктор
    public UserSession(String userName) {
        this.userName = userName;
        this.lastAccess = Instant.now();
//          Внутри автоматически сгенерировать sessionHanle
        final Random random = new Random(Instant.now().toEpochMilli());
        this.sessionHandle = random.nextInt();
//        System.out.println("Super - " + userName + " = " + this.sessionHandle);
    }

    //  обновляет время доступа к сессии
    void updateLastAccess() {
        this.lastAccess = Instant.now();
    }

    public int getSessionHandle() {
        return sessionHandle;
    }

    public String getUserName() {
        return userName;
    }

    public Instant getLastAccess() {
        return lastAccess;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "sessionHandle=" + sessionHandle +
                ", userName='" + userName + '\'' +
                ", lastAccess=" + lastAccess +
                '}';
    }
}
