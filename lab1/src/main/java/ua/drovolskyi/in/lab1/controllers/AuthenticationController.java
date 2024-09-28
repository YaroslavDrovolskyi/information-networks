package ua.drovolskyi.in.lab1.controllers;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import ua.drovolskyi.in.lab1.dto.AuthenticationDto;
import ua.drovolskyi.in.lab1.dto.UserDto;
import ua.drovolskyi.in.lab1.entities.User;
import ua.drovolskyi.in.lab1.services.AuthenticationService;

@RestController
@SessionAttributes({"user"})
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
    public ModelAndView login(@Valid AuthenticationDto authenticationDto,
                              HttpSession session, SessionStatus status){

        UserDto userDto = new UserDto(10L, "login",
                "name", "surname", "patronymic",
                "+380", User.Role.CUSTOMER, true);
        session.setAttribute("user", userDto);

        return new ModelAndView("redirect:/"); /////////////////////////////////////
        //////////////////////////////////// NEED TO CHECK if login and password from authenticationDto are correct,
        // and then add if correct write userDto inti session attribute
        // if not correct then redicrect to GET login with error prevErrorMessage
    }



    /**
     * Finishes current session, and deletes associated attributes
     * @param httpsession
     * @param status
     * @return
     */
    @GetMapping("/logout")
    public ModelAndView logout(HttpSession httpsession, SessionStatus status){
        /* Mark the current handler's session processing as complete, allowing for cleanup of session attributes. */
        status.setComplete();

        /* Invalidates this session then unbinds any objects bound to it. */
        httpsession.invalidate();

        return new ModelAndView("redirect:/");
    }
}