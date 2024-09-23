package ua.drovolskyi.in.lab1.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.drovolskyi.in.lab1.entities.BookOrder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChangeBookOrderStatusDto {
    @NotNull(message = "BookOrder ID can't be null")
    private Long bookOrderId;

    @NotNull(message = "New status can't be null")
    private BookOrder.Status newStatus;
}
