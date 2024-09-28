package ua.drovolskyi.in.lab1.controllers;


import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ua.drovolskyi.in.lab1.converters.BookToDtoConverter;
import ua.drovolskyi.in.lab1.converters.UserToDtoConverter;
import ua.drovolskyi.in.lab1.dto.BookDto;
import ua.drovolskyi.in.lab1.dto.UserDto;
import ua.drovolskyi.in.lab1.entities.Book;
import ua.drovolskyi.in.lab1.entities.User;
import ua.drovolskyi.in.lab1.errors.AccessDeniedException;
import ua.drovolskyi.in.lab1.errors.ResourceDoesNotExistException;
import ua.drovolskyi.in.lab1.repositories.UserRepository;
import ua.drovolskyi.in.lab1.services.AuthenticationService;
import ua.drovolskyi.in.lab1.services.BookService;
import ua.drovolskyi.in.lab1.services.UserService;

import java.util.List;

@RestController
public class UserController {
    private UserService userService;
    private AuthenticationService authService;
    private final UserToDtoConverter userToDtoConverter = new UserToDtoConverter();

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public ModelAndView getUser(@PathVariable(name="id") Long id,
                                HttpSession session){
        if(!authService.isAuthenticatedUser(session)) {
            return new ModelAndView("redirect:/login");
        }
        User userOfSession = authService.getUserOfSession(session);


        // get user
        User user = null;
        try{
            user = userService.getUserById(id);
        } catch(ResourceDoesNotExistException e){ // if user does not exist
            // nothing to do
        }

        // check if customer accesses own user object
        // OWNER and ADMIN don't have such restriction
        if(userOfSession.getRole() == User.Role.CUSTOMER &&
                !userOfSession.getId().equals(id)){
            throw new AccessDeniedException("Customer can access only own User info");
        }


        // convert user to DTO
        UserDto userDto = userToDtoConverter.convert(user);

        // return .jsp with result
        ModelAndView modelAndView = new ModelAndView("view-user"); // /WEB-INF/jsp/view-user.jsp
        modelAndView.addObject("requestedUserId", id);
        modelAndView.addObject("user", userDto); // user object will be accessible in .jsp-file
        return modelAndView;
    }

    @GetMapping("/users")
    public ModelAndView getAllUsers(HttpSession session) {
        if(!authService.isAuthenticatedUser(session)) {
            return new ModelAndView("redirect:/login");
        }
        User user = authService.getUserOfSession(session);
        if(user.getRole() != User.Role.ADMIN &&
                user.getRole() != User.Role.OWNER){
            throw new AccessDeniedException("Customer can't access to list of all users");
        }


        List<UserDto> usersDto = userService.getAllUsers()
                .stream()
                .map(userToDtoConverter::convert)
                .toList();

        // return .jsp with result
        ModelAndView modelAndView = new ModelAndView("view-all-users"); // /WEB-INF/jsp/view-all-users.jsp
        modelAndView.addObject("users", usersDto);
        return modelAndView;
    }

}