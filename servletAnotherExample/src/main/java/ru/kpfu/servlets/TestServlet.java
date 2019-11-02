package ru.kpfu.servlets;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class TestServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if(req.getParameter("t1") != null){
      List<String> errors = new ArrayList<>();
      if(req.getParameter("t1").equals("abc")){
        errors.add("T1 must not be equal \"abc\"");
      }
      if(errors.size()>0){
        req.setAttribute("errors", errors);
        req.setAttribute("t1", req.getParameter("t1"));
      }
      else{
        //write to db
      }
    }
    getServletContext().getRequestDispatcher("/main.jsp").forward(req, resp);
  }
  
}
