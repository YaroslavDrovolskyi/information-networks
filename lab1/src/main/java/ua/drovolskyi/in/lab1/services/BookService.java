package ua.drovolskyi.in.lab1.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.drovolskyi.in.lab1.Utils;
import ua.drovolskyi.in.lab1.dto.BookDto;
import ua.drovolskyi.in.lab1.dto.ChangeBookQuantityDto;
import ua.drovolskyi.in.lab1.entities.Book;
import ua.drovolskyi.in.lab1.errors.ResourceDoesNotExistException;
import ua.drovolskyi.in.lab1.repositories.BookRepository;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book getBookById(Long id){
        Book book = bookRepository.findById(id).orElse(null);

        if(book == null){
            throw new ResourceDoesNotExistException(
                    String.format("Book with ID=%d does not exist", id));
        }
        return book;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAllByOrderByIdAsc();
    }

    /**
     * Creates a new Book according to bookDto and saves it into DB.
     * @param bookDto
     * @return created book
     */
    @Transactional
    public Book createBook(BookDto bookDto){
        if(bookDto.getPublishingYear() > Utils.getCurrentYear()){
            throw new IllegalArgumentException("Publishing year can't be bigger than current year");
        }

        Book book = new Book();
        book.setId(null);
        book.setIsbn(bookDto.getIsbn());
        book.setTitle(bookDto.getTitle());
        book.setAuthors(bookDto.getAuthors());
        book.setNumberOfPages(bookDto.getNumberOfPages());
        book.setPublishingYear(bookDto.getPublishingYear());
        book.setQuantity(0);

        bookRepository.save(book);

        return book;
    }

    /**
     * Adds quantityDelta to quantity of book with ID == bookId.
     * If quantityDelta < 0, then quantity will be decreased
     * @return
     */
    @Transactional
    public Book changeQuantity(ChangeBookQuantityDto changeQuantityDto){
        Book book = getBookById(changeQuantityDto.getBookId());

        if(book.getQuantity() + changeQuantityDto.getQuantityDelta() < 0){
            throw new IllegalArgumentException("Quantity of book will be < 0 after change.");
        }

        book.setQuantity(book.getQuantity() + changeQuantityDto.getQuantityDelta());

        bookRepository.save(book);

        return book;
    }
}
