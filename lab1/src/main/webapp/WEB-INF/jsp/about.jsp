<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<%-- Include Bootstrap --%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">


<html>
<head>
    <title>About us</title>
</head>
<body>
<jsp:include page="nav-bar.jsp" />

<div class="container mt-5 mb-5">
    <h1 class="text-center">About Classical Library</h1>

    <div class="row justify-content-center">
        <div class="col-md-6">
            <img src="${pageContext.request.contextPath}/library-2.jpg" alt="Library"
                 width="100%">
        </div>
    </div>

    <h3>Our Mission</h3>
    <p>Classical Library is dedicated to fostering a love of learning, providing access to information,
        and serving as a community hub. We strive to create a welcoming and inclusive environment
        where everyone can explore their curiosity, expand their knowledge, and connect with others.</p>

    <h3>Our Vision</h3>
    <p>We envision a community where individuals of all ages and backgrounds have the opportunity to reach their full
        potential through access to books, resources, and programs.</p>

    <h3>Our History</h3>
    <p>Classical Library was established in 1990. Over the years, we have grown and evolved to meet
        the changing needs of our community. We are proud of our rich history and our
        commitment to serving the public.</p>

</div>

</body>
</html>
