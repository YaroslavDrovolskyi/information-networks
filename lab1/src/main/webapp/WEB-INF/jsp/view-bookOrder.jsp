<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<%-- Include Bootstrap --%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

<html>
<head>
  <title>Book order</title>
</head>
<body>

<div class="container mt-5">
  <h1 class="text-center mb-3">Book order</h1>

  <c:if test="${empty bookOrder}">
    <p class="text-center">Book order with ID = ${requestedBookOrderId} does not exist</p>
  </c:if>
  <c:if test="${not empty bookOrder}">
    <%-- Print propertiees of bookOrder object --%>
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
          ${bookOrder.bookId}
      </div>
    </div>

    <div class="row justify-content-center">
      <div class="col text-end">
        <b>Customer ID</b>
      </div>
      <div class="col">
          ${bookOrder.customerId}
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
</div>

</body>
</html>

