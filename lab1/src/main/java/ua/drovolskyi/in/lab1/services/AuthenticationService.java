package ua.drovolskyi.in.lab1.services;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.drovolskyi.in.lab1.converters.UserToDtoConverter;
import ua.drovolskyi.in.lab1.dto.AuthenticationDto;
import ua.drovolskyi.in.lab1.dto.RegistrationDto;
import ua.drovolskyi.in.lab1.dto.UserDto;
import ua.drovolskyi.in.lab1.entities.User;
import ua.drovolskyi.in.lab1.errors.AuthException;
import ua.drovolskyi.in.lab1.errors.ResourceDoesNotExistException;
import ua.drovolskyi.in.lab1.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserToDtoConverter userToDtoConverter = new UserToDtoConverter();






    /**
        Checks if session is authenticated (checks session's user object)
     */
    public Boolean isAuthenticatedUser(HttpSession session){
        try{
            UserDto userDto = (UserDto) session.getAttribute("authenticatedUser");

            User user = userRepository.findById(userDto.getId()).orElse(null); // get form DB

            return user != null && user.getIsAllowedToLogin();
        } catch(Exception e){ // if 'user' attribute, or something is null, etc.
            return false;
        }
    }

    /**
     * Returns user of given session.
     * Use this method ONLY when session is authenticated (i.e. isAuthenticatedUser() returns true).
     * @param session
     * @return User object if user is authenticated, throws exception otherwise
     */
    public User getUserOfSession(HttpSession session){
        try{
            UserDto userDto = (UserDto) session.getAttribute("authenticatedUser");
            User user = userRepository.findById(userDto.getId()).orElse(null); // get form DB

            if (user != null && user.getIsAllowedToLogin()){
                return user;
            }
            return null;
        } catch(Exception e){ // if 'user' attribute, or something is null, etc.
            return null;
        }
    }

    /**
     * Checks if given user has one of given roles
     * @param u
     * @return
     */
    public Boolean hasPermission(User u, List<User.Role> permittedRoles){
        return permittedRoles.contains(u.getRole());
    }


    /**
     * Checks if auth data is correct. If it is correct, writes userDTO in session.
     * Otherwise, throws AuthException with corresponding text
     * @param authDto
     * @param session
     */
    public void authenticateUser(AuthenticationDto authDto,
                                    HttpSession session){
        User user = userRepository.findByLogin(authDto.getLogin()).orElse(null);

        if(user == null){
            throw new AuthException("User with such login does not exist");
        }

        if(!user.getIsAllowedToLogin()){
            throw new AuthException("This user is not allowed on this website");
        }

        if(!passwordEncoder.matches(authDto.getPassword(), user.getPasswordHash())){
            throw new AuthException("Password is incorrect");
        }


        session.setAttribute("authenticatedUser", userToDtoConverter.convert(user));
    }


    /**
     * Register new user wit CUSTOMER role.
     * If user with given login already exists, throws an IllegalArgumentException
     * @param dto
     * @return
     */
    @Transactional
    public User registerUser(RegistrationDto dto){
        if(userRepository.findByLogin(dto.getLogin()).isPresent()){
            throw new IllegalArgumentException(
                    String.format("User with login '%s' already exists", dto.getLogin()));
        }

        // create new user and save it to DB
        User user = new User();
        user.setId(null);
        user.setLogin(dto.getLogin());
        user.setRole(User.Role.CUSTOMER);
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPatronymic(dto.getPatronymic());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setIsAllowedToLogin(true);

        String passwordHash = passwordEncoder.encode(dto.getPassword());
        user.setPasswordHash(passwordHash);

        userRepository.save(user);

        return user;
    }


}
