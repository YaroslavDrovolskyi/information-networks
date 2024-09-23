package ua.drovolskyi.in.lab1.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.TrueFalseConverter;
import ua.drovolskyi.in.lab1.entities.User;

/*
    Class is used for sending use data (server->client)
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    private String login;
    private String name;
    private String surname;
    private String patronymic;
    private String phoneNumber;
    private User.Role role;
    private Boolean isValid;
}
