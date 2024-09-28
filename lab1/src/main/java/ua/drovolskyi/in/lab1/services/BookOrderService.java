package ua.drovolskyi.in.lab1.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public BookOrder createBookOrder(Long bookId, Long customerId){
        // get customer
        User customer = userRepository.findById(customerId).orElse(null);
        if(customer == null){
            throw new ResourceDoesNotExistException(
                    String.format("User with ID=%d does not exist", customerId));
        }
        if(customer.getRole() != User.Role.CUSTOMER){
            throw new IllegalArgumentException("Only CUSTOMER can create BookOrder");
        }

        // get book
        Book book = bookRepository.findById(bookId).orElse(null);
        if(book == null){
            throw new ResourceDoesNotExistException(
                    String.format("Book with ID=%d does not exist", bookId));
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

    // when user take the book
    // current status can be only NEW
    @Transactional
    public void satisfyBookOrder(Long bookOrderId){
        BookOrder bookOrder = getBookOrderById(bookOrderId);
        if(bookOrder.getStatus() != BookOrder.Status.NEW){ // check if status == NEW
            throw new IllegalArgumentException(
                    "Can satisfy BookOrder only with NEW status");
        }

        Book book = bookOrder.getBook();
        if(book.getQuantity() <= 0){ // check if book quantity is enough (i.e. >0) to satisfy BookOrder
            throw new IllegalArgumentException(
                    "Can't satisfy BookOrder, because book quantity is = 0");
        }

        // satisfy BookOrder and decrement quantity of book
        bookOrder.setStatus(BookOrder.Status.SATISFIED);
        bookOrderRepository.save(bookOrder);
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);
    }

    // when user returns the book
    // current status can be only SATISFIED
    @Transactional
    public void completeBookOrder(Long bookOrderId){
        BookOrder bookOrder = getBookOrderById(bookOrderId);
        if(bookOrder.getStatus() != BookOrder.Status.SATISFIED){ // check if status == SATISFIED
            throw new IllegalArgumentException(
                    "Can satisfy BookOrder only with SATISFIED status");
        }
        Book book = bookOrder.getBook();

        // complete BookOrder and increment quantity of book
        bookOrder.setStatus(BookOrder.Status.COMPLETED);
        bookOrderRepository.save(bookOrder);
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
    }
}
