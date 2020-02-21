package ru.progwards.java1.lessons.io1;

public class Coder {
    public static void codeFile(String inFileName, String outFileName, char[] code, String logName){
    //  прочитать файл inFileName и перекодировать его посимвольно в соответствии с заданным шифром,
    //  результат записать в outFileName. Шифр задается маccивом char[] code,
    //  где каждому символу symbol оригинального файла соответствует символ
    //  code[(int)symbol] выходного файла. В случае ошибок, в файл с именем logName
    //  вывести название ошибки через метод класса Exception - getMessage()
    }

    public static void main(String[] args) {
        char[] code = {'a', 'b', 'c'};
        codeFile(
                "C:\\Users\\sidne\\IdeaProjects\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\io1\\file2.txt",
                "C:\\Users\\sidne\\IdeaProjects\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\io1\\file3.txt",
                code,
                "C:\\Users\\sidne\\IdeaProjects\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\io1\\file4.txt");
    }
}
