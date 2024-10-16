package ua.drovolskyi.in.lab2.lab2backend.controllers;

import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.drovolskyi.in.lab2.lab2backend.converters.BookOrderToDtoConverter;
import ua.drovolskyi.in.lab2.lab2backend.converters.BookToDtoConverter;
import ua.drovolskyi.in.lab2.lab2backend.converters.PageToDtoConverter;
import ua.drovolskyi.in.lab2.lab2backend.dto.*;
import ua.drovolskyi.in.lab2.lab2backend.entities.BookOrder;
import ua.drovolskyi.in.lab2.lab2backend.services.BookOrderService;

import java.util.List;

@RestController
public class BookOrderController {
    private static final Logger log = LogManager.getLogger();
    private BookOrderService bookOrderService;
    private final BookOrderToDtoConverter bookOrderToDtoConverter = new BookOrderToDtoConverter();
    private final BookToDtoConverter bookToDtoConverter = new BookToDtoConverter();
    private final PageToDtoConverter<BookOrder, BookOrderDto> bookOrderPageToDtoConverter
            = new PageToDtoConverter<>(bookOrderToDtoConverter);

    public BookOrderController(BookOrderService bookOrderService){
        this.bookOrderService = bookOrderService;
    }


    @GetMapping("/bookOrder/{id}")
    public ResponseEntity<BookOrderDto> getBookOrder(
            @PathVariable(name="id") Long id
    ){
        log.info("Received GET request to '/bookOrder', ID = " + id);

        BookOrder bookOrder = bookOrderService.getBookOrderById(id);
        BookOrderDto bookOrderDto = bookOrderToDtoConverter.convert(bookOrder);

        return new ResponseEntity<>(bookOrderDto, HttpStatus.OK);
    }

    @GetMapping("/bookOrders/all")
    public ResponseEntity<List<BookOrderDto>> getAllBookOrders() {
        log.info("Received GET request to '/bookOrders/all'");

        List<BookOrderDto> bookOrdersDto = bookOrderService.getAllBookOrders()
                .stream()
                .map(bookOrderToDtoConverter::convert)
                .toList();

        return new ResponseEntity<>(bookOrdersDto, HttpStatus.OK);
    }

    @GetMapping(value = "/bookOrders/all", params = {"pageIndex", "pageSize"})
    public ResponseEntity<PageDto<BookOrderDto>> getAllBookOrdersPage(
            @RequestParam(name="pageIndex") Integer pageIndex,
            @RequestParam(name="pageSize") Integer pageSize
    ) {
        log.info(String.format("Received GET request to '/bookOrders/all', pageIndex=%d, pageSize=%d",
                pageIndex, pageSize));

        Page<BookOrder> page = bookOrderService.getAllBookOrdersPage(pageIndex, pageSize);
        PageDto<BookOrderDto> pageDto = bookOrderPageToDtoConverter.convert(page);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }


    @GetMapping(value = "/bookOrders/byBookOrderStatus", params = {"status"})
    public ResponseEntity<List<BookOrderDto>> getBookOrdersByStatus(
            @RequestParam(name="status") BookOrder.Status status
    ) {
        log.info("Received GET request to '/bookOrders/byBookOrderStatus', status=" + status.toString());

        List<BookOrderDto> bookOrdersDto = bookOrderService.getAllBookOrdersWithStatus(status)
                .stream()
                .map(bookOrderToDtoConverter::convert)
                .toList();

        return new ResponseEntity<>(bookOrdersDto, HttpStatus.OK);
    }

    @GetMapping(value = "/bookOrders/byBookOrderStatus", params = {"status", "pageIndex", "pageSize"})
    public ResponseEntity<PageDto<BookOrderDto>> getBookOrdersByStatusPage(
            @RequestParam(name="status") BookOrder.Status status,
            @RequestParam(name="pageIndex") Integer pageIndex,
            @RequestParam(name="pageSize") Integer pageSize
    ) {
        log.info(String.format("Received GET request to '/bookOrders/byBookOrderStatus', status=%s, pageIndex=%d, pageSize=%d",
                status.toString(), pageIndex, pageSize));

        Page<BookOrder> page = bookOrderService.getAllBookOrdersWithStatusPage(status, pageIndex, pageSize);
        PageDto<BookOrderDto> pageDto = bookOrderPageToDtoConverter.convert(page);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }


    @GetMapping(value = "/bookOrders/byCustomer", params = {"customerId"})
    public ResponseEntity<List<BookOrderDto>> getBookOrdersByCustomer(
            @RequestParam(name="customerId") Long customerId
    ) {
        log.info("Received GET request to '/bookOrders/byCustomer', customerId=" + customerId);

        List<BookOrderDto> bookOrdersDto = bookOrderService.getAllBookOrdersByCustomer(customerId)
                .stream()
                .map(bookOrderToDtoConverter::convert)
                .toList();

        return new ResponseEntity<>(bookOrdersDto, HttpStatus.OK);
    }

    @GetMapping(value = "/bookOrders/byCustomer", params = {"customerId", "pageIndex", "pageSize"})
    public ResponseEntity<PageDto<BookOrderDto>> getBookOrdersByCustomerPage(
            @RequestParam(name="customerId") Long customerId,
            @RequestParam(name="pageIndex") Integer pageIndex,
            @RequestParam(name="pageSize") Integer pageSize
    ) {
        log.info(String.format("Received GET request to '/bookOrders/byCustomer', customerId=%d, pageIndex=%d, pageSize=%d",
                customerId, pageIndex, pageSize));

        Page<BookOrder> page = bookOrderService.getAllBookOrdersByCustomerPage(customerId, pageIndex, pageSize);
        PageDto<BookOrderDto> pageDto = bookOrderPageToDtoConverter.convert(page);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }


    @GetMapping(value = "/bookOrders/getByBookOrderBook", params = {"bookId"})
    public ResponseEntity<List<BookOrderDto>> getBookOrdersByBook(
            @RequestParam(name= "bookId") Long bookId
    ) {
        log.info("Received GET request to '/bookOrders/byBook', customerId=" + bookId);

        List<BookOrderDto> bookOrdersDto = bookOrderService.getAllBookOrdersByBook(bookId)
                .stream()
                .map(bookOrderToDtoConverter::convert)
                .toList();

        return new ResponseEntity<>(bookOrdersDto, HttpStatus.OK);
    }

    @GetMapping(value = "/bookOrders/byBookId", params = {"bookId", "pageIndex", "pageSize"})
    public ResponseEntity<PageDto<BookOrderDto>> getBookOrdersByBookPage(
            @RequestParam(name= "bookId") Long bookId,
            @RequestParam(name="pageIndex") Integer pageIndex,
            @RequestParam(name="pageSize") Integer pageSize
    ) {
        log.info(String.format("Received GET request to '/bookOrders/byBook', bookId=%d, pageIndex=%d, pageSize=%d",
                bookId, pageIndex, pageSize));

        Page<BookOrder> page = bookOrderService.getAllBookOrdersByBookPage(bookId, pageIndex, pageSize);
        PageDto<BookOrderDto> pageDto = bookOrderPageToDtoConverter.convert(page);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }


    ///////////////////////////////////////// CUSTOMER_ID is HARDCODED
    // customer ID will be taken from authorization info
    @PostMapping("/bookOrder/create")
    public ResponseEntity<BookOrderDto> createBookOrder(
            @Valid @RequestBody CreateBookOrderDto dto
    ) {
        log.info("Received POST request to '/bookOrder/create'");
        Long customerId = 5L; ///////////////////////////////////////////////////////////////////////////
        BookOrder createdBookOrder = bookOrderService.createBookOrder(dto.getBookId(), customerId);
        BookOrderDto createdBookOrderDto = bookOrderToDtoConverter.convert(createdBookOrder);

        return new ResponseEntity<>(createdBookOrderDto, HttpStatus.OK);

    }

    // book quantity--
    @PatchMapping("/bookOrder/{id}/satisfy")
    public ResponseEntity<BookOrderDto> satisfyBookOrder(
            @PathVariable(name="id") Long bookOrderId
    ){
        BookOrder bookOrder = bookOrderService.satisfyBookOrder(bookOrderId);
        BookOrderDto bookOrderDto = bookOrderToDtoConverter.convert(bookOrder);

        return new ResponseEntity<>(bookOrderDto, HttpStatus.OK);
    }

    // book quantity++
    @PatchMapping("/bookOrder/{id}/complete")
    public ResponseEntity<BookOrderDto> completeBookOrder(
            @PathVariable(name="id") Long bookOrderId
    ){
        BookOrder bookOrder = bookOrderService.completeBookOrder(bookOrderId);
        BookOrderDto bookOrderDto = bookOrderToDtoConverter.convert(bookOrder);

        return new ResponseEntity<>(bookOrderDto, HttpStatus.OK);
    }



}