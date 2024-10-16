package ua.drovolskyi.in.lab2.lab2backend.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.drovolskyi.in.lab2.lab2backend.entities.User;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SetUserIsAllowedToLoginDto {
    @NotNull(message = "User ID can't be null")
    Long userId;

    @NotNull(message = "isAllowedToLogin can't be null")
    Boolean isAllowedToLogin;
}
