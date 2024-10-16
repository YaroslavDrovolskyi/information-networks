package ua.drovolskyi.in.lab2.lab2backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.drovolskyi.in.lab2.lab2backend.entities.BookOrder;

/*
    Class is used for sending BookOrder (pass client->server, and client->server)
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookOrderDto {
    private Long id;

    @NotNull(message = "Book ID can't be null")
    private Long bookId;

    private String bookTitle;

    @NotNull(message = "Customer ID can't be null")
    private Long customerId;

    private String customerUsername;

    private BookOrder.Status status;



}