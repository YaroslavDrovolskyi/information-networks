package ua.drovolskyi.in.lab2.lab2backend.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.drovolskyi.in.lab2.lab2backend.dto.RegistrationDto;
import ua.drovolskyi.in.lab2.lab2backend.entities.User;
import ua.drovolskyi.in.lab2.lab2backend.repositories.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    /**
     * Uses SecurityContext
     * @return username of user, whose request is being processed
     */
    public String getAuthenticatedUserUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();

        return username;
    }

    /**
     * Returns current authenticated user.
     * Returns null, only if open (authentication is not required) endpoint is accessed
     * @return
     */
    public User getAuthenticatedUser(){
        String username = getAuthenticatedUserUsername();
        return userRepository.findByUsername(username).orElse(null);
    }


    /**
     * Registers new user wit CUSTOMER role.
     * If user with given username already exists, throws an IllegalArgumentException
     * @param dto
     * @return
     */
    @Transactional
    public User registerUser(RegistrationDto dto){
        if(userRepository.findByUsername(dto.getUsername()).isPresent()){
            throw new IllegalArgumentException(
                    String.format("User with username '%s' already exists", dto.getUsername()));
        }


        // create new user and save it to DB
        User user = new User();
        user.setId(null);
        user.setUsername(dto.getUsername().trim());
        user.setRole(User.Role.CUSTOMER);
        user.setFullName(dto.getFullName().trim());
        user.setPhoneNumber(dto.getPhoneNumber().trim());
        user.setIsAllowedToLogin(true);

        String passwordHash = passwordEncoder.encode(dto.getPassword());
        user.setPasswordHash(passwordHash);

        userRepository.save(user);

        return user;
    }

}
