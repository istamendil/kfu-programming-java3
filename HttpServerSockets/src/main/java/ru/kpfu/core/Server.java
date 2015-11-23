package ru.kpfu.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Alexander Ferenets (Istamendil) <ist.kazan@gmail.com>
 */
public class Server {

  private static ServerSocket serverSocket;
  private static Socket socket;
  private static final int PORT = 8080;

  public Server() {
    try {
      serverSocket = new ServerSocket(Server.PORT);
      while (true) {
        // Get Request
        socket = serverSocket.accept();
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while (true) {
          String s = br.readLine();
          if (s == null || s.trim().length() == 0) {
            break;
          }
          System.out.println(s);
        }
        // Create and send answer
        OutputStream os = socket.getOutputStream();
        os.write("Hi!".getBytes());
        os.flush();
      }
    } catch (Exception ex) {
      System.err.println("Error: " + ex.getMessage());
    }
  }

  public static void main(String[] args) {
    new Server();
  }
}
