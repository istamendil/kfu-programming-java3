package ru.kpfu.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ru.kpfu.entities.Book;
import ru.kpfu.exceptions.DbException;
import ru.kpfu.utils.DbWrapper;

/**
 *
 * @author Alexander Ferenets (Istamendil) <ist.kazan@gmail.com>
 */
public class BookRepository {
  /**
   * Add new book to DB
   * @param book
   * @throws DbException 
   */
  public static void add(Book book) throws DbException{
    Connection conn = DbWrapper.getConnection();
    try {
      // Using Prepare statement instead simple statement 
      // and generating SQL from parameters ourselves is important due to security reasons (SQL injection)
      PreparedStatement st = conn.prepareStatement("INSERT INTO `books` SET `name`=?, `year`=?, `annotation`=?");
      st.setString(1, book.getName());
      st.setInt(2, book.getYear());
      st.setString(3, book.getAnnotation());
      st.execute();
    } catch (SQLException ex) {
      throw new DbException();
    }    
  }
  
  /**
   * Get ALL books from DB
   * @return
   * @throws DbException 
   */
  public static List<Book> getAll() throws DbException{
    ArrayList<Book> books = new ArrayList<>();
    Connection conn = DbWrapper.getConnection();
    try {
      ResultSet r = conn.createStatement().executeQuery("select * from `books`");
      while(r.next()){
        books.add(new Book(r.getInt("id"), r.getString("name"), r.getInt("year"), r.getString("annotation")));
      }
    } catch (SQLException ex) {
      throw new DbException();
    }
    return books;
  }
  
  /**
   * Remove book with exact id
   * @param id
   * @throws DbException 
   */
  public static void remove(int id) throws DbException{
    Connection conn = DbWrapper.getConnection();
    try {
      // Using Prepare statement instead simple statement 
      // and generating SQL from parameters ourselves is important due to security reasons (SQL injection)
      PreparedStatement st = conn.prepareStatement("delete from `books` where `id`=?");
      st.setInt(1, id);
      st.execute();
    } catch (SQLException ex) {
      throw new DbException();
    }
  }
}
