package ua.drovolskyi.in.lab2.lab2backend.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.drovolskyi.in.lab2.lab2backend.converters.PageToDtoConverter;
import ua.drovolskyi.in.lab2.lab2backend.converters.UserToDtoConverter;
import ua.drovolskyi.in.lab2.lab2backend.dto.BookDto;
import ua.drovolskyi.in.lab2.lab2backend.dto.PageDto;
import ua.drovolskyi.in.lab2.lab2backend.dto.UserDto;
import ua.drovolskyi.in.lab2.lab2backend.entities.Book;
import ua.drovolskyi.in.lab2.lab2backend.entities.User;
import ua.drovolskyi.in.lab2.lab2backend.services.UserService;

import java.util.List;

@RestController
public class UserController {
    private static final Logger log = LogManager.getLogger();
    private UserService userService;
    private final UserToDtoConverter userToDtoConverter = new UserToDtoConverter();
    private final PageToDtoConverter<User, UserDto> userPageToDtoConverter
            = new PageToDtoConverter<>(userToDtoConverter);


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(
            @PathVariable(name="id") Long id
    ){
        log.info("Received GET request to '/user', ID = " + id);

        User user = userService.getUserById(id);
        UserDto userDto = userToDtoConverter.convert(user);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping(value = "/user", params = {"username"})
    public ResponseEntity<UserDto> getUserByUsername(
            @RequestParam(name="username") String username
    ){
        log.info("Received GET request to '/user', username = " + username);

        User user = userService.getUserByUsername(username);
        UserDto userDto = userToDtoConverter.convert(user);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        log.info("Received GET request to '/users/all'");

        List<UserDto> usersDto = userService.getAllUsers()
                .stream()
                .map(userToDtoConverter::convert)
                .toList();

        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

    @GetMapping(value = "/users/all", params = {"pageIndex", "pageSize"})
    public ResponseEntity<PageDto<UserDto>> getAllUsersPage(
            @RequestParam(name="pageIndex") Integer pageIndex,
            @RequestParam(name="pageSize") Integer pageSize
    ) {
        log.info(String.format("Received GET request to '/users/all', pageIndex=%d, pageSize=%d",
                pageIndex, pageSize));

        Page<User> page = userService.getAllUsersPage(pageIndex, pageSize);
        PageDto<UserDto> pageDto = userPageToDtoConverter.convert(page);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }


    @GetMapping(value = "/users/byRole", params = {"role"})
    public ResponseEntity<List<UserDto>> getUsersByRole(
            @RequestParam(name="role") User.Role role
    ) {
        log.info("Received GET request to '/users/byRole', role=" + role.toString());

        List<UserDto> usersDto = userService.getUsersByRole(role)
                .stream()
                .map(userToDtoConverter::convert)
                .toList();

        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

    @GetMapping(value = "/users/byRole", params = {"role", "pageIndex", "pageSize"})
    public ResponseEntity<PageDto<UserDto>> getUsersByRolePage(
            @RequestParam(name="role") User.Role role,
            @RequestParam(name="pageIndex") Integer pageIndex,
            @RequestParam(name="pageSize") Integer pageSize
    ) {
        log.info(String.format("Received GET request to '/users/byRole', role=%s, pageIndex=%d, pageSize=%d",
                role.toString(), pageIndex, pageSize));

        Page<User> page = userService.getUsersByRolePage(role, pageIndex, pageSize);
        PageDto<UserDto> pageDto = userPageToDtoConverter.convert(page);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

}