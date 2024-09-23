package ua.drovolskyi.in.lab1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.drovolskyi.in.lab1.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
    List<User> findAllByOrderByIdAsc();
    List<User> findAllByRoleOrderByIdAsc(User.Role role);
}
