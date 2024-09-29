<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!-- File, in which this file will be included should have linked Bootstrap -->

<html>
<body>

    <div class="navbar-nav flex-row">
        <p class="nav-item nav-link p-2">
            <form action="${pageContext.request.contextPath}/logout"
                  method="POST" id="logoutForm" role="form" class="mb-0">
                    <button type="submit" class="btn btn-danger" value="Create">Logout</button>
            </form>
        </p>
    </div>

</body>
</html>
