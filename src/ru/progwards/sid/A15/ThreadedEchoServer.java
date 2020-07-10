package ru.progwards.sid.A15;

import java.io.*;
import java.net.*;
import java.util.*;
/**
 * Данная программа реализует многопоточный сервер, который
 * ожидает обращения по порту 8189 и возвращает клиенту
 * переданные им данные.
 * @author Cay Horstmann
 * @version 1.20 2004-08-03
 */
public class ThreadedEchoServer
{
    public static void main(String[] args )
    {
        try
        {
            int i = 1;
            ServerSocket s = new ServerSocket(8189);
            while (true)
            {
                Socket incoming = s.accept();
                System.out.println("Spawning " + i);
                Runnable r = new ThreadedEchoHandler(incoming);
                Thread t = new Thread(r);
                t.start();
                i++;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
/**
 Этот класс обрабатывает данные введенные клиентом
 в рамках одного соединения.
 */
class ThreadedEchoHandler implements Runnable
{
    public ThreadedEchoHandler(Socket i) {
        incoming = i;
    }
    public void run() {
        try {
            try {
                InputStream inStream = incoming.getInputStream();
                OutputStream outStream = incoming.getOutputStream();
                Scanner in = new Scanner(inStream);
                PrintWriter out = new PrintWriter(outStream, true
                        /* автоматическая передача оставшихся данных */);
                out.println( "Hello! Enter BYE to exit." );
// Передача клиенту полученных от него данных
                boolean done = false;
                while (!done && in.hasNextLine())
                {
                    String line = in.nextLine();
                    out.println("Echo: " + line);
                    if (line.trim().equals("BYE"))
                        done = true;
                }
            } finally {
                incoming.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Socket incoming;
}
//  telnet localhost 8189