package ru.kpfu.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alexander Ferenets (Istamendil) <ist.kazan@gmail.com>
 */
public class HelloServlet extends HttpServlet{
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    out.println(getPageCode(""));
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String name = req.getParameter("name");
    if(name.isEmpty()){
      name = "Stranger";
    }
    String greetings = "<h1>Hello "+name+"!</h1>";
    
    PrintWriter out = resp.getWriter();
    out.println(getPageCode(greetings));
  }
  
  protected String getPageCode(String content){
    return "<!DOCTYPE html><html>"
            + "<head><meta charset='UTF-8'><title>Hello page</title></head>"
            + "<body>"
            + "<form action='' method='POST'><input type='text' name='name'><input type='submit' value='Say hello'></form>"
            + content
            + "</body></html>";
  }
}
