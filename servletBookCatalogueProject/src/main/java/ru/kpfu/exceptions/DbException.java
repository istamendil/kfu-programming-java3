package ru.kpfu.exceptions;

/**
 *
 * @author Alexander Ferenets (Istamendil) <ist.kazan@gmail.com>
 */
public class DbException extends Exception{

  
  public DbException(){
    super();
  }
  
  public DbException(String message) {
    super(message);
  }

}
