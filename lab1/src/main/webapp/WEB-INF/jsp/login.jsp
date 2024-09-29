<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page session="true" %>

<%-- Include Bootstrap --%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

<html>
<head>
    <title>Login</title>
</head>
<body>

<jsp:include page="nav-bar.jsp" />

    <div class="container mt-5">
        <h1 class="text-center mb-3">Login</h1>
        <p class="text-center mb-3"><c:out value="${prevAuthAttemptError}"/></p>

        <form action="${pageContext.request.contextPath}/login"
              method="POST" id="login" role="form">
            <div class="form-group container">

                <div class="row mb-3">
                    <label class="col-2 text-end">Login</label>
                    <input name="login" type="text" required placeholder="login"
                           pattern="^(?!\s*$).+" minLength="5" maxLength="25" class="form-control col"/>
                </div>

                <div class="row mb-3">
                    <label class="col-2 text-end">Password</label>
                    <input name="password" type="password" required placeholder="password"
                           pattern="^(?!\s*$).+" minLength="8" maxLength="50" class="form-control col"/>
                </div>


                <div class="row mb-3 d-flex align-items-center justify-content-center">
                    <div class="col-3 text-center">
                        <input type="submit" value="Login" class="btn btn-success form-control"/>
                    </div>
                </div>


                <div>
                    <p class="text-center">
                        Don't have an account?
                        <a href="${pageContext.request.contextPath}/register"> Register</a>
                    </p>
                </div>

            </div>
        </form>
    </div>

</body>
</html>
