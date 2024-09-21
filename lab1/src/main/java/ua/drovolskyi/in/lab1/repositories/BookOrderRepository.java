package ua.drovolskyi.in.lab1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.drovolskyi.in.lab1.entities.BookOrder;
import ua.drovolskyi.in.lab1.entities.User;

import java.util.List;

public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
    List<BookOrder> findAllByOrderByIdAsc();
    List<BookOrder> findAllByCustomerIdOrderById(Long customerId);
    List<BookOrder> findAllByBookIdOrderById(Long bookId);
    List<BookOrder> findAllByStateOrderById(BookOrder.State state);
}
