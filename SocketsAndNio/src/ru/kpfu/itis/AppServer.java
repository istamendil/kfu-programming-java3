package ru.kpfu.itis;

import ru.kpfu.itis.server.NioServerExample;
import ru.kpfu.itis.server.ServerExample;
import ru.kpfu.itis.server.SocketServerExample;
import ru.kpfu.itis.server.listeners.IntAdderListener;

public class AppServer {
  private static final int PORT = 1234;
  
  public static void main(String[] args) {
    try{
      ServerExample server = new SocketServerExample(PORT);
//      ServerExample server = new NioServerExample(PORT);
      server.registerListener(new IntAdderListener());
      server.start();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
