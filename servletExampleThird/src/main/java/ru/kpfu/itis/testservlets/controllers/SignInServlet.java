package ru.kpfu.itis.testservlets.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.kpfu.itis.testservlets.services.SecurityService;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    getServletContext().getRequestDispatcher("/WEB-INF/views/signin.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String email = req.getParameter("email");
    String password = req.getParameter("password");
    
    if(email != null && password != null){
      if(SecurityService.signIn(req, email, password)){
        resp.sendRedirect(getServletContext().getContextPath() + "/profile");
        return;
      }
    }
    req.setAttribute("email", req.getParameter("email"));
    getServletContext().getRequestDispatcher("/WEB-INF/views/signin.jsp").forward(req, resp);
  }
  
  

}
