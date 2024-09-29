package ua.drovolskyi.in.lab1.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.TrueFalseConverter;
import ua.drovolskyi.in.lab1.entities.User;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegistrationDto {
    @NotBlank(message = "Login can't be blank")
    @Size(min = 5, max = 25, message = "Login should have length from 5 to 25")
    @Pattern(regexp = "^\\S+$", message = "Login can't contain space character")
    private String login;

    @NotBlank(message = "Password can't be blank")
    @Size(min = 8, max = 50, message = "Password should have length from 8 to 50")
    @Pattern(regexp = "^\\S+$", message = "Password can't contain space character")
    private String password;

    @NotBlank(message = "Name can't be blank")
    @Size(min = 2, max = 50, message = "Name should have length from 2 to 50")
    private String name;

    @NotBlank(message = "Surname can't be blank")
    @Size(min = 2, max = 50, message = "Surname should have length from 2 to 50")
    private String surname;

    @NotBlank(message = "Patronymic can't be blank")
    @Size(min = 2, max = 50, message = "Patronymic should have length from 2 to 50")
    private String patronymic;

    @NotBlank(message = "Phone number can't be blank")
    @Size(min = 13, max = 13, message = "Phone number should have length 13")
    @Pattern(regexp = "^\\+380\\d{2}\\d{3}\\d{2}\\d{2}$",
            message = "Phone number must be in +380 XX XXX XX XX or +380-XX-XXX-XX-XX format (where X is a digit)")
    private String phoneNumber;
}
