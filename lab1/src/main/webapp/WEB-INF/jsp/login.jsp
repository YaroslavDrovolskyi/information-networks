<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>${sessionScope.user.id}</p>
    <p>${sessionScope.user.phoneNumber}</p>
</body>
</html>
