<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head><meta charset='UTF-8'><title>Hello page</title></head>
  <body>
  <c:if test="${not empty message}">
    <h3>${message}</h3>
  </c:if>
  <form action='' method='POST'>
    <input type='text' name='email'><br>
    <input type='password' name='password'><br>
    <input type='radio' name='sex' value='1'> Male <input type='radio' name='sex' value='0'> Female<br>
    <input type='checkbox' name='subscription'> Subscribe?<br>
    <input type='submit' value='Say hello'>
  </form>
</body>
</html>
