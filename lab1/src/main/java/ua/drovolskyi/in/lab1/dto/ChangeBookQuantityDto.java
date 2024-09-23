package ua.drovolskyi.in.lab1.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChangeBookQuantityDto {
    @NotNull(message = "Book ID can't be null")
    private Long bookId;

    @NotNull(message = "Quantity delta can't be null")
    private Integer quantityDelta; // is the amount you want to add to the book amount
}