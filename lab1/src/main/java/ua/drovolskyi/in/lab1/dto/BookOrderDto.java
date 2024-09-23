package ua.drovolskyi.in.lab1.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.drovolskyi.in.lab1.entities.BookOrder;

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

    @NotNull(message = "Customer ID can't be null")
    private Long customerId;
    
    private BookOrder.Status status;
}