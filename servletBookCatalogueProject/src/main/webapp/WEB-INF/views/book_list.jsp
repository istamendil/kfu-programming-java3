<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset='UTF-8'>
    <title>Books list</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">
    <script src="<c:url value="/js/jquery-2.1.4.min.js" />"></script>
    <script src="<c:url value="/js/script.js" />"></script>
  </head>
  <body>
    <!-- Navigation - special Bootstrap element -->
    <nav class="navbar navbar-default">
      <div class="container-fluid">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav">
            <li><a href="<c:url value="/"/>">Home</a></li>
            <li><a href="<c:url value="/book/add"/>">Add book</a></li>
            <li><a href="<c:url value="/book/list"/>">Book list</a></li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="wrapper">
      <div class="book-catalogue">
        <c:choose>
          <%-- Do we have books? --%>
          <c:when test="${fn:length(books) gt 0}">
            <%-- Go throught Books collection --%>
            <c:forEach items="${books}" var="book">
              <div class="book">
                <h5 class="title">
                  ${book.getName()}
                  <a href="<c:url value="/book/remove?id=${book.getId()}" />" class="remove_book_button">x</a>
                </h5>
                <div class="year">${book.getYear()}</div>
                <div class="annotation">${book.getAnnotation()}</div>
              </div>
            </c:forEach>
          </c:when>
          <%-- If collection is empty write about this --%>
          <c:otherwise>
            <h3>No books to display</h3>
          </c:otherwise>
        </c:choose>
      </div>
    </div>
  </body>
</html>
