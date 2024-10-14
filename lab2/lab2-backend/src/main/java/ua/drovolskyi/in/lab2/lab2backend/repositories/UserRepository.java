package ua.drovolskyi.in.lab2.lab2backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.drovolskyi.in.lab2.lab2backend.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

//    List<User> findAllByOrderByIdAsc(); default method

    List<User> findAllByRole(User.Role role, Sort sort);
    Page<User> findAllByRole(User.Role role, Pageable pageable);
}
