<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<%-- Include Bootstrap --%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">


<html>
<head>
    <title>Home</title>
</head>
<body>
    <jsp:include page="nav-bar.jsp" />

    <div class="container mt-5">
        <h1 class="text-center">Welcome to Classical Library!</h1>

        <div class="row justify-content-center">
            <div class="col-md-6">
                <img src="${pageContext.request.contextPath}/library-1.jpg" alt="Library"
                     width="100%">
            </div>
        </div>


        <p>Explore Our Collections From bestsellers to rare finds, our extensive collections
            include fiction, non-fiction, audiobooks, and digital resources.
            Whether you're a student, researcher, or casual reader, you'll find something that piques your interest.</p>

        <p>Engage with Programs and Events Join us for a variety of programs, including author talks, workshops,
            storytime sessions for kids, and book clubs. Stay connected with our calendar to participate in events
            that spark your creativity and expand your horizons.</p>

        <p>Access Resources Anytime, Anywhere Our digital library allows you to borrow e-books, audiobooks, and research
            databases from the comfort of your home. With just a few clicks, you can
            access a wealth of information at your fingertips.</p>

        <p>  Visit Us We invite you to visit our welcoming space, where knowledgeable staff are ready
            to assist you. Whether you need help finding a book or accessing online resources,
            we’re here to support your journey.</p>

        <p> Join Our Community At Classical Library, everyone is welcome.
            Explore, learn, and connect with fellow community members. We can’t wait to see you!</p>

        <p>For more information, check out our website or visit us in person.
            Your adventure starts here!</p>
    </div>

    <div class="container text-center mt-5 mb-5">
        <a href="${pageContext.request.contextPath}/about">
            About us
        </a>
    </div>




</body>
</html>
