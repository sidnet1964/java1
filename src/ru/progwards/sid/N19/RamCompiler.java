package ru.progwards.sid.N19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class RamCompiler {
    final int R0 = 0;   //  нулевой регистр
    final int Z0 = 0;   //  нулевое значение (по умолчанию в новом регистре)
    final char M1 = '=';    //  модификаторы операндов
    final char M2 = '-';    //
    final char M3 = '*';    //

//    String  fileName; //  нужно ли делать свойством?
    public Map<String, Integer> labelMap;
    public List<LineProgram> program;   //  хранение программы
    public ArrayDeque<Integer> input;   //  входные данные
    public List<String> output;
    public Map<Integer, Integer> memory;
    public String textErrCom;
    public boolean errorCom;
    public boolean errorRun;
    //  список всех команд эмулятора
    final static String[] command = {"ADD", "DIV", "GOTO", "HALT", "JGTZ", "JMP", "JZ", "LOAD", "MUL", "READ", "STORE", "SUB", "WRITE"};

    //  конструктор
    RamCompiler(String fileName){
        labelMap    = new TreeMap<>();
        program     = new ArrayList<>();
        input       = new ArrayDeque<>();
        output      = new ArrayList<String>();
        memory      = new TreeMap<>();
        textErrCom = null;
        errorCom = false;  //  наличие ошибок компиляции
        errorRun = false;  //  наличие ошибок выполнения
        memory.put(R0, Z0); //  инициализировать нулевой регистр
        loadProgram(fileName);    //  загрузить информацию
    }

    List<Integer> registers(){
        List<Integer> registers_1 = new ArrayList<>();
//        System.out.println(memory.forEach());
//        int maxReg = memory
//        Map.Entry< K,V> pollLastEntry()
//        Map.Entry<Integer,Integer> lastEntry()
//        Integer highestKey = memory.lastKey();
//        Integer lowestKey = this.memory.firstKey();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(3, 11);
        map.put(2, 11);
        map.put(1, 11);
        map.put(5, 11);
        map.put(4, 11);

        Integer highestKey = map.lastKey();
        Integer lowestKey = map.firstKey();
        System.out.println(highestKey);
        System.out.println(lowestKey);

        return registers_1;
    }
    //  -----------------------------------
    //  обработка содержимого одного файла команд
    void loadProgram(String fileName) {
        String commandName = "";
        int operand = 0;
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(Paths.get(fileName)); //  , UTF_8
        } catch (IOException e) {
            e.printStackTrace();
        }
        int numLine = 0;    //  номер строки прграммы
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
                System.out.println(textErrCom);
                errorCom = true;
                return;
                //  ошибка компиляции - отсутствует метка перед символом :
                //  длина метки и содержание пока не определено
                //  записать сообшениние в выходной поток
            }
            if (pos > 0){
                String labelName = line1.substring(0, pos).toLowerCase();
                /// проверить метку на валидность ---
                labelMap.put(labelName, program.size());
                program.add(new LineProgram("GOTO", labelName));    //  оператор метки
//                System.out.println("labelName = " + labelName);
                continue;
            }
            //  обработка строки ввода  <input> 1 2 3 4 5 6 7 1 1 1 2 5 0
            pos = line1.indexOf("<input>");
            if (pos >= 0) {
                checkInput(line1.substring(pos + 7));
                continue;
            }
            if (!checkCommand(line1, numLine)) {    //  проверить строку на наличие команды
                textErrCom = '"' + line1 + '"' + " - ошибка в строке " + numLine;
                System.out.println(textErrCom);
                errorCom = true;
                return;
            }
//            System.out.println("("+numLine+") - " + line1);
        }
    }
    //  --------------------------------
    //  разобрать строку ввода на части, результат записать в очередь
    void checkInput(String line0){
        //  line0 - остаток строки со списком данных
        try (Scanner scanner = new Scanner(line0)) {
            while (scanner.hasNextInt()) {
                int operand = scanner.nextInt();
                input.offer(operand);
            }
        } catch (Exception e) {
            System.out.println("Ошибочка вышла!");
        }
        System.out.println("input = " + input);
    }
    //  --------------------------------
    boolean checkCommand(String line0, int numLine){
        //  line0 - полная  строка, предполагаем наличие команды из списка
        String operand = "-";
        char modifier = '-';
        int register = 0;

        String[] subStr = line0.split(" "); // Разделения строки str с помощью метода split()
        String nameComm = subStr[0].toUpperCase();  //  перевести команду в верхний регистр
        int ind1 = Arrays.binarySearch(command, nameComm);  //  поиск в сортированном массиве
        if (ind1 < 0)  //  команда не найдена
            return false;
        //  команда опознана - разбираем операнд, если он присутствует
        switch (nameComm){
            case "JMP":
            case "JZ":
            case "JGTZ":
                //  второй операнд это метка (label)
                if (subStr.length == 2 ) {   //  что делать, если >2 ?
                    operand = subStr[1].toLowerCase(); //  извлечь операнд команды (X1)
                    if (checkLabel(operand))
                        program.add(new LineProgram(nameComm, operand));    //  оператор перехода
                    else
                        return false;
                }
                else
                    return false;
                break;
            case "HALT":    //  операнд отсутсвует, проверки нет
                program.add(new LineProgram(nameComm, "END"));    //  оператор завершения
                break;
            default:    //  остальные команды
                if (subStr.length > 1) {
                    operand = subStr[1]; //  извлечь операнд команды (=1, 1, *1, X1)
                    modifier = operand.charAt(0);
                    if (modifier == M1 || modifier == M3)
                        register = Integer.parseInt(operand.substring(1));  //  i в остатке операнда
                        //  register > 0 ---
                    else if (Character.isDigit(modifier)) {
                        register = Integer.parseInt(operand);  //  i это операнд
                        //  register > 0 ---
                        modifier = M2;  //  установить принудительно вместо ' '
                    }
                    else
                        System.out.println("Ошибка - неизвестный модификатор " + line0);
                }
                //  дополнительно проверить невозможность некоторых модификаторов
                if (nameComm == "READ" && (modifier == M1 || modifier == M3))
                    //  недопустимый модификатор ---
                    return false;
                if (nameComm == "STORE" && modifier == M1)
                    //  недопустимый модификатор ---
                    return false;
                program.add(new LineProgram(nameComm, modifier, register));
        }
        return true;
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
        for (int ind = 0; ind < program.size(); ind++) {
//            System.out.println(ind + " | " + program.get(ind));
            LineProgram line1 = program.get(ind);
            System.out.println(ind + " | " + line1 + " - memory = " + memory);
            switch (line1.commandName) {
                case "READ":    //  Ri ← next input
//                    String str = "3";   //  временно
//                    try (Scanner scanner = new Scanner(System.in)) {
//                        str = scanner.nextLine();
//                    }
//                    int input = Integer.parseInt(str);
                    int incoming = input.poll();
                    memory.put(line1.register, incoming);
                    break;
                case "WRITE":   //  output ← Ri
                    int bufferW = 0;
                    if (line1.prefix == M1)
                        bufferW = line1.register;
                    else
                        bufferW = memory.getOrDefault(line1.register, Z0);   //  0 по умолчанию
                    System.out.println("RAM - вывод -> " + bufferW);
                case "LOAD":    //  R0 ← Ri
                    int bufferL = 0;
                    if (line1.prefix == M1)
                        bufferL = line1.register;
                    else
                        bufferL = memory.getOrDefault(line1.register, Z0);   //  0 по умолчанию
                    memory.put(R0, bufferL);
                    break;
                case "STORE":   //  Ri ← R0
                    int bufferS = memory.get(R0);
                    memory.put(line1.register, bufferS);
                    break;
                case "ADD": //  R0 ← R0 + Ri        |
                case "SUB": //  R0 ← R0 - Ri        |
                case "MUL": //  R0 ← R0 * Ri        |
                case "DIV": //  R0 ← R0 / Ri        |
                    //  арифметические вычисления   |
                    arithmetic(line1);  //          |
                    break;
                case "JGTZ":
                    int triggerG = memory.get(R0);
                    if (triggerG > 0) {
                        int address = labelMap.get(line1.label);
                        ind = address;  // - 1;
                    }
                    break;
                case "JZ":
                    int triggerZ = memory.get(R0);
                    if (triggerZ == 0) {
                        int address = labelMap.get(line1.label);
                        ind = address;  // - 1;
                    }
                    break;
                case "JMP": //  безусловный переход
                    int address = labelMap.get(line1.label);
                    ind = address - 1;  // - 1;
                    break;
                case "GOTO":    //  метка перехода
                    break;
                default:
            }
        }
    }
    //  арифметические операйии + - * /
    void arithmetic(LineProgram line1){
        //  1) подготовка операндов
        long bufferRI = 0;   //  буфер для хранения результата, long - для контроля переполнения
        if (line1.prefix == M1)    //  constant (=i)
            bufferRI = line1.register;
        else                        //  direct operand (i)
            bufferRI = memory.getOrDefault(line1.register, Z0);   //  0 по умолчанию
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
//            throw new ArithmeticException( this + ": исключение в arithmetic");
            memory.put(R0, Z0);  //  значение НЕ допустимо
    }
    public enum commandName { ADD, DIV, HALT, JGTZ, JMP, JZ, LOAD, MUL, READ, STORE, SUB, WRITE }

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
        RamCompiler pr1 = new RamCompiler("C:/Projects/Academy/Java1/factorial.txt");
        //  есть вариант определить в конструкторе каталог, а имена файлов сделать стандартными
        System.out.println("pr1.labelMap = " + pr1.labelMap);
//        System.out.println(pr1.program);
//        for (String line1 : pr1.program) {
//            System.out.println(line1);
//        }
//        for (int ind = 0; ind < pr1.program.size(); ind++)
//            System.out.println(ind + " | " + pr1.program.get(ind));
        pr1.execute();
        System.out.println("pr1.memory = " + pr1.memory);
//        String highestKey = pr1.labelMap.lastKey();
        System.out.println(pr1.registers());

//        System.out.println("pr1.memory = " + pr1.memory.lastKey());
        //        System.out.println(Integer.MAX_VALUE);

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
