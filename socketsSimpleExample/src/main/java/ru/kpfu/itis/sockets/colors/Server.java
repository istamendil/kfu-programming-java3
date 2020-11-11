package ru.kpfu.itis.sockets.colors;

import ru.kpfu.itis.sockets.Protocol;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Server {
    public static void main(String[] args) throws Throwable {
        //Init server and one connection
        System.out.println("Starting server...");
        ServerSocket server = new ServerSocket(Protocol.PORT);
        System.out.println("Starting to listen port " + Protocol.PORT);
        Socket s = server.accept();
        System.out.println("Got one connection.");
        InputStream in = s.getInputStream();

        //Create Window
        JFrame frame = new JFrame("Sockets example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setOpaque(true);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setBounds(300, 200, 700, 900);
        frame.setVisible(true);


        Color c;
        ByteBuffer buf = ByteBuffer.allocate(12);
        int b = -1;
        int counter = 0;
        while((counter>=12)||((b = in.read()) != -1)){
            //Got whole color
            if(counter >= 12){
                System.out.println(Arrays.toString(buf.array()));
                buf.rewind();
                System.out.println(buf.getInt());
                System.out.println(buf.getInt());
                System.out.println(buf.getInt());
                buf.rewind();
                c = new Color(buf.getInt(), buf.getInt(), buf.getInt());
                System.out.println("Got last byte of color: " + c);
                panel.setBackground(c);
                System.out.println("Waiting for the next color");
                counter = 0;
                buf = ByteBuffer.allocate(12);
            }  else {
				//Add new byte to buffer
                System.out.println((byte)b);
                buf.put((byte)b);
                counter++;
            }
        }
    }
}
