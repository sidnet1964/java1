package ru.progwards.sid.N19;

//  объект класса содержит одну команду с параметрами
public class LineProgram {
    public String commandName;  // - наименование команды
    public char prefix;         // - префикс ' ', '=', '*'
    public int register;        // - номер регистра
    public String label;        // - текстовая метка loop

    public LineProgram(String commandName, char prefix, int register) {   //  конструктор # 1
        this.commandName = commandName;
        this.prefix = prefix;
        this.register = register;
    }
    public LineProgram(String commandName, String label) {   //  конструктор # 2
        this.commandName = commandName;
        this.label = label;
        this.prefix = ':';  //  по умолчанию в командах перехода
    }

    @Override
    public String toString() {
        return "LP {" + "'" + commandName + '\'' +
                ", " + prefix +
                " , " + register +
                ", " + label +
                '}';
    }
}
