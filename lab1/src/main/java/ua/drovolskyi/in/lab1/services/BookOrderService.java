package ua.drovolskyi.in.lab1.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.drovolskyi.in.lab1.dto.BookOrderDto;
import ua.drovolskyi.in.lab1.dto.ChangeBookOrderStatusDto;
import ua.drovolskyi.in.lab1.entities.Book;
import ua.drovolskyi.in.lab1.entities.BookOrder;
import ua.drovolskyi.in.lab1.entities.User;
import ua.drovolskyi.in.lab1.errors.ResourceDoesNotExistException;
import ua.drovolskyi.in.lab1.repositories.BookOrderRepository;
import ua.drovolskyi.in.lab1.repositories.BookRepository;
import ua.drovolskyi.in.lab1.repositories.UserRepository;

import java.util.List;

@Service
public class BookOrderService {
    @Autowired
    BookOrderRepository bookOrderRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookService bookService;


    public BookOrder getBookOrderById(Long id){
        BookOrder bookOrder = bookOrderRepository.findById(id).orElse(null);

        if(bookOrder == null){
            throw new ResourceDoesNotExistException(
                    String.format("BookOrder with ID=%d does not exist", id));
        }
        return bookOrder;
    }


    public List<BookOrder> getAllBookOrders(){
        return bookOrderRepository.findAllByOrderByIdAsc();
    }

    public List<BookOrder> getAllBookOrdersWithStatus(BookOrder.Status status){
        return bookOrderRepository.findAllByStatusOrderByIdAsc(status);
    }

    public List<BookOrder> getAllBookOrdersByCustomer(Long customerId){
        return bookOrderRepository.findAllByCustomerIdOrderByIdAsc(customerId);
    }

    public List<BookOrder> getAllBookOrdersByBook(Long bookId){
        return bookOrderRepository.findAllByBookIdOrderByIdAsc(bookId);
    }



    @Transactional
    public BookOrder createBookOrder(BookOrderDto bookOrderDto){
        // get customer
        User customer = userRepository.findById(bookOrderDto.getCustomerId()).orElse(null);
        if(customer == null){
            throw new ResourceDoesNotExistException(
                    String.format("User with ID=%d does not exist", bookOrderDto.getCustomerId()));
        }
        if(customer.getRole() != User.Role.CUSTOMER){
            throw new IllegalArgumentException("Only CUSTOMER can create BookOrder");
        }

        // get book
        Book book = bookRepository.findById(bookOrderDto.getBookId()).orElse(null);
        if(book == null){
            throw new ResourceDoesNotExistException(
                    String.format("Book with ID=%d does not exist", bookOrderDto.getBookId()));
        }

        // create BookOrder
        BookOrder bookOrder = new BookOrder();
        bookOrder.setId(null);
        bookOrder.setBook(book);
        bookOrder.setCustomer(customer);
        bookOrder.setStatus(BookOrder.Status.NEW);

        bookOrderRepository.save(bookOrder);

        return bookOrder;
    }


    @Transactional
    public BookOrder changeBookOrderStatus(ChangeBookOrderStatusDto changeBookOrderStatusDto){
        // get bookOrder
        BookOrder bookOrder = bookOrderRepository.findById(changeBookOrderStatusDto.getBookOrderId()).orElse(null);
        if(bookOrder == null){
            throw new ResourceDoesNotExistException(
                    String.format("BookOrder with ID=%d does not exist",
                            changeBookOrderStatusDto.getBookOrderId()));
        }

        bookOrder.setStatus(changeBookOrderStatusDto.getNewStatus());

        bookOrderRepository.save(bookOrder);

        return bookOrder;
    }


}
