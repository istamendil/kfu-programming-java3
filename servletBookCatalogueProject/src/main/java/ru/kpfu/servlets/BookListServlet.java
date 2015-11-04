package ru.kpfu.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.kpfu.exceptions.DbException;
import ru.kpfu.repositories.BookRepository;

/**
 *
 * @author Alexander Ferenets (Istamendil) <ist.kazan@gmail.com>
 */
public class BookListServlet extends HttpServlet{
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try{
      req.setAttribute("books", BookRepository.getAll());
    }
    catch(DbException ex){
      req.setAttribute("message", "Error with DB: " + ex.getMessage());
    }
    getServletContext().getRequestDispatcher("/WEB-INF/views/book_list.jsp").forward(req, resp);
  }
}
