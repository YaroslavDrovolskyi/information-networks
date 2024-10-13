package ua.drovolskyi.in.lab2.lab2backend.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.drovolskyi.in.lab2.lab2backend.entities.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
//    List<Book> findAll(Sort sort); - is default method // Sort.by(Sort.Direction.ASC, "id"))
//    List<Book> findAll(Pageable pageable); - is default method // PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.ASC, "id"))
}