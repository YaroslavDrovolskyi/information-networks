<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!-- File, in which this file will be included should have linked Bootstrap -->

<html>
<body>


        <c:choose>


            <c:when test="${empty sessionScope.authenticatedUser.role}"> <!-- Not authenticated user -->
                <nav class="navbar navbar-expand bg-light ps-5 pe-5">
                    <a href="${pageContext.request.contextPath}/" class="navbar-brand">Home</a>

                    <div class="navbar-nav flex-row">
                        <a href="${pageContext.request.contextPath}/books" class="nav-item nav-link p-2">
                            <button class="btn btn-primary"> Books </button>
                        </a>
                    </div>

                    <div class="navbar-nav flex-row">
                        <a href="${pageContext.request.contextPath}/login" class="nav-item nav-link p-2">
                            <button class="btn btn-primary"> Login </button>
                        </a>
                    </div>
                </nav>
            </c:when>


            <c:when test="${sessionScope.authenticatedUser.role == 'CUSTOMER'}"> <!-- User is CUSTOMER -->
                <nav class="navbar navbar-expand bg-light ps-5 pe-5">
                    <a href="${pageContext.request.contextPath}/" class="navbar-brand">Home</a>

                    <div class="navbar-nav flex-row">
                        <a href="${pageContext.request.contextPath}/books" class="nav-item nav-link p-2">
                            <button class="btn btn-primary"> Books </button>
                        </a>
                    </div>

                    <div class="navbar-nav flex-row">
                        <a href="${pageContext.request.contextPath}/bookOrders" class="nav-item nav-link p-2">
                            <button class="btn btn-primary"> My book orders </button>
                        </a>
                    </div>

                    <div class="navbar-nav flex-row">
                        <a href="${pageContext.request.contextPath}/user/${sessionScope.authenticatedUser.id}"
                           class="nav-item nav-link p-2 mb-0">
                            <button class="btn btn-primary"> Profile (${sessionScope.authenticatedUser.login}) </button>
                        </a>
                    </div>

                    <jsp:include page="logout-form.jsp" />
                </nav>
            </c:when>


            <c:when test="${sessionScope.authenticatedUser.role == 'ADMIN'}"> <!-- User is ADMIN -->
                <nav class="navbar navbar-expand bg-light ps-5 pe-5">
                    <a href="${pageContext.request.contextPath}/" class="navbar-brand">Home</a>

                    <div class="navbar-nav flex-row">
                        <a href="${pageContext.request.contextPath}/books" class="nav-item nav-link p-2">
                            <button class="btn btn-primary"> Books </button>
                        </a>
                    </div>

                    <div class="navbar-nav flex-row">
                        <a href="${pageContext.request.contextPath}/book/create" class="nav-item nav-link p-2">
                            <button class="btn btn-primary"> Create book </button>
                        </a>
                    </div>

                    <div class="navbar-nav flex-row">
                        <a href="${pageContext.request.contextPath}/bookOrders" class="nav-item nav-link p-2">
                            <button class="btn btn-primary"> All book orders </button>
                        </a>
                    </div>

                    <div class="navbar-nav flex-row">
                        <a href="${pageContext.request.contextPath}/user/${sessionScope.authenticatedUser.id}"
                           class="nav-item nav-link p-2 mb-0">
                            <button class="btn btn-primary"> Profile (${sessionScope.authenticatedUser.login}) </button>
                        </a>
                    </div>

                    <jsp:include page="logout-form.jsp" />
                </nav>
            </c:when>


            <c:when test="${sessionScope.authenticatedUser.role == 'OWNER'}"> <!-- User is OWNER -->
                <nav class="navbar navbar-expand bg-light ps-5 pe-5">
                    <a href="${pageContext.request.contextPath}/" class="navbar-brand">Home</a>

                    <div class="navbar-nav flex-row">
                        <a href="${pageContext.request.contextPath}/books" class="nav-item nav-link p-2">
                            <button class="btn btn-primary"> Books </button>
                        </a>
                    </div>

                    <div class="navbar-nav flex-row">
                        <a href="${pageContext.request.contextPath}/bookOrders" class="nav-item nav-link p-2">
                            <button class="btn btn-primary"> All book orders </button>
                        </a>
                    </div>

                    <div class="navbar-nav flex-row">
                        <a href="${pageContext.request.contextPath}/users" class="nav-item nav-link p-2">
                            <button class="btn btn-primary"> All users </button>
                        </a>
                    </div>

                    <div class="navbar-nav flex-row">
                        <a href="${pageContext.request.contextPath}/user/${sessionScope.authenticatedUser.id}"
                           class="nav-item nav-link p-2 mb-0">
                            <button class="btn btn-primary"> Profile (${sessionScope.authenticatedUser.login}) </button>
                        </a>
                    </div>

                    <jsp:include page="logout-form.jsp" />
                </nav>
            </c:when>

            
        </c:choose>

</body>
</html>
