package ru.progwards.java1.lessons.datetime;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;

public class SessionManager {
    private int sessionValid;
//    private TreeSet<UserSession> sessions;  //  вариант - TreeMap()
    private Map<String, UserSession> sessions;
//  конструктор
    public SessionManager(int sessionValid){
        this.sessionValid = sessionValid;
//        sessions = new TreeSet<>();
        sessions = new TreeMap<>();
    }
//  добавляет новую сессию пользователя
    public void add(UserSession userSession){
        String userName = userSession.userName;
        sessions.put(userName, userSession);
    }
//  проверяет наличие существующей сессии по userName
    public UserSession find(String userName){
        if  (! sessions.containsKey(userName))
            sessions.put(userName, new UserSession(userName));
        return sessions.get(userName);
    }
        //  ----------------------------------------------
    class UserSession{
        private int sessionHandle;
        private String userName;
        private ZonedDateTime lastAccess;
//  конструктор
        public UserSession(String userName){
            this.userName = userName;
            this.lastAccess = ZonedDateTime.now();
//          Внутри автоматически сгенерировать sessionHanle
            Instant instant = Instant.now();
            final Random random = new Random(instant.getEpochSecond());
            this.sessionHandle = random.nextInt();
        }
        void updateLastAccess(){

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
    }
}
