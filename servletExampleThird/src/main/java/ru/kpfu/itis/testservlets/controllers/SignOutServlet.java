package ru.kpfu.itis.testservlets.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.kpfu.itis.testservlets.services.SecurityService;

@WebServlet("/signout")
public class SignOutServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    SecurityService.signOut(req);
    resp.sendRedirect(getServletContext().getContextPath() + "/");
  }
  

}
