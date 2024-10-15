package ua.drovolskyi.in.lab2.lab2backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.drovolskyi.in.lab2.lab2backend.entities.User;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String fullName;
    private String phoneNumber;
    private User.Role role;
    private Boolean isAllowedToLogin;
}