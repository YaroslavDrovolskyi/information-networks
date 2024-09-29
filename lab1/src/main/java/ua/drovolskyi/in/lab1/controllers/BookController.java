package ua.drovolskyi.in.lab1.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.drovolskyi.in.lab1.converters.BookToDtoConverter;
import ua.drovolskyi.in.lab1.dto.BookDto;
import ua.drovolskyi.in.lab1.entities.Book;
import ua.drovolskyi.in.lab1.entities.User;
import ua.drovolskyi.in.lab1.errors.AccessDeniedException;
import ua.drovolskyi.in.lab1.errors.ResourceDoesNotExistException;
import ua.drovolskyi.in.lab1.services.AuthenticationService;
import ua.drovolskyi.in.lab1.services.BookService;

import java.util.List;

@RestController
@SessionAttributes({"authenticatedUser"}) // it is UserDto object
public class BookController {
    private BookService bookService;
    private AuthenticationService authService;
    private final BookToDtoConverter bookToDtoConverter = new BookToDtoConverter();

    public BookController(BookService bookService, AuthenticationService authService) {
        this.bookService = bookService;
        this.authService = authService;
    }


    @GetMapping("/book/{id}")
    public ModelAndView getBook(@PathVariable(name="id") Long id){
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
        modelAndView.addObject("requestedBookId", id);
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
        ModelAndView modelAndView = new ModelAndView("view-all-books"); // /WEB-INF/jsp/view-all-books.jsp
        modelAndView.addObject("books", booksDto);
        return modelAndView;
    }

    @GetMapping("/book/create")
    public ModelAndView getCreateBookPage(HttpSession session){
        if(!authService.isAuthenticatedUser(session)) {
            return new ModelAndView("redirect:/login");
        }
        User user = authService.getUserOfSession(session);
        if(user.getRole() != User.Role.ADMIN){
            throw new AccessDeniedException("You can't create book!");
        }


        return new ModelAndView("create-book");
    }

    @PostMapping("/book/create")
    public ModelAndView createBook(@Valid BookDto createBookDto,
                                   HttpSession session){
        if(!authService.isAuthenticatedUser(session)) {
            return new ModelAndView("redirect:/login");
        }
        User user = authService.getUserOfSession(session);
        if(user.getRole() != User.Role.ADMIN){
            throw new AccessDeniedException("You can't create book!");
        }

        Book createdBook = bookService.createBook(createBookDto);

        return new ModelAndView(String.format("redirect:/book/%d", createdBook.getId()));
    }

}