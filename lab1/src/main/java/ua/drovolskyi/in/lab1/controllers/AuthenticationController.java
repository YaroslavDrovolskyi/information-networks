package ua.drovolskyi.in.lab1.controllers;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.drovolskyi.in.lab1.dto.AuthenticationDto;
import ua.drovolskyi.in.lab1.dto.RegistrationDto;
import ua.drovolskyi.in.lab1.dto.UserDto;
import ua.drovolskyi.in.lab1.entities.User;
import ua.drovolskyi.in.lab1.errors.AuthException;
import ua.drovolskyi.in.lab1.services.AuthenticationService;

@RestController
@SessionAttributes({"authenticatedUser"}) // it is UserDto object
public class AuthenticationController {

    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage(HttpSession session){
        Boolean isAuthenticated = authenticationService.isAuthenticatedUser(session);

        if(isAuthenticated){ // if already authenticated user goes to /login page
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("login");
    }


    // method that is invoked when user submit the 'login form'
    @PostMapping("/login")
    public ModelAndView login(@Valid AuthenticationDto authDto,
                              HttpSession session, RedirectAttributes redirectAttrs){
        try{
            authenticationService.authenticateUser(authDto, session);
            return new ModelAndView("redirect:/");
        }
        catch(AuthException e) {
            redirectAttrs.addFlashAttribute("prevAuthAttemptError", e.getMessage());
            return new ModelAndView("redirect:/login");
        }
    }


    /**
     * Finishes current session, and deletes associated attributes
     * @param session
     * @param status
     * @return
     */
    @PostMapping("/logout")
    public ModelAndView logout(HttpSession session, SessionStatus status){
        /* Mark the current handler's session processing as complete, allowing for cleanup of session attributes. */
        status.setComplete();

        /* Invalidates this session then unbinds any objects bound to it. */
        session.invalidate();

        return new ModelAndView("redirect:/login");
    }


    @GetMapping("/register")
    public ModelAndView getRegisterPage(HttpSession session){
        Boolean isAuthenticated = authenticationService.isAuthenticatedUser(session);

        if(isAuthenticated){ // if already authenticated user goes to /register page
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("register");
    }


    //

    /**
     * <p>Invokes when user submit the 'register' form.</p>
     * <p>Registers new CUSTOMER user with given credentials, and authenticate this new user.
     * After that, redirects to '/' endpoint.</p>
     * <p>If some error occurred, redirects to '/register' page with error message</p>
     *
     * @param registerDto
     * @param session
     * @return
     */
    @PostMapping("/register")
    public ModelAndView register(@Valid RegistrationDto registerDto,
                              HttpSession session, RedirectAttributes redirectAttrs){
        try{
            authenticationService.registerUser(registerDto);
            authenticationService.authenticateUser(
                    new AuthenticationDto(registerDto.getLogin(), registerDto.getPassword()),
                    session
            );
            return new ModelAndView("redirect:/");
        }
        catch(Exception e) {
            redirectAttrs.addFlashAttribute("prevRegisterAttemptError", e.getMessage());
            return new ModelAndView("redirect:/register");
        }
    }
}