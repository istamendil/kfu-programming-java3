<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset='UTF-8'>
    <title>Books Form</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">
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
      <%-- Show some message about adding entity --%>
      <c:if test="${not empty message}">
        <h3>${message}</h3>
      </c:if>
      <%-- Show form --%>
      <form action="" method="POST" class="form-horizontal">
        <div class="form-group">
          <label for="name" class="col-sm-2 control-label">Name</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="name" placeholder="Name" name="name">
          </div>
        </div>
        <div class="form-group">
          <label for="year" class="col-sm-2 control-label">Year</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="year" placeholder="Year published" name="year">
          </div>
        </div>
        <div class="form-group">
          <label for="annotation" class="col-sm-2 control-label">Annotation</label>
          <div class="col-sm-10">
            <textarea class="form-control" id="annotation" name="annotation"></textarea>
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">Save</button>
          </div>
        </div>
      </form>
    </div>
  </body>
</html>
