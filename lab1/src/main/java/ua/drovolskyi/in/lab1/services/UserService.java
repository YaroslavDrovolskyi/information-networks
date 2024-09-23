package ua.drovolskyi.in.lab1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.drovolskyi.in.lab1.entities.User;
import ua.drovolskyi.in.lab1.errors.ResourceDoesNotExistException;
import ua.drovolskyi.in.lab1.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id){
        User user = userRepository.findById(id).orElse(null);

        if(user == null){
            throw new ResourceDoesNotExistException(
                    String.format("User with ID=%d does not exist", id));
        }
        return user;
    }

    public User getUserByLogin(String login){
        User user = userRepository.findByLogin(login).orElse(null);

        if(user == null){
            throw new ResourceDoesNotExistException(
                    String.format("User with login='%s' does not exist", login));
        }
        return user;
    }

    public List<User> getAllUsers(){
        return userRepository.findAllByOrderByIdAsc();
    }

    public List<User> getUsersByRole(User.Role role){
        return userRepository.findAllByRoleOrderByIdAsc(role);
    }
}
