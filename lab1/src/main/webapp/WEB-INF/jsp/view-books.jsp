<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>View Books</title>
    <!-- <link href="<c:url value="/css/common.css"/>" rel="stylesheet" type="text/css"> -->
</head>
<body>
<table>
    <thead>
    <tr>
        <th>ISBN</th>
        <th>Name</th>
        <th>Author</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${book}</td>
        <td>${book}</td>
        <td>${book}</td>
    </tr>
    </tbody>
</table>
</body>
</html>