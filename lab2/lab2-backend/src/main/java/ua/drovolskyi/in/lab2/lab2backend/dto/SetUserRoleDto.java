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
public class SetUserRoleDto {
    @NotNull(message = "User ID can't be null")
    Long userId;

    @NotNull(message = "Role can't be null")
    User.Role role;
}
