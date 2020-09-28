package ru.kpfu.itis.testservlets.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class MainServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("email", req.getSession().getAttribute("email"));
    getServletContext().getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);
  }

}
