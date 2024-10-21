package ua.drovolskyi.in.lab2.lab2backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Set;

import static org.springframework.security.config.Customizer.withDefaults;

/*
    Note, that multiple calling @Bean method DON'T lead creating multiple objects.
    Spring caches object, created at first call, and returns it as a result for non-first calling

    Before reading code of this class, please, read the difference between authentication and authorization
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    // automatically initialized by @Bean
    private UsernamePasswordAuthenticationFilter authFilter;

    @Bean
    public AuthenticationProvider authProviderBean() {
        return new CustomAuthenticationProvider();
    }

    @Bean
    public AuthenticationManager authManagerBean() {
        return new ProviderManager(authProviderBean());
    }

    /**
     * Generates set of URLs that users should not be authenticated to interact with them.
     * This info will be taken into account by Filter during authentication.
     * @return Set of such URLs
     */
    @Bean
    public Set<String> notProtectedUrlsBean(){
        return Set.of(
                "/register"
        );
    }

    @Bean
    public UsernamePasswordAuthenticationFilter authFilterBean(){
        this.authFilter = new CustomAuthenticationFilter(authManagerBean(), notProtectedUrlsBean());
        authFilter.setFilterProcessesUrl("/**"); // set Filter to process any URL
        authFilter.setPostOnly(false);
        authFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        authFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        return authFilter;
    }


    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    /*
    Docs for HttpSecurity: https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/builders/HttpSecurity.html

     */
    @Bean
    @DependsOn({"authFilterBean"})
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // insert authFilters into security filter chain
        http.addFilterBefore(this.authFilter, CustomAuthenticationFilter.class);

        // configure endpoints security
        http
                .csrf((csrf) -> csrf.disable()) // for POST method be working
                .cors(withDefaults())
                .authorizeHttpRequests((authorizeHttpRequests) ->
                                authorizeHttpRequests
                                        .anyRequest().authenticated() // only for authenticated users

                );
        /*
            Required Authorities for accessing each endpoint
            are written directly on methods with @PreAuthorize annotation

            If endpoint is allowed to be accessed by any authenticated user,
             we shouldn't write @PreAuthorized, because it is default behaviour

             If endpoint shouldn't require only authenticated user,
             we should add write it in notProtectedUrlsBean()
             because ways like .permitAll() method or .ignore() does not work for me
         */

        return http.build();
    }

}