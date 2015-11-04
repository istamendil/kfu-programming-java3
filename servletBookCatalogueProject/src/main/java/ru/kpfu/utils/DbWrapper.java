package ru.kpfu.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import ru.kpfu.exceptions.DbException;

/**
 *
 * @author Alexander Ferenets (Istamendil) <ist.kazan@gmail.com>
 */
public class DbWrapper {
  private final static String DRIVER = "com.mysql.jdbc.Driver";
  private final static String CONNECTION_URI = "jdbc:mysql://127.0.0.1:3306/book_catalogue";
  private final static String LOGIN = "projects";
  private final static String PASSWORD = "lamp";
  
  private static Connection conn;
  
  
  public static Connection getConnection() throws DbException{
    if(conn == null){
      try{
        Class.forName(DRIVER);
        conn = DriverManager.getConnection(CONNECTION_URI, LOGIN, PASSWORD);
      }
      catch(ClassNotFoundException ex){
        throw new DbException("Can't find DB driver.");
      } catch (SQLException ex) {
        throw new DbException("Can't connect to DB (" + ex.getErrorCode() + ": " + ex.getMessage() + ").");
      }
      
    }
    return conn;
  }
}
