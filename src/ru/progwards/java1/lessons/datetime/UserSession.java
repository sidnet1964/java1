package ru.progwards.java1.lessons.datetime;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.TreeSet;

public class UserSession {
        //  ----------------------------------------------
        private int sessionHandle;
        private String userName;
        private ZonedDateTime lastAccess;
//  конструктор
        public UserSession(String userName){
            this.userName = userName;
            this.lastAccess = ZonedDateTime.now();
            this.sessionHandle = Integer.parseInt(userName);
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

    public static void main(String[] args) {
        TreeSet<UserSession> treeSet = new TreeSet<>( new Comparator<UserSession>() {
            @Override
            public int compare(UserSession o1, UserSession o2) {
                return Integer.compare(o1.sessionHandle, o2.sessionHandle);
            }
        });
        for (int i = 100; i < 1000; i+=100)
            treeSet.add(new UserSession(String.valueOf(i)));
        UserSession test1 = new UserSession("150");
        UserSession test2 = new UserSession("200");
        if (treeSet.contains(test1))
            System.out.println(treeSet.ceiling(test1));
        else
            System.out.println(treeSet.comparator());

        if (treeSet.contains(test2))
            System.out.println(treeSet.ceiling(test2));
        else
            System.out.println(treeSet.comparator());

//        System.out.println(treeSet.contains(test1));
//        System.out.println(treeSet.contains(test2));
    }
}
