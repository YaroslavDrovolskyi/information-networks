package ua.drovolskyi.in.lab2.lab2backend.controllers;

import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.drovolskyi.in.lab2.lab2backend.converters.UserToDtoConverter;
import ua.drovolskyi.in.lab2.lab2backend.dto.RegistrationDto;
import ua.drovolskyi.in.lab2.lab2backend.dto.UserDto;
import ua.drovolskyi.in.lab2.lab2backend.entities.User;
import ua.drovolskyi.in.lab2.lab2backend.services.AuthService;

@RestController
public class AuthController {
    private static final Logger log = LogManager.getLogger();

    private AuthService authService;
    private final UserToDtoConverter userToDtoConverter = new UserToDtoConverter();

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserDto> registerUser(
            @Valid @RequestBody RegistrationDto registrationDto
    ) {
        log.info("Received POST request to '/register'");

        User user = authService.registerUser(registrationDto);
        UserDto userDto = userToDtoConverter.convert(user);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
