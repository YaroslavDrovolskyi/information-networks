package ua.drovolskyi.in.lab1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.drovolskyi.in.lab1.entities.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByOrderById();
}
