<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<%-- Include Bootstrap --%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

<html>
<head>
  <title>All users</title>
</head>
<body>

<jsp:include page="nav-bar.jsp" />

<div class="container mt-5">
  <h1 class="text-center mb-3">All users</h1>

  <c:if test="${empty users}">
    <p class="text-center">No users!</p>
  </c:if>
  <c:if test="${not empty users}">
    <table class="table table-striped">
      <thead>
      <tr>
        <td><b>ID</b></td>
        <td><b>Login</b></td>
        <td><b>Name</b></td>
        <td><b>Surname</b></td>
        <td><b>Patronymic</b></td>
        <td><b>Phone number</b></td>
        <td><b>Role</b></td>
        <td><b>Is allowed to login</b></td>
      </tr>
      </thead>
      <c:forEach var="user" items="${users}">
        <tr>
          <td>
            <a href="${pageContext.request.contextPath}/user/${user.id}">${user.id}</a>
          </td>
          <td><c:out value="${user.login}"/></td>
          <td><c:out value="${user.name}"/></td>
          <td><c:out value="${user.surname}"/></td>
          <td><c:out value="${user.patronymic}"/></td>
          <td><c:out value="${user.phoneNumber}"/></td>
          <td><c:out value="${user.role}"/></td>
          <td><c:out value="${user.isAllowedToLogin}"/></td>
        </tr>
      </c:forEach>
    </table>
  </c:if>
</div>


</body>
</html>