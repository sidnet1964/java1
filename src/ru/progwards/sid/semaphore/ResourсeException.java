package ru.progwards.sid.semaphore;

// # 20 # исключение, информирующее о сбое в поставке ресурса # ResourceException.java
public class ResourсeException extends Exception {
    public ResourсeException() {
        super();
    }
    public ResourсeException(String message, Throwable cause) {
        super(message, cause);
    }
    public ResourсeException(String message) {
        super(message);
    }
    public ResourсeException(Throwable cause) {
        super(cause);
    }
}