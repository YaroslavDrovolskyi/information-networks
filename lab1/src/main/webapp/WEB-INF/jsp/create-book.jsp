<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="java.time.Year"%>

<%-- Include Bootstrap --%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

<html>
<head>
    <title>Create book</title>
</head>
<body>

<div class="container mt-5">
    <h1 class="text-center mb-3">Create book</h1>

    <form action="${pageContext.request.contextPath}/book/create"
          method="POST" id="createBookForm" role="form">

    <%-- <input type="hidden" id="createBookAction" name="createBookAction"
               value="createBookId" /> --%>
        <div class="form-group container">

            <div class="row mb-3">
                <label class="col-2 text-end">ISBN</label>
                <input name="isbn" type="text"
                       pattern="^(?!\s*$).+" minLength="10" maxLength="20" class="form-control col"/>
            </div>

            <div class="row mb-3">
                <label class="col-2 text-end">Title</label>
                <input name="title" type="text" required
                       pattern="^(?!\s*$).+" minLength="5" maxLength="50" class="form-control col"/>
            </div>

            <div class="row mb-3">
                <label class="col-2 text-end">Authors</label>
                <input name="authors" type="text" required
                       pattern="^(?!\s*$).+" minLength="5" maxLength="100" class="form-control col"/>
            </div>

            <div class="row mb-3">
                <label class="col-2 text-end">Number of pages</label>
                <input name="numberOfPages" type="number" required
                       min="1" max="100000" step="1" value="1" class="form-control col"/>
            </div>

            <div class="row mb-3">
                <label class="col-2 text-end">Publishing year</label>
                <input name="publishingYear" type="number" required
                       min="1800" max="<%=Year.now().getValue() %>" step="1"
                       value="<%=Year.now().getValue() %>" class="form-control col"/>
            </div>

            <div class="row mb-3 d-flex align-items-center justify-content-center">
                <div class="col-3 text-center">
                    <input type="submit" value="Create!" class="btn btn-success form-control mt-3"/>
                </div>
            </div>
            
        </div>
    </form>



</div>



</body>
</html>
