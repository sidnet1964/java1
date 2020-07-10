package ru.progwards.sid.A15;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;

public class SocketTest {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("time-A.nist.gov", 13);
//            s.setSoTimeout(1000);
            try {
                InputStream inStream = s.getInputStream();
                Scanner in = new Scanner(inStream);
                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    System.out.println(line);
                }
            } finally {
                s.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//  ------------------------------------
        try {
//            InetAddress address = InetAddress.getLocalHost();
            InetAddress address = InetAddress.getByName("time-A.nist.gov");
            System.out.println(address);
            //  Sidnet1964/192.168.0.100
            //  time-A.timefreq.bldrdoc.gov/132.163.96.1
            //  time-A.nist.gov/129.6.15.28
            System.out.println(address.getHostName());      //  time-A.nist.gov
            System.out.println(address.getHostAddress());   //  129.6.15.28
            byte[] addressBytes = address.getAddress();
            System.out.println(Arrays.toString(addressBytes));
            //  [-127, 6, 15, 28]
            InetAddress[] addresses = InetAddress.getAllByName("yandex.ru");
            System.out.println(Arrays.toString(addresses));
//  [yandex.ru/5.255.255.70, yandex.ru/77.88.55.60, yandex.ru/5.255.255.60, yandex.ru/77.88.55.66]
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}