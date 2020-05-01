package ru.progwards.java1.lessons.N19;

public class WrongCommand extends RuntimeException {
    public String commandName = "";

    public WrongCommand(String commandName, int commandLine) {
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