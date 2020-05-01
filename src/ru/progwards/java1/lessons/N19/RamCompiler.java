package ru.progwards.java1.lessons.N19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class RamCompiler {
    final static int R0 = 0;   //  индекс нулевого регистра
    final static int Z0 = 0;   //  нулевое значение (по умолчанию в новом регистре)
    final static int Z1 = 0;   //  значение по умолчанию в новом регистре 1-99
    final static char M1 = '=';    //  модификаторы операндов
    final static char M2 = '-';    //
    final static char M3 = '*';    //
    //  список всех команд эмулятора
    final static String[] command = {"ADD", "DIV", "GOTO", "HALT", "JGTZ", "JMP", "JZ", "LOAD", "MUL", "READ", "STORE", "SUB", "WRITE"};
    final static String EC11 = " - недопустимый модификатор - ";
    final static String ER11 = " - регистр с отрицательным номером ";

//    String  fileName; //  нужно ли делать свойством?
    TreeMap<String, Integer> labelMap;
    List<LineProgram> program;  //  хранение программы
    List<Integer> inputList;    //  входные данные для вывода (возврата)
    List<Integer> outputList;   //  выходные данные для вывода (возврата)
    ArrayDeque<Integer> input;  //  входные данные
    TreeMap<Integer, Integer> memory;
    public String textErrCom;
    public boolean errorCom;
    public boolean errorRun;
    public int numLine;             // номер строки исходной программы
    public int indexLineProgram;    // номер строки выполняемой программы, глобальная

    //  конструктор
    RamCompiler(String fileName){
        labelMap    = new TreeMap<>();
        program     = new ArrayList<>();
        inputList   = new ArrayList<>();
        input       = new ArrayDeque<>();
        outputList  = new ArrayList<>();
        memory      = new TreeMap<>();
        textErrCom = null;
        errorCom = false;  //  наличие ошибок компиляции
        errorRun = false;  //  наличие ошибок выполнения
        memory.put(R0, Z0); //  инициализировать нулевой регистр
        numLine = 0;     // номер строки исходной программы
        indexLineProgram = 0;    // номер строки выполняемой программы
        loadProgram(fileName);    //  загрузить информацию
    }

    //  входной поток
    public List<Integer> input(){
//        List<Integer> output = new ArrayList<>(this.output);
        return inputList;
    }
    //  выходной поток
    public List<Integer> output(){
//        List<Integer> output = new ArrayList<>(this.output);
        return outputList;
    }

    //   регистры программы
//    public List<Integer> registers(){
//        List<Integer> registers = new ArrayList<>(this.memory.values());
//        return registers;
//    }
    public Map<Integer, Integer> registers(){
        return memory;
    }

    //  -----------------------------------
    //  обработка содержимого одного файла команд
    void loadProgram(String fileName) {
//        Integer highestKey = memory.lastKey();
        String commandName = "";
        int operand = 0;
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(Paths.get(fileName)); //  , UTF_8
        } catch (IOException e) {
            throw new WrongCommand(" - ошибка в имени файла " + fileName, numLine);
        }
//        int numLine = 0;    //  номер строки прграммы
        for (String line1 : allLines) {
            numLine++;      //  увеличить счетчик независимо от содержания
            line1 = line1.trim();           //  1)  удаляем пробелы из строки
            int pos = line1.indexOf(";");   //  2) поиск комментария
            if (pos == 0)                   //  2) комментарий с начала строки
                continue;
            if (pos > 0)                    //  2)  комментарий в конце строки
                line1 = line1.substring(0, pos);
            if (line1.isEmpty())            //  3) пропускаем пустые строки
                continue;
            //  в этом варианте метка размещается на отдельной строке
            pos = line1.indexOf(":");       //  4) признак метки (label)
            if (pos == 0){
                textErrCom = "Отсутствует метка в строке " + numLine;
                throw new WrongCommand(" - отсутствует метка перед /':/' " + line1, numLine);
                //  ошибка компиляции - отсутствует метка перед символом :
                //  длина метки и содержание пока не определено
                //  записать сообшениние в выходной поток
            }
            if (pos > 0){
                String labelName = line1.substring(0, pos).toLowerCase();
                /// проверить метку на валидность ---
                //  добавить новую метку в таблицу
                if (labelMap.containsKey(labelName))
                    throw new WrongCommand(" - метка уже использована " + line1, numLine);
                labelMap.put(labelName, program.size());
                program.add(new LineProgram("GOTO", numLine, labelName));    //  оператор метки
//                continue;
                line1 = line1.substring(pos+1).trim();   //  обрезать метку и продолжить
                if (line1.isEmpty())            //  3) пропускаем пустые строки (повторно)
                    continue;
            }
            //  обработка строки ввода  <input> 1 2 3 4 5 6 7 1 1 1 2 5 0
            pos = line1.indexOf("<input>");
            if (pos >= 0) {
                checkInput(line1.substring(pos + 7));
                continue;
            }
            checkCommand(line1);
        }
        //  проход по сформированной программе c проверкой меток
        for (LineProgram lineP : program) {
            String label = lineP.label;
            if (label != null && !lineP.commandName.equals("GOTO")) {    //  JGTZ, JZ, JMP
                var index = labelMap.get(label);
                if (index == null)
                    throw new WrongCommand(" - неизвестная метка " + label, numLine);
                else
                    lineP.register = (int) index;
            }
//                if (!labelMap.containsKey(label))
        }
    }
    //  --------------------------------
    //  разобрать строку ввода на части, результат записать в очередь
    void checkInput(String line0) {
        //  line0 - остаток строки со списком данных
        try (Scanner scanner = new Scanner(line0)) {
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    int operand = scanner.nextInt();
                    input.offerLast(operand);   //  .offer(operand);
                    inputList.add(operand);
                    //  --- параллельно заполнять список
                }
                else
                    throw new WrongCommand(" - ожидаются тип int " + line0, numLine);
            }
        }
    }
    //  --------------------------------
    void checkCommand(String line0) {
        //  line0 - полная  строка, предполагаем наличие команды из списка
        String operand = "-";
        char modifier = '-';
        int register = 0;

        String[] subStr = line0.split(" "); // Разделения строки str с помощью метода split()
        String nameComm = subStr[0].toUpperCase();  //  перевести команду в верхний регистр
        int ind1 = Arrays.binarySearch(command, nameComm);  //  поиск в сортированном массиве
        if (ind1 < 0)  //  команда не найдена
            throw new WrongCommand(" - команда не найдена" + line0, numLine);
        //  команда опознана - разбираем операнд, если он присутствует
        switch (nameComm) {
            case "JMP":
            case "JZ":
            case "JGTZ":
                //  второй операнд это метка (label)
                if (subStr.length == 2) {   //  что делать, если >2 ?
                    operand = subStr[1].toLowerCase(); //  извлечь операнд команды (X1)
                    if (checkLabel(operand))
                        program.add(new LineProgram(nameComm, numLine, operand));    //  оператор перехода
                    else
                        throw new WrongCommand(" - метка не найдена" + line0, numLine);
                } else
                    throw new WrongCommand(" - недопустимый операнд" + line0, numLine);
                break;
            case "HALT":    //  операнд отсутсвует, проверки нет
                program.add(new LineProgram(nameComm, numLine, null));    //  оператор завершения
                break;
            default:    //  остальные команды
                if (subStr.length > 1) {
                    operand = subStr[1]; //  извлечь операнд команды (=1, 1, *1, X1)
                    modifier = operand.charAt(0);
                    if (modifier == M1 || modifier == M3)
                        operand = operand.substring(1);  //  i в остатке операнда
                    else if (Character.isDigit(modifier))
                        modifier = M2;  //  установить принудительно вместо ' '
                    //  register > 0 ---
                    register = safetyInteger(operand, line0);//  i это операнд
                } else
                    throw new WrongCommand(" - неизвестный модификатор " + line0, numLine);
        }
        //  дополнительно проверить невозможность некоторых модификаторов
        if (nameComm.equals("READ") && modifier == M1)
            throw new WrongCommand(EC11 + line0, numLine);
        if (nameComm.equals("STORE") && modifier == M1)
            throw new WrongCommand(EC11 + line0, numLine);
        program.add(new LineProgram(nameComm, numLine, modifier, register));
    }

    //  --------------------------------
    //  безопасное преобразование строки в int
    int safetyInteger(String operandStr, String line0){
        int operandInt = 0;
        try (Scanner scanner = new Scanner(operandStr)) {
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    operandInt = scanner.nextInt();
                    continue;
                }
                else
                    throw new WrongCommand(" - ожидаются тип int " + line0, numLine);
            }
        }
        return operandInt;
    }

    //  --------------------------------
    //  разобрать операнд
    boolean checkLabel(String thisIsLabel){
        //  первый символ должен быть буквой
        //  остальные символы тоже надо проверить ---
        return Character.isLetter(thisIsLabel.charAt(0));
    }
    //  --------------------------------
    //  выполнить заруженную иразобранную программу
    public void execute(){
        for (indexLineProgram = 0; indexLineProgram < program.size(); indexLineProgram++) {
            LineProgram line1 = program.get(indexLineProgram);
//            System.out.println(indexLineProgram + " | " + line1 + " , " + memory); //  debug
            switch (line1.commandName) {
                case "WRITE":   //  output ← Ri
                    int bufferW = 0;
                    switch (line1.prefix){
                        case M1:
                            bufferW = line1.register;
                            break;
                        case M2:
                            bufferW = memory.getOrDefault(line1.register, Z1);
                            break;
                        case M3:
                            bufferW = memory.getOrDefault(line1.register, Z1);
                            if (bufferW < 0)
                                throw new WrongCommand(ER11 + line1, line1.commandLine);
                            bufferW = memory.getOrDefault(bufferW, Z1);
                    }
                    outputList.add(bufferW);
                    break;
                case "LOAD":    //  R0 ← Ri
                    int bufferL = 0;
                    switch (line1.prefix){
                        case M1:
                            bufferL = line1.register;
                            break;
                        case M2:
                            bufferL = memory.getOrDefault(line1.register, Z1);
                            break;
                        case M3:
                            bufferL = memory.getOrDefault(line1.register, Z1);
                            if (bufferL < 0)
                                throw new WrongCommand(ER11 + line1, line1.commandLine);
                            bufferL = memory.getOrDefault(bufferL, Z1);
                    }
                    memory.put(R0, bufferL);
                    break;
                case "STORE":   //  Ri ← R0
                    int bufferS = memory.get(R0);   //  содержимое R0
                    int registerS = 0;
                    switch (line1.prefix){
                        case M1:
                            //  этой ситуации не должно быть, но вдруг ...
                            throw new WrongCommand(" - недопустимый модификатор " + line1, line1.commandLine);
                        case M2:
                            registerS = line1.register;
                            break;
                        case M3:
                            registerS = line1.register;
                            //  --- проверить логику по поводу Z1
                            if (registerS < 0)
                                throw new WrongCommand(ER11 + line1, line1.commandLine);
                            registerS = memory.getOrDefault(registerS, Z1);
                    }
                    memory.put(registerS, bufferS);
                    break;
                case "READ":    //  Ri ← next input
                    if (input.peekFirst() == null)
                        throw new WrongCommand(" - входной поток пуст " + line1, line1.commandLine);
                    int incoming = input.pollFirst();   //  .poll();
//+
                    int registerR = 0;
                    switch (line1.prefix){
                        case M1:
                            //  этой ситуации не должно быть, но вдруг ...
                            throw new WrongCommand(" - недопустимый модификатор " + line1, line1.commandLine);
                        case M2:
                            registerR = line1.register;
                            break;
                        case M3:
                            registerR = line1.register;
                            //  --- проверить логику по поводу Z1
                            if (registerR < 0)
                                throw new WrongCommand(ER11 + line1, line1.commandLine);
                            registerR = memory.getOrDefault(registerR, Z1);
                    }
//-
                    memory.put(registerR, incoming);
                    break;
//  группа операторов вычисления
                case "ADD": //  R0 ← R0 + Ri        |
                case "SUB": //  R0 ← R0 - Ri        |
                case "MUL": //  R0 ← R0 * Ri        |
                case "DIV": //  R0 ← R0 / Ri        |
                    //  арифметические вычисления   |
                    arithmetic(line1);  //          |
                    break;
//  группа операторов перехода
                case "JGTZ":
                case "JZ":
                case "JMP":
                    move_to_position(line1);
                    break;
                case "GOTO":    //  метка перехода
                    break;
                default:
            }
        }
    }
    //  --------------------------------
    //  операции перехода - JGTZ, JZ, JMP
    void move_to_position(LineProgram line1){
//  indexLineProgram сделана сквозной (глобальной) чтобы не передавать/возвращать ее
        int trigger = memory.get(R0);   //  содержимое R0 (не требуется для JMP)
        switch (line1.commandName) {
            case "JGTZ":
                if (trigger > 0) {
                    int address = line1.register;
                    indexLineProgram = address - 1;  // - 1;
                }
                break;
            case "JZ":
                if (trigger == 0) {
                    int address = line1.register;
                    indexLineProgram = address - 1;  // - 1;
                }
                break;
            case "JMP": //  безусловный переход
                int address = line1.register;
                indexLineProgram = address - 1;  // - 1;
                break;
        }
    }
    //  --------------------------------
    //  арифметические операции + - * /
    void arithmetic(LineProgram line1){
        //  1) подготовка операндов
        long bufferRI = 0;   //  буфер для хранения результата, long - для контроля переполнения
        switch (line1.prefix){
            case M1:
                bufferRI = line1.register;
                break;
            case M2:
                bufferRI = memory.getOrDefault(line1.register, Z1);
                break;
            case M3:
                bufferRI = memory.getOrDefault(line1.register, Z1);
                if (bufferRI < 0)
                    throw new WrongCommand(ER11 + line1, line1.commandLine);
                bufferRI = memory.getOrDefault((int)bufferRI, Z1);
        }
        //  2) выполнение операции
        switch (line1.commandName) {
            case "ADD": //  R0 ← R0 + Ri
                bufferRI = memory.get(R0) + bufferRI;
                break;
            case "SUB": //  R0 ← R0 - Ri
                bufferRI = memory.get(R0) - bufferRI;
                break;
            case "MUL": //  R0 ← R0 * Ri
                bufferRI = memory.get(R0) * bufferRI;
                break;
            case "DIV": //  R0 ← R0 / Ri
                //  предупреждение деления на 0 !   ArithmeticException
                if (bufferRI == 0)
                    throw new ArithmeticException( this + ": исключение в arithmetic");
                bufferRI = memory.get(R0) / bufferRI;
                break;
            default:
        }
        //  3) запись результата
        if (bufferRI >= Integer.MIN_VALUE && bufferRI <= Integer.MAX_VALUE)
            memory.put(R0, (int)bufferRI);  //  значение в допустимом диапазоне
        else
            throw new WrongCommand(" - переполнение регистра R0 " + line1, line1.commandLine);
    }
//    public enum commandName { ADD, DIV, HALT, JGTZ, JMP, JZ, LOAD, MUL, READ, STORE, SUB, WRITE }

    public static void main(String[] args) {
        //  1.  загрузка текста программы
        //  2.  загузка файла с данными или ввод
        //  3.  определение вывода - консоль или файл
        //  4.  установка режима исполнения (отладки)
        //  5.  исполнение программы, замер быстродействия
        //  6.  журнал ошибок компиляции
        //  7.  журнал ошибок исполнения
        //  8.  аварийный останов, анализ зацикливания
        //  9.  контороль использования памяти (регистров)
        //  10. использование индексных регистров отдельно (TreeMap)
        //
        //  - структуры данных для хранения программы (код + операнд)
        //      с учетом перехода по меткам
        //  - структура данных для моделирования памяти

        RamCompiler pr1 = null;
        try {
            pr1 = new RamCompiler("C:/Users/sidnet1964/IdeaProjects/HelloWorld/src/ru/progwards/java1/lessons/N19/bubble.txt");
        } catch (WrongCommand e ) {
            System.out.println(e.getMessage());
        }
        if (pr1 != null) {
            //  проверить на null
            //  есть вариант определить в конструкторе каталог, а имена файлов сделать стандартными
//            System.out.println("pr1.labelMap = " + pr1.labelMap);

            pr1.execute();

//            System.out.println("pr1.memory = " + pr1.memory);
//            System.out.println("pr1.outputList = " + pr1.outputList);

            System.out.println();
            System.out.println("pr1.registers() = " + pr1.registers());
            System.out.println("pr1.input() = " + pr1.input());
            System.out.println("pr1.output() = " + pr1.output());
//            System.out.println(Integer.MAX_VALUE);
//            long size = 100;
//            int[] intArr = new Random().ints(size, -99, 99).toArray();
//            System. out.println("intArr = " + Arrays.toString(intArr));

        }
    }

}
//  "ADD", "DIV", "HALT", "JGTZ", "JMP", "JZ", "LOAD", "MUL", "READ", "STORE", "SUB", "WRITE"
//  Постановка задачи
//Создать класс RamCompiler, который принимает на вход исходный код на RAM и выполняет программу.
//
//Конструктор RamCompiler(String fileName)
//Метод execute() - выполняет программу
//Метод List<int> input() - входящий поток
//Метод List<int> output() - входящий поток
//Метод List<int> registers() - регистры, номер регистра соответствует индексу в коллекции.
// Если какой-то регистр не был инициализирован, там должен быть null.
