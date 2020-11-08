package ru.kpfu.itis.sockets.colors;

import ru.kpfu.itis.sockets.Protocol;

import java.awt.*;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Random;

public class Client {
    public static void main(String[] args) throws Throwable {
        System.out.println("Starting client...");
        Socket s = new Socket(InetAddress.getLocalHost(), Protocol.PORT);
        OutputStream out = s.getOutputStream();
        System.out.println("Starting sending random colors");
        while(true){
            Thread.sleep(1000);
            int r = (new Random()).nextInt(255);
            int g = (new Random()).nextInt(255);
            int b = (new Random()).nextInt(255);
            Color c = new Color(r, g, b);
            ByteBuffer buf = ByteBuffer.allocate(12);
            buf.putInt(r).putInt(g).putInt(b);
            System.out.println(">> " + c);
            System.out.println(Arrays.toString(buf.array()));
            out.write(buf.array());
            out.flush();
        }
    }
}
