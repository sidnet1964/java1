package ru.progwards.java1.lessons.N19;

//  объект класса содержит одну команду с параметрами
public class LineProgram {
    public String commandName;  // - наименование команды
    public int commandLine;     // номер строки исходной программы
    public char prefix;         // - префикс ' ', '=', '*'
    public int register;        // - номер регистра
    public String label;        // - текстовая метка loop

    public LineProgram(String commandName, int commandLine, char prefix, int register) {   //  конструктор # 1
        this.commandName = commandName;
        this.commandLine = commandLine;
        this.prefix = prefix;
        this.register = register;
    }
    public LineProgram(String commandName, int commandLine, String label) {   //  конструктор # 2
        this.commandName = commandName;
        this.commandLine = commandLine;
        this.label = label;
        this.prefix = ':';  //  по умолчанию в командах перехода
    }

    @Override
    public String toString() {
        return "LP, " + commandLine +
                ", " + "'" + commandName + '\'' +
                ", " + prefix +
                " , " + register +
                ", " + label
                ;
//               + '}'; //  упростить вывод
    }
}
