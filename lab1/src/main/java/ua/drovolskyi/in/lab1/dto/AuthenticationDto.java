package ua.drovolskyi.in.lab1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationDto {

    @NotBlank(message = "Login can't be blank")
    @Size(min=5, max = 25, message = "Login should have length from 5 to 25")
    private String login;

    @NotBlank(message = "Password can't be blank")
    @Size(min=8, max = 50, message = "Password should have length from 8 to 50")
    String password;
}