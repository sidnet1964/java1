package ru.progwards.java1.lessons.datetime;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Random;

class UserSession {
    //  ----------------------------------------------
    private int sessionHandle;
    private String userName;
    private ZonedDateTime lastAccess;

    //  конструктор
    public UserSession(String userName) {
        this.userName = userName;
        this.lastAccess = ZonedDateTime.now();
//          Внутри автоматически сгенерировать sessionHanle
        final Random random = new Random(Instant.now().getEpochSecond());
        this.sessionHandle = random.nextInt();
    }

    //  обновляет время доступа к сессии
    void updateLastAccess() {
        this.lastAccess = ZonedDateTime.now();
    }

    public int getSessionHandle() {
        return sessionHandle;
    }

    public String getUserName() {
        return userName;
    }

    public ZonedDateTime getLastAccess() {
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
