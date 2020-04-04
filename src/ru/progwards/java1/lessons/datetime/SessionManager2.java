package ru.progwards.java1.lessons.datetime;

import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class SessionManager2 {
    private int sessionValid;   //  период валидности сессии в секундах
    private Map<Integer, UserSession> sessionsIn;   //  ключ - int sessionHandle;
    private Map<String, UserSession> sessionsSt;    //  ключ - String userName;
//  конструктор
    public SessionManager2(int sessionValid){
        this.sessionValid = sessionValid;
        this.sessionsIn = new TreeMap<>();
        this.sessionsSt = new TreeMap<>();
    }
//  добавляет новую сессию пользователя (в коллекцию)
    public void add(UserSession userSession){
        sessionsIn.put(userSession.sessionHandle, userSession);
        sessionsSt.put(userSession.userName, userSession);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss:S");
        System.out.println("void add - " + dtf.format(userSession.lastAccess));
    }

//  проверяет наличие существующей сессии по userName
//  Если срок валидности истек, или такой сессии нет, возвращает null.
//  В противном случае возвращает сессию, обновив ее дату доступа.
    public UserSession find(String userName){   //  find    find    find    find    find    find    find    find
        System.out.print("US find - " + userName);
        if  (! sessionsSt.containsKey(userName)) {
            System.out.println(" = null");
            return null;    //  нет такой сессии
        }
        else {
            UserSession curSes = sessionsSt.get(userName);
            System.out.println(" = " + curSes.sessionHandle);
            ZonedDateTime zdt = ZonedDateTime.now();
            Duration dur = Duration.between(curSes.lastAccess, zdt);    //  промежуток с последнего обращения
            int seconds = (int)dur.toSeconds(); //  возвращается long и усекается
            if (this.sessionValid < seconds)
                return null;    //  время истекло
            else {
                curSes.updateLastAccess();
                return curSes;
            }
        }
    }
//  проверяет наличие существующей сессии по хендлу
//  Проверка сессии по хендлу должна работать максимально быстро.
    public UserSession get(int sessionHandle){  //  get     get     get     get     get     get     get     get
        System.out.print("US get - " + sessionHandle);
        if  (! sessionsIn.containsKey(sessionHandle)) {
            System.out.println(" = null");
            return null;    //  нет такой сессии
        }
        else {
            UserSession curSes = sessionsIn.get(sessionHandle);
            System.out.println(" = " + curSes.userName);
            ZonedDateTime zdt = ZonedDateTime.now();
            Duration dur = Duration.between(curSes.lastAccess, zdt);
            int seconds = (int)dur.toSeconds();      //  toSecondsPart();
            if (this.sessionValid < seconds)
                return null;    //  время истекло
            else {
                curSes.updateLastAccess();
                return curSes;
            }
        }
    }
//  удаляет указанную сессию пользователя
        public void delete(int sessionHandle){
            sessionsIn.remove(sessionHandle);
        }
//  удаляет все сессии с истекшим сроком годности
        public void deleteExpired(){
            Collection<UserSession> collection = sessionsSt.values();
            var iterator = collection.iterator();
            while (iterator.hasNext()) {
                UserSession toDel = iterator.next();    //  кандидат на удаление
                if (checkExpired(toDel)) {
                    Integer integerToDel = toDel.sessionHandle;
                    System.out.println("P90 - " + toDel.userName);
                    iterator.remove();
                    sessionsIn.remove(integerToDel);
                }
            }
        }
        public boolean checkExpired(UserSession userSession){
            ZonedDateTime zdt = ZonedDateTime.now();
            Duration dur = Duration.between(userSession.getLastAccess(), zdt);
            int seconds = (int)dur.toSeconds();      //  toSecondsPart();
            if (this.sessionValid < seconds)
                return true;    //  время истекло
            else
                return false;

        }
    //  ----------------------------------------------
        static class UserSession{
        private int sessionHandle;
        private String userName;
        private ZonedDateTime lastAccess;
//  конструктор
        public UserSession(String userName){
            this.userName = userName;
            this.lastAccess = ZonedDateTime.now();
//          Внутри автоматически сгенерировать sessionHanle
            final Random random = new Random(Instant.now().getEpochSecond());
            this.sessionHandle = random.nextInt();
        }
//  обновляет время доступа к сессии
        void updateLastAccess(){
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

    public static void main(String[] args) throws InterruptedException {
//  Создать сессию по userName, для этого
//- сделать find,
//- убедиться что вернется null
//- создать новую сессию
//- добавить используя add
        SessionManager2 smm1 = new SessionManager2(10);
//        for (int i = 100; i < 600; i+=100) {                 //  накачка пассивными сессиями
//            smm1.add(new UserSession(String.valueOf(i)));
//            Thread.sleep(1000);
//        }

        UserSession uss_1 = smm1.find("sidnet");
        if (uss_1 == null) {
            uss_1 = new UserSession("sidnet");
            System.out.println(uss_1);
            smm1.add(uss_1);
        }
//  Вызвать несколько раз get
        smm1.get(uss_1.sessionHandle);
        smm1.get(uss_1.sessionHandle);
        smm1.get(uss_1.sessionHandle);
        System.out.println(smm1.get(uss_1.sessionHandle));
//  Подождать (Thread.sleep) время, большее, чем время валидности
        Thread.sleep(11000);
//  4. Проверить что сессии нет через метод get
        System.out.println("P4 " + smm1.get(uss_1.sessionHandle));
//  5. Создать еще одну сессию
        UserSession uss_2 = smm1.find("sidnet");
        if (uss_2 == null) {
            uss_2 = new UserSession("sidnet");
            System.out.println("P5 " + uss_2);
            smm1.add(uss_2);
        }
//  6. Подождать половину времени валидности сессии
        Thread.sleep(5000);
        System.out.println("P6 " + smm1.sessionsSt.size());
//  7. Создать еще одну сессию
        UserSession uss_3 = smm1.find("sidnet");
        if (uss_3 == null) {
            uss_3 = new UserSession("sidnet");
            System.out.println("P7 " + uss_3);
            smm1.add(uss_3);
        }
//  8. Подождать еще раз половину времени валидности сессии
        Thread.sleep(5000);
        System.out.println("P8 " + smm1.sessionsSt.size());
//  9. Вызвать deleteExpired()
        smm1.deleteExpired();
        System.out.println("P91 = " + smm1.sessionsSt.size());
        System.out.println("P92 = " + smm1.sessionsIn.size());
//Убедиться, что одна сессия удалена, вторая нет
//
//Удалить оставшуюся через метод delete
//
//Убедиться что удаление прошло
    }
}
