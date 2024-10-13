package ua.drovolskyi.in.lab2.lab2backend.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.drovolskyi.in.lab2.lab2backend.entities.BookOrder;

import java.util.List;


public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
//    List<BookOrder> findAllByOrderByIdAsc(); // is default method

    List<BookOrder> findAllByCustomerId(Long customerId, Sort sort);
    List<BookOrder> findAllByCustomerId(Long customerId, Pageable pageable);

    List<BookOrder> findAllByBookId(Long bookId, Sort sort);
    List<BookOrder> findAllByBookId(Long bookId, Pageable pageable);

    List<BookOrder> findAllByStatus(BookOrder.Status status, Sort sort);
    List<BookOrder> findAllByStatus(BookOrder.Status status, Pageable pageable);
}
