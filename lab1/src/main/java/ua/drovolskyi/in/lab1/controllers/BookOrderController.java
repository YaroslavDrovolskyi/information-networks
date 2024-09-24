package ua.drovolskyi.in.lab1.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.drovolskyi.in.lab1.converters.BookOrderToDtoConverter;
import ua.drovolskyi.in.lab1.converters.BookToDtoConverter;
import ua.drovolskyi.in.lab1.dto.BookDto;
import ua.drovolskyi.in.lab1.dto.BookOrderDto;
import ua.drovolskyi.in.lab1.dto.CreateBookOrderDto;
import ua.drovolskyi.in.lab1.entities.Book;
import ua.drovolskyi.in.lab1.entities.BookOrder;
import ua.drovolskyi.in.lab1.errors.ResourceDoesNotExistException;
import ua.drovolskyi.in.lab1.services.BookOrderService;
import ua.drovolskyi.in.lab1.services.BookService;

import java.util.List;

@RestController
public class BookOrderController {
    private BookOrderService bookOrderService;
    private final BookOrderToDtoConverter bookOrderToDtoConverter = new BookOrderToDtoConverter();

    public BookOrderController(BookOrderService bookOrderService){
        this.bookOrderService = bookOrderService;
    }


    @GetMapping("/bookOrder/{id}")
    public ModelAndView getBookOrder(@PathVariable(name="id") Long id){
        // get bookOrder
        BookOrder bookOrder = null;
        try{
            bookOrder = bookOrderService.getBookOrderById(id);
        } catch(ResourceDoesNotExistException e){ // if book does not exist
            // nothing to do
        }

        // convert bookOrder to DTO
        BookOrderDto bookOrderDto = bookOrderToDtoConverter.convert(bookOrder);

        // return .jsp with result
        ModelAndView modelAndView = new ModelAndView("view-bookOrder"); // /WEB-INF/jsp/view-bookOrder.jsp
        modelAndView.addObject("requestedBookOrderId", id);
        modelAndView.addObject("bookOrder", bookOrderDto); // bookOrder object will be accessible in .jsp-file
        return modelAndView;
    }

    @GetMapping("/bookOrders")
    public ModelAndView getAllBookOrders() {
        List<BookOrderDto> bookOrdersDto = bookOrderService.getAllBookOrders()
                .stream()
                .map(bookOrderToDtoConverter::convert)
                .toList();

        // return .jsp with result
        ModelAndView modelAndView = new ModelAndView("view-all-bookOrders"); // /WEB-INF/jsp/view-all-bookOrders.jsp
        modelAndView.addObject("bookOrders", bookOrdersDto);
        return modelAndView;
    }

    // customer ID will be taken from authorization info
    @PostMapping("/bookOrder/create")
    public ModelAndView createBookOrder(CreateBookOrderDto dto){
        BookOrder createdBookOrder = bookOrderService.createBookOrder(
                new BookOrderDto(null, dto.getBookId(), 1L, null)
        );
//////////////////////////////////////////////////////////////////////// CUSTOMER ID IS HARDCODED

        return new ModelAndView(String.format("redirect:/bookOrder/%d", createdBookOrder.getId()));
    }



}
