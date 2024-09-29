<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<%-- Include Bootstrap --%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

<html>
<head>
    <title>User</title>
</head>
<body>

<jsp:include page="nav-bar.jsp" />

<div class="container mt-5">
    <h1 class="text-center mb-3">User</h1>

    <c:if test="${empty user}">
        <p class="text-center">User with ID = ${requestedUserId} does not exist</p>
    </c:if>
    <c:if test="${not empty user}">
        <%-- Print propertiees of user object --%>
        <div class="row justify-content-center">
            <div class="col text-end">
                <b>ID</b>
            </div>
            <div class="col">
                    ${user.id} <%-- Attribute, that was passed from backend --%>
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col text-end">
                <b>Login</b>
            </div>
            <div class="col">
                <c:out value="${user.login}"/>
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col text-end">
                <b>Name</b>
            </div>
            <div class="col">
                <c:out value="${user.name}"/>
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col text-end">
                <b>Surname</b>
            </div>
            <div class="col">
                <c:out value="${user.surname}"/>
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col text-end">
                <b>Patronymic</b>
            </div>
            <div class="col">
                <c:out value="${user.patronymic}"/>
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col text-end">
                <b>Phone Number</b>
            </div>
            <div class="col">
                <c:out value="${user.phoneNumber}"/>
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col text-end">
                <b>Role</b>
            </div>
            <div class="col">
                <c:out value="${user.role}"/>
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col text-end">
                <b>Is allowed to login</b>
            </div>
            <div class="col">
                <c:out value="${user.isAllowedToLogin}"/>
            </div>
        </div>

    </c:if>
</div>

</body>
</html>

