<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<%-- Include Bootstrap --%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">


<html>
<head>
    <title>Home</title>
</head>
<body>
    <jsp:include page="nav-bar.jsp" />

    <p>${sessionScope.authenticatedUser.id}</p>
    <p>${sessionScope.authenticatedUser.login}</p>
    <p>${sessionScope.authenticatedUser.name}</p>
    <p>${sessionScope.authenticatedUser.surname}</p>
    <p>${sessionScope.authenticatedUser.patronymic}</p>
    <p>${sessionScope.authenticatedUser.phoneNumber}</p>
    <p>${sessionScope.authenticatedUser.role}</p>
</body>
</html>
