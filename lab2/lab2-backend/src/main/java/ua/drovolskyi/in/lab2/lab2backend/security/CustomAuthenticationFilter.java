package ua.drovolskyi.in.lab2.lab2backend.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final Logger log = LogManager.getLogger();

    protected final Set<String> notProtectedUrls;

    /**
     *
     * @param authenticationManager
     * @param notProtectedUrls is set of URLs that users should not be authenticated to interact with them
     */
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager,
                                      Set<String> notProtectedUrls) {
        super(authenticationManager);
        this.notProtectedUrls = notProtectedUrls;
    }


    // Authorization header contains: "username password"

    // all authentication is performed in attemptAuthentication() method, that is fully defined in parent class
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String requestedUrl = request.getRequestURI();

        if(notProtectedUrls.contains(requestedUrl)){
            return UsernamePasswordAuthenticationToken.authenticated(
                    " ",
                    " ",
                    List.of(new SimpleGrantedAuthority("ANONYMOUS"))
            );
        }

        return super.attemptAuthentication(request, response);
    }


    @Override
    protected String obtainUsername(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null){
            return null;
        }

        String[] splittedHeader = authHeader.split(" ");
        return splittedHeader[0];
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null){
            return null;
        }

        String[] splittedHeader = authHeader.split(" ");
        if(splittedHeader.length < 2){
            return null;
        }
        return splittedHeader[1];
    }
}