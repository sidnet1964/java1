package ru.progwards.java2.lessons.gc;

public class OutOfMemoryException extends RuntimeException {
    public String commandName = "";

    public OutOfMemoryException(String commandName, int commandLine) {
        super("Ошибка в строке # " + commandLine);
        this.commandName = commandName;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + (commandName == null ? "" : " \"" + commandName + "\"");
    }
}
//  пример использования
//  throw new WrongCommand(" - неизвестный модификатор " + line0, numLine);