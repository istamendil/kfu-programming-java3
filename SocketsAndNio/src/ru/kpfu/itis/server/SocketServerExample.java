package ru.kpfu.itis.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import ru.kpfu.itis.protocol.Message;

public class SocketServerExample implements ServerExample{
  protected List<ServerEventListener> listeners;
  protected int port; 
  protected ServerSocket server;
  protected boolean started;
  // Better to incapsulate to List<Connection>
  protected List<Socket> sockets;
  
  public SocketServerExample(int port){
    this.listeners = new ArrayList<>();
    this.port = port;
    this.sockets = new ArrayList<>();
    this.started = false;
  }
  
  @Override
  public void registerListener(ServerEventListener listener) throws ServerException{
    if(started){
      throw new ServerException("Server has been started already.");
    }
    listener.init(this);
    this.listeners.add(listener);
  }
  
  @Override
  public void start() throws ServerException{
    try{
      // Start server
      server = new ServerSocket(this.port);
      started = true;
      
      // Proccess connections
      while(true){
        Socket s = server.accept();
        // If some connection comes, it will wait for previous connection handling.
        // Java implements queue for this waiting.
        // So status will be still LISTENING (can be checked with netstat).
        handleConnection(s);
      }
    }
    catch(IOException ex){
      throw new ServerException("Problem with server starting.", ex);
    }
  }
  
  protected void handleConnection(Socket socket) throws ServerException{
    sockets.add(socket);
    try{
      // Every connection will wait creating and handling of message
      Message message = Message.readMessage(socket.getInputStream());
      System.out.println("New message:");
      System.out.println(Message.toString(message));
      for(ServerEventListener listener : listeners){
        if(message.getType() == listener.getType()){
          // One by one! Another left listeners will wait current
          // Another thread could be created here or before for every Listener
          listener.handle(socket, message);
        }
      }
    }
    catch(IOException ex){
      throw new ServerException("Problem with handling connection.", ex);
    } catch (ServerEventListenerException ex) {
      throw new ServerException("Problem with handling message.", ex);
    }
  }
  
  @Override
  public void sendMessage(Socket socket, Message message) throws ServerException{
    if(!started){
      throw new ServerException("Server hasn't been started yet.");
    }
    //ToDo: check if this socket is from our pull
    try{
      socket.getOutputStream().write(Message.getBytes(message));
      socket.getOutputStream().flush();
    } catch (IOException ex) {
      throw new ServerException("Can't send message.", ex);
    }
  }
  
  @Override
  public void sendBroadCastMessage(Message message) throws ServerException{
    if(!started){
      throw new ServerException("Server hasn't been started yet.");
    }
    try{
      byte[] rawMessage = Message.getBytes(message);
      for(Socket socket : sockets){
        socket.getOutputStream().write(rawMessage);
        socket.getOutputStream().flush();
      }
    } catch (IOException ex) {
      throw new ServerException("Can't send message.", ex);
    }
  }
  
}
