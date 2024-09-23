package ua.drovolskyi.in.lab1.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
    Class is used for creating new book (pass client->server),
    and for sending book object to client (pass server->client)
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDto {
    private Long id;

    @Size(max = 20, message = "ISBN should have max length = 20")
    private String isbn;

    @NotBlank(message = "Title can't be blank")
    @Size(min = 5, max = 50, message = "Title should have length from 5 to 50")
    private String title;

    @NotBlank(message = "Authors can't be blank")
    @Size(min = 5, max = 100, message = "Authors should have length from 5 to 100")
    private String authors;

    @NotNull(message = "Number of pages can't be null")
    @Positive(message = "Number of pages should be > 0")
    private Integer numberOfPages;

    @NotNull(message = "Publishing year can't be null")
    @Min(value = 1800, message = "Publishing year can't be less than 1800")
    // need to check that publishingYear should be <= {current year}
    private Integer publishingYear;

    private Integer quantity;
}