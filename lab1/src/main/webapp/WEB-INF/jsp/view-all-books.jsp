<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<%-- Include Bootstrap --%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

<html>
<head>
    <title>All books</title>
</head>
<body>

<div class="container mt-5">
    <h1 class="text-center mb-3">All books</h1>

    <c:if test="${empty books}">
        <p class="text-center">No books!</p>
    </c:if>
    <c:if test="${not empty books}">
        <table class="table table-striped">
            <thead>
            <tr>
                <td><b>ID</b></td>
                <td><b>ISBN</b></td>
                <td><b>Title</b></td>
                <td><b>Authors</b></td>
                <td><b>Number of pages</b></td>
                <td><b>Publishing year</b></td>
                <td><b>Quantity</b></td>
            </tr>
            </thead>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/book/${book.id}">${book.id}</a>
                    </td>
                    <td>${book.isbn}</td>
                    <td>${book.title}</td>
                    <td>${book.authors}</td>
                    <td>${book.numberOfPages}</td>
                    <td>${book.publishingYear}</td>
                    <td>${book.quantity}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>


</body>
</html>