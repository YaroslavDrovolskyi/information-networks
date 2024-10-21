package ua.drovolskyi.in.lab2.lab2backend.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.drovolskyi.in.lab2.lab2backend.entities.User;
import ua.drovolskyi.in.lab2.lab2backend.repositories.UserRepository;


import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        if(username.equals("") || password.equals("")){
            throw new AuthenticationCredentialsNotFoundException("Username or password is not present");
        }


        User user = userRepository.findByUsername(username).orElse(null);

        if(user == null){
            throw new UsernameNotFoundException(
                    String.format("User with username='%s' does not exist", username));
        }

        if(!user.getIsAllowedToLogin()){
            throw new DisabledException("User is not allowed to login");
        }

        if(!passwordEncoder.matches(password, user.getPasswordHash())){
            throw new BadCredentialsException("Password is incorrect");
        }


        // create and return authentication token with role
        return UsernamePasswordAuthenticationToken.authenticated(
                username,
                password,
                List.of(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

/*
    Sources:
    - https://www.baeldung.com/spring-security-authentication-provider
    - https://docs.spring.io/spring-security/site/docs/4.0.x/apidocs/org/springframework/security/authentication/AuthenticationProvider.html
 */