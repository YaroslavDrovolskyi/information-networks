package ua.drovolskyi.in.lab1.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.drovolskyi.in.lab1.converters.BookToDtoConverter;
import ua.drovolskyi.in.lab1.dto.BookDto;
import ua.drovolskyi.in.lab1.entities.Book;
import ua.drovolskyi.in.lab1.entities.BookOrder;
import ua.drovolskyi.in.lab1.entities.User;
import ua.drovolskyi.in.lab1.errors.ResourceDoesNotExistException;
import ua.drovolskyi.in.lab1.repositories.BookOrderRepository;
import ua.drovolskyi.in.lab1.repositories.BookRepository;
import ua.drovolskyi.in.lab1.repositories.UserRepository;
import ua.drovolskyi.in.lab1.services.BookService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    private BookService bookService;
    private final BookToDtoConverter bookToDtoConverter = new BookToDtoConverter();

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/book")
    public ModelAndView getBook(@RequestParam(name="id") Long id){
        // get book
        Book book = null;
        try{
            book = bookService.getBookById(id);
        } catch(ResourceDoesNotExistException e){ // if book does not exist
            // nothing to do
        }

        // convert book to DTO
        BookDto bookDto = bookToDtoConverter.convert(book);

        // return .jsp with result
        ModelAndView modelAndView = new ModelAndView("view-book"); // /WEB-INF/jsp/view-book.jsp
        modelAndView.addObject("book", bookDto); // book object will be accessible in .jsp-file
        return modelAndView;
    }

    @GetMapping("/books")
    public ModelAndView getAllBooks() {
        List<BookDto> booksDto = bookService.getAllBooks()
                .stream()
                .map(bookToDtoConverter::convert)
                .toList();

        // return .jsp with result
        ModelAndView modelAndView = new ModelAndView("view-all-books"); // /WEB-INF/jsp/view-all-book.jsp
        modelAndView.addObject("books", booksDto);
        return modelAndView;
    }

    @GetMapping("/book/create")
    public ModelAndView fetchCreateBookPage(){
        return new ModelAndView("create-book");
    }

    @PostMapping("/book/create")
    public ModelAndView createBook(@Valid BookDto createBookDto){
        Book createdBook = bookService.createBook(createBookDto);

        return new ModelAndView(String.format("redirect:/book?id=%d", createdBook.getId()));
    }

}

// input model object (as seen in some guides) is unnecessary