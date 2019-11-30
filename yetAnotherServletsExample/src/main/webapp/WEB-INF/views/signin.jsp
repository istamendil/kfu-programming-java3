<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/views/_header.jsp" %>

<form action="<c:url value="signin"/>" method="POST">
  <input name="email" type="email" placeholder="E-MAIL"<c:if test="${not empty email}"> value="<c:out value="${email}"/>"</c:if>><br>
  <input name="password" type="password" placeholder="Password"><br>
  <input type="submit" value="Sing In">
</form>

<%@include file="/WEB-INF/views/_footer.jsp" %>

