<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<%-- Include Bootstrap --%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">


<html>
<head>
    <title>Error</title>
</head>
<body>

    <jsp:include page="nav-bar.jsp" />

    <div class="container text-center mt-5 mb-5">
        <p><c:out value="${errorMessage}"/></p>
    </div>

</body>
</html>