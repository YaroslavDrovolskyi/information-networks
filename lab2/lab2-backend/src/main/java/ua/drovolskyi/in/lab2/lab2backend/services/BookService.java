package ua.drovolskyi.in.lab2.lab2backend.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.drovolskyi.in.lab2.lab2backend.Utils;
import ua.drovolskyi.in.lab2.lab2backend.dto.BookDto;
import ua.drovolskyi.in.lab2.lab2backend.dto.ChangeBookQuantityDto;
import ua.drovolskyi.in.lab2.lab2backend.entities.Book;
import ua.drovolskyi.in.lab2.lab2backend.errors.ResourceDoesNotExistException;
import ua.drovolskyi.in.lab2.lab2backend.repositories.BookRepository;


import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBookById(Long id){
        Book book = bookRepository.findById(id).orElse(null);

        if(book == null){
            throw new ResourceDoesNotExistException(
                    String.format("Book with ID=%d does not exist", id));
        }
        return book;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        );
    }

    public Page<Book> getAllBooksPage(Integer pageIndex, Integer pageSize){
        return bookRepository.findAll(
                PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.ASC, "id"))
        );
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
        book.setIsbn(bookDto.getIsbn().trim());
        book.setTitle(bookDto.getTitle().trim());
        book.setAuthors(bookDto.getAuthors().trim());
        book.setNumberOfPages(bookDto.getNumberOfPages());
        book.setPublishingYear(bookDto.getPublishingYear());
        book.setQuantity(0);

        bookRepository.save(book);

        return book;
    }

    /**
     * Adds quantityDelta to quantity of book with ID == bookId.
     * If quantityDelta < 0, then quantity will be decreased
     * @return edited book
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
