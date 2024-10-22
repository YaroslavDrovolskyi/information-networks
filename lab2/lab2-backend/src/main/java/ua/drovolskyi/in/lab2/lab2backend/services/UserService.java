package ua.drovolskyi.in.lab2.lab2backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.drovolskyi.in.lab2.lab2backend.dto.SetUserIsAllowedToLoginDto;
import ua.drovolskyi.in.lab2.lab2backend.dto.SetUserRoleDto;
import ua.drovolskyi.in.lab2.lab2backend.entities.User;
import ua.drovolskyi.in.lab2.lab2backend.errors.ResourceDoesNotExistException;
import ua.drovolskyi.in.lab2.lab2backend.repositories.UserRepository;

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

    public User getUserByUsername(String username){
        User user = userRepository.findByUsername(username).orElse(null);

        if(user == null){
            throw new ResourceDoesNotExistException(
                    String.format("User with username='%s' does not exist", username));
        }
        return user;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        );
    }

    /**
     *
     * @param pageIndex is zero-based index of page
     * @return
     */
    public Page<User> getAllUsersPage(Integer pageIndex, Integer pageSize){
        return userRepository.findAll(
                PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.ASC, "id"))
        );
    }

    public List<User> getUsersByRole(User.Role role){
        return userRepository.findAllByRole(
                role,
                Sort.by(Sort.Direction.ASC, "id")
        );
    }

    public Page<User> getUsersByRolePage(User.Role role, Integer pageIndex, Integer pageSize){
        return userRepository.findAllByRole(
                role,
                PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.ASC, "id"))
        );
    }

    public User setUserRole(SetUserRoleDto dto){
        if(dto.getRole() == User.Role.OWNER){
            throw new IllegalArgumentException("Setting role to OWNER is not allowed");
        }

        User user = getUserById(dto.getUserId());
        user.setRole(dto.getRole());

        userRepository.save(user);

        return user;
    }

    public User setUserIsAllowedToLogin(SetUserIsAllowedToLoginDto dto){
        User user = getUserById(dto.getUserId());
        user.setIsAllowedToLogin(dto.getIsAllowedToLogin());

        userRepository.save(user);

        return user;
    }
}
