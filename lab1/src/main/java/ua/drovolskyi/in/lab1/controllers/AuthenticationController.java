package ua.drovolskyi.in.lab1.controllers;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import ua.drovolskyi.in.lab1.dto.AuthenticationDto;
import ua.drovolskyi.in.lab1.dto.UserDto;
import ua.drovolskyi.in.lab1.entities.User;
import ua.drovolskyi.in.lab1.errors.AuthException;
import ua.drovolskyi.in.lab1.services.AuthenticationService;

@RestController
@SessionAttributes({"authenticatedUser"})
public class AuthenticationController {

    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage(HttpSession session, SessionStatus status){
        Boolean isAuthenticated = authenticationService.isAuthenticatedUser(session);

        if(isAuthenticated){ // if already authenticated user goes to /login page
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("login");
    }


    // method that is invoked when user submit the 'login form'
    @PostMapping("/login")
    public ModelAndView login(@Valid AuthenticationDto authDto,
                              HttpSession session){
        try{
            authenticationService.authenticateUser(authDto, session);
            return new ModelAndView("redirect:/");
        }
        catch(AuthException e) {
            ModelAndView modelAndView = new ModelAndView("redirect:/login");
            modelAndView.addObject("prevAuthAttemptError", e.getMessage());
            return modelAndView;
        }
    }


    /**
     * Finishes current session, and deletes associated attributes
     * @param httpsession
     * @param status
     * @return
     */
    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session, SessionStatus status){
        /* Mark the current handler's session processing as complete, allowing for cleanup of session attributes. */
        status.setComplete();

        /* Invalidates this session then unbinds any objects bound to it. */
        session.invalidate();

        return new ModelAndView("redirect:/");
    }


    @GetMapping("/register")
    public ModelAndView getRegisterPage(HttpSession session){
        Boolean isAuthenticated = authenticationService.isAuthenticatedUser(session);

        if(isAuthenticated){ // if already authenticated user goes to /register page
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("register");
    }
}