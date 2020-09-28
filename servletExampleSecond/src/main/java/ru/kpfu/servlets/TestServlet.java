package ru.kpfu.servlets;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * More complex example. It looks more like real code.
 *
 * It also can be mapped to "/" and become "Default servlet"
 * Default servlet handles any request that is not handled by another servlets.
 * So static files won't be served. For them "File Servlet" can be implemented.
 * It must handle any request that looks like request for static content:
 * try to find file and read it from drive, write to response.
 */
@WebServlet("")
public class TestServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String notice = null;
    String t1 = req.getParameter("t1");
    List<String> errors = new ArrayList<>();


    // Has form been sent?
    if(t1 != null){
      // Check all params
      if(t1.equals("abc")){
        errors.add("T1 must not be equal \"abc\"");
      }
      if(errors.size()>0){
        //Some problems
        resp.getWriter().print(generateOutput(t1, notice, errors));
        return;
      }
      else{
        //Everything is good.
        //Try to process data (write to DB here)
        notice = "Info has been written to DB.";
        resp.getWriter().print(generateOutput(t1, notice, errors));
        return;
      }
    }
    //Generate initial HTML
    resp.getWriter().print(generateOutput(t1, notice, errors));
  }

  protected String generateOutput(String t1, String notice, List<String> errors){
    StringBuilder sb = new StringBuilder();
    sb.append("<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>Servlet Example</title>\n" +
            "</head>\n" +
            "<body>\n");
    if(notice != null){
      sb.append("<div style=\"color:green;font-size:30px;\">" + notice + "</div>\n");
    }
    String t1HtmlString;
    if(t1 != null && !t1.isEmpty()) {
      t1HtmlString = "value=\""+t1+"\"";
    }
    else{
      t1HtmlString ="";
    }
    sb.append("  <form action=\"\" method=\"GET\">\n" +
            "      <input type=\"text\" name=\"t1\" " + t1HtmlString + "><br>\n");
    if(!errors.isEmpty()) {
      for (String error : errors) {
        sb.append("<div style=\"color:red\"> " + error + " </div>");
      }
    }
    sb.append(
            "  </form>\n" +
            "</body>\n" +
            "</html>\n");

    return sb.toString();
  }
  
}
