package ua.drovolskyi.in.lab2.lab2backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationDto {
    @NotBlank(message = "Username can't be blank")
    @Size(min = 5, max = 25, message = "Username should have length from 5 to 25")
    @Pattern(regexp = "^\\S+$", message = "Username can't contain space character")
    private String username;

    @NotBlank(message = "Password can't be blank")
    @Size(min = 8, max = 50, message = "Password should have length from 8 to 50")
    @Pattern(regexp = "^\\S+$", message = "Password can't contain space character")
    private String password;

    @NotBlank(message = "Full name can't be blank")
    @Size(min = 1, max = 80, message = "Full name should have length from 1 to 80")
    private String fullName;

    @NotBlank(message = "Phone number can't be blank")
    @Size(min = 13, max = 13, message = "Phone number should have length 13")
    @Pattern(regexp = "^\\+380\\d{2}\\d{3}\\d{2}\\d{2}$",
            message = "Phone number must be in +380XXXXXXXXX format (where X is a digit)")
    private String phoneNumber;
}