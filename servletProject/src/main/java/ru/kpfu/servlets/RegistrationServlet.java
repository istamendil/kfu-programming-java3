package ru.kpfu.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.kpfu.entities.User;
import ru.kpfu.exceptions.DbException;
import ru.kpfu.exceptions.DuplicateEntryException;
import ru.kpfu.repositories.UserRepository;

/**
 *
 * @author Alexander Ferenets (Istamendil) <ist.kazan@gmail.com>
 */
public class RegistrationServlet extends HttpServlet{
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if(req.getParameter("status") != null){
      if(req.getParameter("status").equals("1")){
        req.setAttribute("message", "User has been created.");
      }
    }
    getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String content = "";
    
    if(     req.getParameter("email") != null &&
            req.getParameter("password") != null &&
            req.getParameter("sex") != null
            ){
      String email = req.getParameter("email");
      String password = req.getParameter("password");
      boolean sex = req.getParameter("sex").equals("1")?true:false;
      boolean subscription = req.getParameter("subscription") == null?false:true;

      try{
        User user = new User(email, password, sex, subscription);
        UserRepository.add(user);
        resp.sendRedirect(req.getRequestURI()+"?status=1");
        return;
      }
      catch(DbException ex){
        req.setAttribute("message", "Error with DB has been occured.");
      }
      catch(DuplicateEntryException ex){
        req.setAttribute("message", "User with such email already exists.");
      }
    }
    else{
      req.setAttribute("message", "You have to fill all form fields.");
    }
    
    getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
  }
}
