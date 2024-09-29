<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <p>${sessionScope.authenticatedUser.id}</p>
    <p>${sessionScope.authenticatedUser.login}</p>
    <p>${sessionScope.authenticatedUser.name}</p>
    <p>${sessionScope.authenticatedUser.surname}</p>
    <p>${sessionScope.authenticatedUser.patronymic}</p>
    <p>${sessionScope.authenticatedUser.phoneNumber}</p>
    <p>${sessionScope.authenticatedUser.role}</p>
</body>
</html>
