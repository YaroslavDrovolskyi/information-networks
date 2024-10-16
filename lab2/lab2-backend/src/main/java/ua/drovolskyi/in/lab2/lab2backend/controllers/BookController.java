package ua.drovolskyi.in.lab2.lab2backend.controllers;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.drovolskyi.in.lab2.lab2backend.converters.BookToDtoConverter;
import ua.drovolskyi.in.lab2.lab2backend.converters.PageToDtoConverter;
import ua.drovolskyi.in.lab2.lab2backend.dto.BookDto;
import ua.drovolskyi.in.lab2.lab2backend.dto.ChangeBookQuantityDto;
import ua.drovolskyi.in.lab2.lab2backend.dto.PageDto;
import ua.drovolskyi.in.lab2.lab2backend.entities.Book;
import ua.drovolskyi.in.lab2.lab2backend.services.BookService;


import java.util.List;

@RestController
public class BookController {
    private static final Logger log = LogManager.getLogger();

    private final BookService bookService;
    private final BookToDtoConverter bookToDtoConverter = new BookToDtoConverter();
    private final PageToDtoConverter<Book, BookDto> bookPageToDtoConverter
            = new PageToDtoConverter<>(bookToDtoConverter);

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/book/{id}")
    public ResponseEntity<BookDto> getBook(
            @PathVariable(name="id") Long id
    ){
        log.info("Received GET request to '/book', ID = " + id);

        Book book = bookService.getBookById(id);
        BookDto bookDto = bookToDtoConverter.convert(book);

        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @GetMapping(value = "/books/all")
    public ResponseEntity<List<BookDto>> getAllBooks(){
        log.info("Received GET request to '/books/all'");

        List<BookDto> booksDto = bookService.getAllBooks()
                .stream()
                .map(bookToDtoConverter::convert)
                .toList();

        return new ResponseEntity<>(booksDto, HttpStatus.OK);
    }

    // pageIndex is zero-based
    @GetMapping(value = "/books/all", params = {"pageIndex", "pageSize"})
    public ResponseEntity<PageDto<BookDto>> getAllBooksPage(
            @RequestParam(name="pageIndex") Integer pageIndex,
            @RequestParam(name="pageSize") Integer pageSize){
        log.info(String.format("Received GET request to '/books/all', pageIndex=%d, pageSize=%d",
                pageIndex, pageSize));

        Page<Book> page = bookService.getAllBooksPage(pageIndex, pageSize);
        PageDto<BookDto> pageDto = bookPageToDtoConverter.convert(page);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    @PostMapping("/book/create")
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto createBookDto){
        log.info("Received POST request to '/book/create'");
        Book createdBook = bookService.createBook(createBookDto);
        BookDto createdBookDto = bookToDtoConverter.convert(createdBook);

        return new ResponseEntity<>(createdBookDto, HttpStatus.OK);
    }

    @PatchMapping("/book/changeQuantity")
    public ResponseEntity<BookDto> changeBookQuantity(@Valid @RequestBody ChangeBookQuantityDto dto){
        log.info("Received POST request to '/book/changeQuantity'");
        Book editedBook = bookService.changeQuantity(dto);
        BookDto editedBookDto = bookToDtoConverter.convert(editedBook);

        return new ResponseEntity<>(editedBookDto, HttpStatus.OK);
    }

}