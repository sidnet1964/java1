package ru.progwards.java1.lessons.datetime;

import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

//  вариант с одним TreeMap - sessionsIn
public class SessionManager {
    private int sessionValid;   //  период валидности сессии в секундах
    private Map<Integer, UserSession> sessionsIn;   //  ключ - int sessionHandle;
//    private Map<String, UserSession> sessionsSt;    //  ключ - String userName;
//  конструктор
    public SessionManager(int sessionValid){
        this.sessionValid = sessionValid * 1000;    //  храним в милисикундах
        this.sessionsIn = new TreeMap<>();
//        this.sessionsSt = new TreeMap<>();
    }
//  добавляет новую сессию пользователя (в коллекцию)
    public void add(UserSession userSession){
        sessionsIn.put(userSession.getSessionHandle(), userSession);
//        sessionsSt.put(userSession.userName, userSession);
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSS");
        //System.out.println("void add - " + userSession.getLastAccess());
    }

//  проверяет наличие существующей сессии по userName. Если срок валидности истек или такой сессии нет,
//  возвращает null. В противном случае возвращает сессию, обновив ее дату доступа.
    public UserSession find(String userName){   //  find    find    find    find    find    find    find    find
        //  вариант с одним TreeMap - sessionsIn, userName ищется перебором
        //System.out.println("US find - " + userName);
        Collection<UserSession> collection = sessionsIn.values();
        var iterator = collection.iterator();
        while (iterator.hasNext()) {
            UserSession userSession = iterator.next();    //  объект на ПРОВЕРКУ
            if (userSession.getUserName().equals(userName)) {    //  объект обнаружен
                if (checkExpired(userSession))
                    return null;
                else {
                    //System.out.println("US find + " + userSession.getUserName());
                    userSession.updateLastAccess();
                    return userSession;
                }
            }
        }
        return null;    //  цикл завершен, значит не найдено
    }
//  проверяет наличие существующей сессии по хендлу
//  Проверка сессии по хендлу должна работать максимально быстро.
    public UserSession get(int sessionHandle){  //  get     get     get     get     get     get     get     get
        //System.out.println("US get - " + sessionHandle);
        if  (! sessionsIn.containsKey(sessionHandle)) {
            //System.out.println("US get = null");
            return null;    //  нет такой сессии
        }
        else {
            UserSession userSession = sessionsIn.get(sessionHandle);
//            Instant zdt = Instant.now();
//            Duration dur = Duration.between(curSes.getLastAccess(), zdt);
//            int seconds = (int)dur.toSeconds();      //  toSecondsPart();
            //System.out.println("US get -> " + userSession.getUserName());
            if (checkExpired(userSession))
//            if (this.sessionValid < seconds)
                return null;    //  время истекло
            else {
                userSession.updateLastAccess();
                return userSession;
            }
        }
    }
//  удаляет указанную сессию пользователя
        public void delete(int sessionHandle){
            sessionsIn.remove(sessionHandle);
        }
//  удаляет все сессии с истекшим сроком годности
        public void deleteExpired(){
            Collection<UserSession> collection = sessionsIn.values();
            var iterator = collection.iterator();
            while (iterator.hasNext()) {
                UserSession userSession = iterator.next();    //  кандидат на удаление
                if (checkExpired(userSession)) {
                    iterator.remove();
                }
            }
        }
//  проверить валидность сессии по времени
        public boolean checkExpired(UserSession userSession){
            Long zdt = Instant.now().toEpochMilli();  //  текущий момент
//            //System.out.println("checkExpired 1 = " + zdt);
//            //System.out.println("checkExpired 2 = " + userSession.getLastAccess().toEpochMilli());
//            //System.out.println("checkExpired 3 = " + (zdt - userSession.getLastAccess().toEpochMilli()));
//            //System.out.println("checkExpired 4 = " + this.sessionValid);

            if (this.sessionValid < (zdt - userSession.getLastAccess().toEpochMilli()))
                return true;    //  время истекло
            else
                return false;

        }

    public static void main(String[] args) throws InterruptedException {
//  Создать сессию по userName, для этого
//- сделать find,
//- убедиться что вернется null
//- создать новую сессию
//- добавить используя add
        SessionManager smm1 = new SessionManager(1);
//        for (int i = 100; i < 600; i+=100) {                 //  накачка пассивными сессиями
//            smm1.add(new UserSession(String.valueOf(i)));
//            Thread.sleep(1000);
//        }

//        UserSession uss_1 = smm1.find("sidnet");
//        if (uss_1 == null) {
//            uss_1 = new UserSession("sidnet");
//            //System.out.println("P0 -> " + uss_1);
//            smm1.add(uss_1);
//        }
        smm1.add(new UserSession("sidnet"));
        Thread.sleep(500);
        smm1.add(new UserSession("sidyul"));
//  4. Проверить что сессии нет через метод get
//  Вызвать несколько раз get
//        System.out.println("P4 - д/б null -> " + smm1.get(uss_1.getSessionHandle()));

//        smm1.get(uss_1.getSessionHandle());
//        smm1.get(uss_1.getSessionHandle());
//        //System.out.println(smm1.get(uss_1.getSessionHandle()));
////  Подождать (Thread.sleep) время, большее, чем время валидности
//        Thread.sleep(11000);
////  4. Проверить что сессии нет через метод get
//        //System.out.println("P4 - д/б null -> " + smm1.get(uss_1.getSessionHandle()));
////  5. Создать еще одну сессию
//        UserSession uss_2 = smm1.find("sidnet");
//        if (uss_2 == null) {
//            uss_2 = new UserSession("sidnet");
//            //System.out.println("P5 " + uss_2);
//            smm1.add(uss_2);
//        }
////  6. Подождать половину времени валидности сессии
//        Thread.sleep(5000);
//        //System.out.println("P6 " + smm1.sessionsIn.size());
////  7. Создать еще одну сессию
//        UserSession uss_3 = smm1.find("sidnet");
//        if (uss_3 == null) {
//            uss_3 = new UserSession("sidnet");
//            //System.out.println("P7 " + uss_3);
//            smm1.add(uss_3);
//        }
////  8. Подождать еще раз половину времени валидности сессии
//        Thread.sleep(5000);
//        //System.out.println("P8 " + smm1.sessionsIn.size());
////  9. Вызвать deleteExpired()
//        smm1.deleteExpired();
//        //System.out.println("P92 = " + smm1.sessionsIn.size());
////Убедиться, что одна сессия удалена, вторая нет
////
////Удалить оставшуюся через метод delete
////
////Убедиться что удаление прошло
    }
}
