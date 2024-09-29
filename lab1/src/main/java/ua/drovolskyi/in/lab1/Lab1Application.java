package ua.drovolskyi.in.lab1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.drovolskyi.in.lab1.entities.Book;
import ua.drovolskyi.in.lab1.repositories.BookRepository;

@SpringBootApplication
public class Lab1Application {


    public static void main(String[] args) {
        SpringApplication.run(Lab1Application.class, args);
    }

}

/*
TODO:
XSS attack protection
Template UI items (top panel etc.)
static web-pages ("about company" page (maybe with local images))
add icon
Logout - must be POST request

Errors to handle: AccessDeniedException, IllegalArfumentException

 */

/*
About OAuth:
- https://auth0.com/intro-to-iam/what-is-oauth-2
- https://www.baeldung.com/spring-security-5-oauth2-login
- https://github.com/ariphidayat/springmvc-oauth2-example
 */


/*
https://www.baeldung.com/spring-boot-jsp

// The JSP executes as a servlet on the server side.


${requestScope.attribute} or ${attribute}
Attribute is attribute added in controller by model.addAttribute() method

 <a href="${pageContext.request.contextPath}/books" - link
 */

// how to return .jsp: https://www.youtube.com/watch?v=BSPq2YgIwwE

// In controller functions, input Model (or ModeAndView) object (as seen in some guides) is unnecessary
