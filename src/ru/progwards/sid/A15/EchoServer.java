package ru.progwards.sid.A15;

import java.io.*;
import java.net.*;
import java.util.*;

public class EchoServer {
    public static void main(String[] args ) {
        try {
// Создание сокета на стороне сервера.
            ServerSocket socket = new ServerSocket(8189);
// Ожидание обращения клиента.
//  Этот метод блокирует текущий поток до тех пор, пока не будет совершено подключение.
            Socket incoming = socket.accept( );
            try {
                InputStream inStream = incoming.getInputStream();
                OutputStream outStream = incoming.getOutputStream();
                Scanner in = new Scanner(inStream);
                PrintWriter out = new PrintWriter(outStream, true
                        /* автоматическая передача оставшихся данных */);
                out.println( "Hello! Enter BYE to exit." );
// Передача клиенту полученных от него данных.
                boolean done = false;
                while (!done && in.hasNextLine())
                {
                    String line = in.nextLine();
                    out.println("Echo: " + line);
                    if (line.trim().equals("BYE")) done = true;
                }
            } finally {
                incoming.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}