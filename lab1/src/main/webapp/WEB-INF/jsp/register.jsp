<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page session="true" %>

<%-- Include Bootstrap --%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

<html>
<head>
    <title>Registration</title>
</head>
<body>

<jsp:include page="nav-bar.jsp" />

<div class="container mt-5">
    <h1 class="text-center mb-3">Registration</h1>
    <p class="text-center mb-3"><c:out value="${prevRegisterAttemptError}"/></p>

    <form action="${pageContext.request.contextPath}/register"
          method="POST" id="register" role="form">
        <div class="form-group container">

            <div class="row mb-3">
                <label class="col-2 text-end">Login</label>
                <input name="login" type="text" required placeholder="login"
                       pattern="^\S+$" minLength="5" maxLength="25" class="form-control col"/>
            </div>

            <div class="row mb-3">
                <label class="col-2 text-end">Password</label>
                <input name="password" type="password" required placeholder="password"
                       pattern="^\S+$" minLength="8" maxLength="50" class="form-control col"/>
            </div>

            <div class="row mb-5">
                <label class="col-2 text-end">Repeat password</label>
                <input name="passwordRepeat" type="password" required placeholder="password"
                       pattern="^\S+$" minLength="8" maxLength="50" class="form-control col"/>
            </div>
            

            <div class="row mb-3 mt-3">
                <label class="col-2 text-end">Name</label>
                <input name="name" type="text" required
                       pattern="^(?!\s*$).+" minLength="2" maxLength="50" class="form-control col"/>
            </div>

            <div class="row mb-3">
                <label class="col-2 text-end">Surname</label>
                <input name="surname" type="text" required
                       pattern="^(?!\s*$).+" minLength="2" maxLength="50" class="form-control col"/>
            </div>

            <div class="row mb-3">
                <label class="col-2 text-end">Patronymic</label>
                <input name="patronymic" type="text" required
                       pattern="^(?!\s*$).+" minLength="2" maxLength="50" class="form-control col"/>
            </div>

            <div class="row mb-3">
                <label class="col-2 text-end">Phone number</label>
                <input name="phoneNumber" type="text" required placeholder="+380---------"
                       pattern="^\+380\d{2}\d{3}\d{2}\d{2}$" minLength="13" maxLength="13" class="form-control col"/>
            </div>


            <div class="row mb-3 d-flex align-items-center justify-content-center">
                <div class="col-3 text-center">
                    <input type="submit" value="Register" class="btn btn-success form-control"/>
                </div>
            </div>


            <div>
                <p class="text-center">
                    Already have account?
                    <a href="${pageContext.request.contextPath}/login"> Login</a>
                </p>
            </div>

        </div>
    </form>
</div>

</body>
</html>
