package ua.drovolskyi.in.lab1.services;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.drovolskyi.in.lab1.dto.UserDto;
import ua.drovolskyi.in.lab1.entities.User;
import ua.drovolskyi.in.lab1.repositories.UserRepository;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;





    /**
        Checks if session is authenticated (checks session's user object)
     */
    public Boolean isAuthenticated(HttpSession session){
        try{
            UserDto userDto = (UserDto) session.getAttribute("user");

            User user = userRepository.findById(userDto.getId()).orElse(null); // get form DB

            return user != null && user.getIsAllowedToLogin();
        } catch(Exception e){ // if 'user' attribute, ot something is null, etc.
            return false;
        }
    }

}
