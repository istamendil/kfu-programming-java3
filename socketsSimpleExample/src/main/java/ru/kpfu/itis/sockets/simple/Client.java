package ru.kpfu.itis.sockets.simple;

import ru.kpfu.itis.sockets.Protocol;

import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class Client {
    public static void main(String[] args) throws Throwable {
        System.out.println("Starting client...");
        Socket s = new Socket(InetAddress.getLocalHost(), Protocol.PORT);
        OutputStream out = s.getOutputStream();
        System.out.println("Starting sending random bytes");
        while(true){
            Thread.sleep(1000);
            byte random = (byte)(new Random()).nextInt(127);
            System.out.println(">> " + random);
            out.write(random);
            out.flush();
        }
    }
}
