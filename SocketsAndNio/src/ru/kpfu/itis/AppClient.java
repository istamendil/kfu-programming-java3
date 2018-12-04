package ru.kpfu.itis;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import ru.kpfu.itis.client.SocketClientExample;
import ru.kpfu.itis.protocol.Message;

public class AppClient {
  private static final String HOST = "127.0.0.1";
  private static final int PORT = 1234;
  
  public static void main(String[] args) {
    try{
      SocketClientExample client = new SocketClientExample(InetAddress.getByName(HOST), PORT);
      client.connect();
      byte[] data = ByteBuffer.allocate(8).putInt(2).putInt(3).array();
      // Message request = Message.createMessage(-1, data);// Bad request
      Message request = Message.createMessage(Message.TYPE1, data);
      System.out.println("Request");
      System.out.println(Message.toString(request));
      System.out.println();
      Message response = client.sendMessage(request);
      System.out.println("Response");
      System.out.println(Message.toString(response));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
