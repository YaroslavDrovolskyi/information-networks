package ua.drovolskyi.in.lab1.services;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.drovolskyi.in.lab1.dto.UserDto;
import ua.drovolskyi.in.lab1.entities.User;
import ua.drovolskyi.in.lab1.errors.ResourceDoesNotExistException;
import ua.drovolskyi.in.lab1.repositories.UserRepository;

import java.util.List;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;





    /**
        Checks if session is authenticated (checks session's user object)
     */
    public Boolean isAuthenticatedUser(HttpSession session){
        try{
            UserDto userDto = (UserDto) session.getAttribute("user");

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
            UserDto userDto = (UserDto) session.getAttribute("user");
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

}
