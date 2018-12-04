package ru.kpfu.itis.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import ru.kpfu.itis.protocol.Message;

public class SocketClientExample implements ClientExample{
  protected final InetAddress address;
  protected final int port;
  protected Socket socket;

  public SocketClientExample(InetAddress address, int port) {
    this.address = address;
    this.port = port;
  }

  public InetAddress getAddress() {
    return address;
  }

  @Override
  public void connect() throws ClientException {
    try{
      socket = new Socket(address, port);
    }
    catch(IOException ex){
      throw new ClientException("Can't connect.", ex);
    }
  }

  @Override
  public Message sendMessage(Message message) throws ClientException {
    try{
      socket.getOutputStream().write(Message.getBytes(message));
      socket.getOutputStream().flush();
      return Message.readMessage(socket.getInputStream());
    }
    catch(IOException ex){
      throw new ClientException("Can't send message.", ex);
    }
  }
  
  
}
