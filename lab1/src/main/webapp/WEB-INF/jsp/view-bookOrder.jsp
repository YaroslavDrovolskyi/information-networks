<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="ua.drovolskyi.in.lab1.entities.BookOrder"%>

<%-- Include Bootstrap --%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

<html>
<head>
    <title>Book order</title>
</head>
<body>

<jsp:include page="nav-bar.jsp" />

<div class="container mt-5">
    <h1 class="text-center mb-3">Book order</h1>

    <c:if test="${empty bookOrder}"> <!-- BookOrder is BookOrderDto object-->
        <p class="text-center">Book order with ID = ${requestedBookOrderId} does not exist</p>
    </c:if>
    <c:if test="${not empty bookOrder}">
        <%-- Print properties of bookOrder object --%>
        <div class="row justify-content-center">
            <div class="col text-end">
                <b>ID</b>
            </div>
            <div class="col">
                    ${bookOrder.id} <%-- Attribute, that was passed from backend --%>
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col text-end">
                <b>Book ID</b>
            </div>
            <div class="col">
                <a href="${pageContext.request.contextPath}/book/${bookOrder.bookId}">${bookOrder.bookId}</a>
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col text-end">
                <b>Book quantity</b>
            </div>
            <div class="col">
                <c:out value="${book.quantity}"/>
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col text-end">
                <b>Customer ID</b>
            </div>
            <div class="col">
                <a href="${pageContext.request.contextPath}/user/${bookOrder.customerId}">${bookOrder.customerId}</a>
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col text-end">
                <b>Status</b>
            </div>
            <div class="col">
                    ${bookOrder.status}
            </div>
        </div>
    </c:if>


    <!-- only for ADMIN -->
    <c:if test="${sessionScope.authenticatedUser.role == 'ADMIN'}">
        <c:if test="${bookOrder.status == 'NEW'}"> <!-- Satisfy BookOrder, if its status is NEW -->
            <c:if test="${book.quantity > 0}"> <!-- Can satisfy only when book quantity > 0 -->
                <div class="row justify-content-center mt-3 mb-5">
                    <form action="${pageContext.request.contextPath}/bookOrder/${bookOrder.id}/satisfy"
                          method="POST" id="satisfyBookOrderForm" role="form">
                        <div class="form-group col-xs-5 text-center">
                            <button type="submit" class="btn btn-info" value="Satisfy">Satisfy!</button>
                        </div>
                    </form>
                </div>
            </c:if>
            <c:if test="${book.quantity <= 0}"> <!-- Can't satisfy when book quantity <= 0 -->
                <div class="row justify-content-center mt-3 mb-5">
                    <p class="text-center">Can't satisfy, because book quantity is 0</p>
                </div>
            </c:if>
        </c:if>

        <c:if test="${bookOrder.status == 'SATISFIED'}"> <!-- Complete BookOrder, if its status is SATISFIED -->
            <div class="row justify-content-center mt-3 mb-5">
                <form action="${pageContext.request.contextPath}/bookOrder/${bookOrder.id}/complete"
                      method="POST" id="completeBookOrderForm" role="form">
                    <div class="form-group col-xs-5 text-center">
                        <button type="submit" class="btn btn-success" value="Complete">Complete!</button>
                    </div>
                </form>
            </div>
        </c:if>
    </c:if>

</div>

</body>
</html>

