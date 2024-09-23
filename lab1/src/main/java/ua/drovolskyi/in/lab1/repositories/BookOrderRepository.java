package ua.drovolskyi.in.lab1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.drovolskyi.in.lab1.entities.BookOrder;

import java.util.List;

public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
    List<BookOrder> findAllByOrderByIdAsc();
    List<BookOrder> findAllByCustomerIdOrderByIdAsc(Long customerId);
    List<BookOrder> findAllByBookIdOrderByIdAsc(Long bookId);
    List<BookOrder> findAllByStatusOrderByIdAsc(BookOrder.Status status);
}
