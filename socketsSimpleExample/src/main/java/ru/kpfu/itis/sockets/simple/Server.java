package ru.kpfu.itis.sockets.simple;

import ru.kpfu.itis.sockets.Protocol;

import java.awt.*;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Server {
    public static void main(String[] args) throws Throwable {
        System.out.println("Starting server...");
        ServerSocket server = new ServerSocket(Protocol.PORT);
        System.out.println("Starting to listen port " + Protocol.PORT);
        Socket s = server.accept();
        System.out.println("Got one connection.");
        InputStream in = s.getInputStream();
        Color c;
        ByteBuffer buf = ByteBuffer.allocate(12);
        int b;
        int counter = 0;
        while((b = in.read()) != -1){
            //gathering color info
            if(counter < 12){
                System.out.println("<< " + b);
            }
            //next color
            else{
                System.out.println("Waiting for the next color");
                counter = 0;
            }
        }
    }
}
