package ua.drovolskyi.in.lab2.lab2backend.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());

        // Get the DispatcherServlet bean from the WebApplicationContext
        DispatcherServlet dispatcherServlet = context.getBean(DispatcherServlet.class);

        // Dispatch the request to the specified URL after successful authentication
        // It correctly according to authentication flow:
        // if user is authenticated, request should be processed by DispatcherServlet
        dispatcherServlet.service(request, response);

    }
}