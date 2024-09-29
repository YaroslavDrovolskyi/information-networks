package ua.drovolskyi.in.lab1.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.drovolskyi.in.lab1.converters.BookOrderToDtoConverter;
import ua.drovolskyi.in.lab1.converters.BookToDtoConverter;
import ua.drovolskyi.in.lab1.dto.BookDto;
import ua.drovolskyi.in.lab1.dto.BookOrderDto;
import ua.drovolskyi.in.lab1.dto.CreateBookOrderDto;
import ua.drovolskyi.in.lab1.entities.BookOrder;
import ua.drovolskyi.in.lab1.entities.User;
import ua.drovolskyi.in.lab1.errors.AccessDeniedException;
import ua.drovolskyi.in.lab1.errors.ResourceDoesNotExistException;
import ua.drovolskyi.in.lab1.services.AuthenticationService;
import ua.drovolskyi.in.lab1.services.BookOrderService;

import java.util.List;

@RestController
@SessionAttributes({"authenticatedUser"}) // it is UserDto object
public class BookOrderController {
    private AuthenticationService authService;
    private BookOrderService bookOrderService;
    private final BookOrderToDtoConverter bookOrderToDtoConverter = new BookOrderToDtoConverter();
    private final BookToDtoConverter bookToDtoConverter = new BookToDtoConverter();

    public BookOrderController(BookOrderService bookOrderService, AuthenticationService authService){
        this.bookOrderService = bookOrderService;
        this.authService = authService;
    }


    @GetMapping("/bookOrder/{id}")
    public ModelAndView getBookOrder(@PathVariable(name="id") Long id,
                                     HttpSession session){
        if(!authService.isAuthenticatedUser(session)) {
            return new ModelAndView("redirect:/login");
        }
        User user = authService.getUserOfSession(session);

        // get bookOrder
        BookOrder bookOrder = null;
        try{
            bookOrder = bookOrderService.getBookOrderById(id);
        } catch(ResourceDoesNotExistException e){ // if book does not exist
            // nothing to do
        }

        // check if customer accesses own BookOrder
        // OWNER and ADMIN don't have such restriction
        if(bookOrder != null){
            if(user.getRole() == User.Role.CUSTOMER &&
                    !bookOrder.getCustomer().getId().equals(user.getId())){
                throw new AccessDeniedException("Customer can access only own BookOrder");
            }
        }

        // convert bookOrder and book to DTO
        BookOrderDto bookOrderDto = bookOrderToDtoConverter.convert(bookOrder);
        BookDto bookDto = bookToDtoConverter.convert(bookOrder != null ? bookOrder.getBook() : null);

        // return .jsp with result
        ModelAndView modelAndView = new ModelAndView("view-bookOrder"); // /WEB-INF/jsp/view-bookOrder.jsp
        modelAndView.addObject("requestedBookOrderId", id);
        modelAndView.addObject("bookOrder", bookOrderDto); // bookOrder object will be accessible in .jsp-file
        modelAndView.addObject("book", bookDto);
        return modelAndView;
    }

    @GetMapping("/bookOrders")
    public ModelAndView getAllBookOrders(HttpSession session) {
        if(!authService.isAuthenticatedUser(session)) {
            return new ModelAndView("redirect:/login");
        }
        User user = authService.getUserOfSession(session);

        // get book orders
        List<BookOrderDto> bookOrdersDto = null;
        if(user.getRole() == User.Role.CUSTOMER){ // if user is CUSTOMER, it will receive only own BookOrders
            bookOrdersDto = bookOrderService.getAllBookOrdersByCustomer(user.getId())
                    .stream()
                    .map(bookOrderToDtoConverter::convert)
                    .toList();
        }
        else{ // user is ADMIn or OWNER
            bookOrdersDto = bookOrderService.getAllBookOrders()
                    .stream()
                    .map(bookOrderToDtoConverter::convert)
                    .toList();
        }


        // return .jsp with result
        ModelAndView modelAndView = new ModelAndView("view-all-bookOrders"); // /WEB-INF/jsp/view-all-bookOrders.jsp
        modelAndView.addObject("bookOrders", bookOrdersDto);
        return modelAndView;
    }

    // customer ID will be taken from authorization info
    @PostMapping("/bookOrder/create")
    public ModelAndView createBookOrder(@Valid CreateBookOrderDto dto,
                                        HttpSession session){
        if(authService.isAuthenticatedUser(session)){
            User user = authService.getUserOfSession(session);
            if(authService.hasPermission(user, List.of(User.Role.CUSTOMER))){
                BookOrder createdBookOrder = bookOrderService.createBookOrder(
                        dto.getBookId(),
                        user.getId()
                );
                return new ModelAndView(String.format("redirect:/bookOrder/%d", createdBookOrder.getId()));
            }
        }
        return new ModelAndView("redirect:/login");
    }

    // book quantity--
    @PostMapping("/bookOrder/{id}/satisfy")
    public ModelAndView satisfyBookOrder(@PathVariable(name="id") Long id,
                                         HttpSession session){
        if(authService.isAuthenticatedUser(session)){
            User user = authService.getUserOfSession(session);
            if(authService.hasPermission(user, List.of(User.Role.ADMIN))){
                bookOrderService.satisfyBookOrder(id);
                return new ModelAndView(String.format("redirect:/bookOrder/%d", id));
            }
        }
        return new ModelAndView("redirect:/login");
    }

    // book quantity++
    @PostMapping("/bookOrder/{id}/complete")
    public ModelAndView completeBookOrder(@PathVariable(name="id") Long id,
                                          HttpSession session){
        if(authService.isAuthenticatedUser(session)){
            User user = authService.getUserOfSession(session);
            if(authService.hasPermission(user, List.of(User.Role.ADMIN))){
                bookOrderService.completeBookOrder(id);
                return new ModelAndView(String.format("redirect:/bookOrder/%d", id));
            }
        }
        return new ModelAndView("redirect:/login");
    }



}
