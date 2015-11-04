package ru.kpfu.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.kpfu.exceptions.DbException;
import ru.kpfu.repositories.BookRepository;

/**
 * Delete books servlet
 * @author Alexander Ferenets (Istamendil) <ist.kazan@gmail.com>
 */
public class BookRemoveServlet extends HttpServlet{
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // Notices for response
    ArrayList<String> notices = new ArrayList<>();
    try{
      //trye to remove entity with its repository
      BookRepository.remove(Integer.valueOf(req.getParameter("id")));
    }
    catch(DbException ex){
      notices.add("Can't delete book.");
    }
    
    // Generate response - JSON mime type
    resp.setContentType("application/json");
    PrintWriter writer = resp.getWriter();
    // Object with one field - array of notices
    writer.print("{\"notices\":[");
    if(notices.size() > 0){
      writer.print("\"");
      writer.print(String.join("\",\"", notices));
      writer.print("\"");
    }
    writer.print("]}");
  }
}
