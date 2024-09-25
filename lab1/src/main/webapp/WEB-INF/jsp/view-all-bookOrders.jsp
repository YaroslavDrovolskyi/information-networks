<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<%-- Include Bootstrap --%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

<html>
<head>
    <title>All book orders</title>
</head>
<body>

<div class="container mt-5">
    <h1 class="text-center mb-3">All book orders</h1>

    <c:if test="${empty bookOrders}">
        <p class="text-center">No book orders!</p>
    </c:if>
    <c:if test="${not empty bookOrders}">
        <table class="table table-striped">
            <thead>
            <tr>
                <td><b>ID</b></td>
                <td><b>Book ID</b></td>
                <td><b>Customer ID</b></td>
                <td><b>Status</b></td>
            </tr>
            </thead>
            <c:forEach var="bookOrder" items="${bookOrders}">
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/bookOrder/${bookOrder.id}">${bookOrder.id}</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/book/${bookOrder.bookId}">${bookOrder.bookId}</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/user/${bookOrder.customerId}">${bookOrder.customerId}</a>
                    </td>
                    <td>${bookOrder.status}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>


</body>
</html>