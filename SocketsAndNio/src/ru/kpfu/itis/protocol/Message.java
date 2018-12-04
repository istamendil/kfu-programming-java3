package ru.kpfu.itis.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

// Message contents: [FIRST BYTES (2), DATA LENGTH(4), MESSAGE TYPE(4), DATA(?)]
// Factory Method depending on context can be moved outside
public class Message {
  // Some abstract types of message. E.G. Chat Message and Register Message
  // Better to make message complex typed. So values must be 1,2,4,8,... to make available store few values in one int
  public static final int TYPE1 = 1;
  public static final int TYPE2 = 2;
  
  public static final int MAX_LENGTH = 1000;
  protected static final byte[] START_BYTES = new byte[]{0xA, 0xB};
  public static Message createMessage(int messageType, byte[] data)
          throws IllegalArgumentException{//Better to create own Exception
    if(data.length > MAX_LENGTH){
      throw new IllegalArgumentException("Message can't be " + data.length
                        + " bytes length. Maximum is " + MAX_LENGTH + "."
      );
    }
    if(messageType != TYPE1 && messageType != TYPE2){
      throw new IllegalArgumentException("Wrong message type");
    }
    return new Message(messageType, data);
  }
  public static byte[] getBytes(Message message){
    //ToDo: check message for correct type and data size
    
    int rawMessageLength = START_BYTES.length 
            + 4 + 4 // 4 for length size and 4 for message type
            + message.getData().length;
    byte[] rawMessage = new byte[rawMessageLength];
    int j = 0;
    for(int i = 0; i < START_BYTES.length; i++){
      rawMessage[j++] = START_BYTES[i];
    }
    byte[] type = ByteBuffer.allocate(4).putInt(message.getType()).array();
    for(int i = 0; i < type.length; i++){
      rawMessage[j++] = type[i];
    }
    byte[] length = ByteBuffer.allocate(4).putInt(message.getData().length).array();
    for(int i = 0; i < length.length; i++){
      rawMessage[j++] = length[i];
    }
    byte[] data = message.getData();
    for(int i = 0; i < data.length; i++){
      rawMessage[j++] = data[i];
    }
    return rawMessage;
  }
  public static String toString(Message message){
    StringBuilder sb = new StringBuilder();
    String delimeter = " ";
    String nl = System.getProperty("line.separator");
    byte[] bytes = Message.getBytes(message);
    sb.append("First bytes: ");
    for(int i = 0; i < START_BYTES.length; i++){
      sb.append(bytes[i]);sb.append(delimeter);
    }
    sb.append(nl);
    sb.append("Type: ");
    sb.append(ByteBuffer.wrap(bytes, 2, 4).getInt());
    sb.append(nl);
    sb.append("Length: ");
    sb.append(ByteBuffer.wrap(bytes, 6, 4).getInt());
    sb.append(nl);
    sb.append("Data: ");
    for(int i = 10; i < bytes.length; i++){
      sb.append(bytes[i]);
      sb.append(delimeter);
    }
    return sb.toString();
  }
  public static Message readMessage(InputStream in)
          throws IllegalArgumentException{//Better to create own Exception
    byte[] buffer = new byte[MAX_LENGTH];// Not the most optimized approach
    try{
      in.read(buffer, 0, START_BYTES.length);//Block Thread here
      if(!Arrays.equals(
              Arrays.copyOfRange(buffer, 0, START_BYTES.length),
              START_BYTES)){
        throw new IllegalArgumentException(
                "Message first bytes must be " + Arrays.toString(START_BYTES)
        );
      }
      // Offset is 0 because this thread is not markable
      in.read(buffer, 0, 4);//Block Thread here
      int messageType = ByteBuffer.wrap(buffer, 0, 4).getInt();
      if(messageType != TYPE1 && messageType != TYPE2){
        throw new IllegalArgumentException("Wrong message type: " + messageType + ".");
      }
      in.read(buffer, 0, 4);//Block Thread here
      int messageLength = ByteBuffer.wrap(buffer, 0, 4).getInt();
      if(messageLength > MAX_LENGTH){
        throw new IllegalArgumentException(
                "Message can't be " + messageLength
                        + " bytes length. Maximum is " + MAX_LENGTH + "."
        );
      }
      in.read(buffer, 0, messageLength);//Can end before messageLength
      return new Message(messageType, Arrays.copyOfRange(buffer, 0, messageLength));
    }
    catch(IOException ex){
      throw new IllegalArgumentException("Can't read message", ex);
    }
  }
  
  protected final byte[] data;
  protected final int type;
  protected Message(int type, byte[] data){
    this.data = data;
    this.type = type;
  }

  public byte[] getData() {
    return data;
  }

  public int getType() {
    return type;
  }
  
}
