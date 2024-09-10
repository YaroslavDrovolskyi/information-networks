package ua.drovolskyi.in.lab1;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// This class binds our application’s Servlet, Filter and ServletContextInitializer to the runtime server,
// which is necessary for our application to run.
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Lab1Application.class);
    }

}
