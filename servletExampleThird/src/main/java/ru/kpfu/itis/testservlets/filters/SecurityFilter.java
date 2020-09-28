package ru.kpfu.itis.testservlets.filters;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.kpfu.itis.testservlets.services.SecurityService;

@WebFilter("/*")
public class SecurityFilter extends HttpFilter{
  protected final String[] protectedPaths = {"/profile"};

  @Override
  protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
    boolean prot= false;
    for(String path : protectedPaths){
      if(path.equals(req.getRequestURI().substring(req.getContextPath().length()))){
        prot = true;
        break;
      }
    }
    if(prot && !SecurityService.isSigned(req)){
      res.sendRedirect(req.getContextPath() + "/");
    }
    else{
      if(SecurityService.isSigned(req)){
        req.setAttribute("user", SecurityService.getUser(req));
      }
      chain.doFilter(req, res);
    }
  }
  
}
