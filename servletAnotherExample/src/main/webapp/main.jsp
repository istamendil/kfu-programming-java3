<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
  </head>
  <body>
    <form action="" method="GET">
      <input type="text" name="t1"<c:if test="${!t1.isEmpty()}">value="${t1}"</c:if>><br>
      <c:if test="${error != null}">
        <span style="color:red">${error}</span>
      </c:if>
    </form>
  </body>
</html>
