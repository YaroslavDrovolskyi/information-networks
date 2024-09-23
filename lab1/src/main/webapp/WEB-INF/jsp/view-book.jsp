<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<%-- Include Bootstrap --%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

<html>
<head>
    <title>Book</title>
</head>
<body>

<div class="container mt-5">
    <h1 class="text-center mb-3">Book</h1>

    <c:if test="${empty book}">
        <%-- Get id parameter from GET request, on which current page is response for --%>
        <p class="text-center">Book with ID = ${param.id} does not exist</p>
    </c:if>
    <c:if test="${not empty book}">
        <%-- Print propertiees of book object --%>
        <div class="row justify-content-center"}>
            <div class="col text-end">
                <b>ID</b>
            </div>
            <div class="col">
                    ${book.id} <%-- Attribute, that was passed from backend --%>
            </div>
        </div>

        <div class="row justify-content-center"}>
            <div class="col text-end">
                <b>ISBN</b>
            </div>
            <div class="col">
                    ${book.isbn}
            </div>
        </div>

        <div class="row justify-content-center"}>
            <div class="col text-end">
                <b>Title</b>
            </div>
            <div class="col">
                    ${book.title}
            </div>
        </div>

        <div class="row justify-content-center"}>
            <div class="col text-end">
                <b>Authors</b>
            </div>
            <div class="col">
                    ${book.authors}
            </div>
        </div>

        <div class="row justify-content-center"}>
            <div class="col text-end">
                <b>Number of pages</b>
            </div>
            <div class="col">
                    ${book.numberOfPages}
            </div>
        </div>

        <div class="row justify-content-center"}>
            <div class="col text-end">
                <b>Publishing year</b>
            </div>
            <div class="col">
                    ${book.publishingYear}
            </div>
        </div>

        <div class="row justify-content-center"}>
            <div class="col text-end">
                <b>Quantity</b>
            </div>
            <div class="col">
                    ${book.quantity}
            </div>
        </div>
    </c:if>
</div>

</body>
</html>

