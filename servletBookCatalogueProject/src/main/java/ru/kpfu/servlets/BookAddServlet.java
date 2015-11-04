package ru.kpfu.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.kpfu.entities.Book;
import ru.kpfu.exceptions.DbException;
import ru.kpfu.repositories.BookRepository;

/**
 *
 * @author Alexander Ferenets (Istamendil) <ist.kazan@gmail.com>
 */
public class BookAddServlet extends HttpServlet{
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // Was some book added?
    if(req.getParameter("status") != null){
      if(req.getParameter("status").equals("1")){
        req.setAttribute("message", "Book has been created.");
      }
    }
    // Show form and status message
    getServletContext().getRequestDispatcher("/WEB-INF/views/book_add.jsp").forward(req, resp);
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // Was form filled?
    if(     req.getParameter("name") != null &&
            req.getParameter("year") != null &&
            req.getParameter("annotation") != null
            ){
      String name = req.getParameter("name");
      int    year = Integer.valueOf(req.getParameter("year"));
      String annotation = req.getParameter("annotation");

      try{
        // Also here can be validation of form fields!
        
        // Try to create simple entity object
        Book book = new Book(name, year, annotation);
        // ... and add it to DB with our Repository class
        BookRepository.add(book);
        // In case of succes ask browser to get this page again 
        // so it won't be able to resend data again !
        resp.sendRedirect(req.getRequestURI()+"?status=1");
        // End method execution
        return;
      }
      catch(DbException ex){
        req.setAttribute("message", "Error with DB has been occured.");
      }
    }
    else{
      req.setAttribute("message", "You have to fill all form fields.");
    }
    // Show form. We will be here in case of having some errors only
    getServletContext().getRequestDispatcher("/WEB-INF/views/book_add.jsp").forward(req, resp);
  }
}
