package ua.drovolskyi.in.lab1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.TrueFalseConverter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", unique = true, nullable = false, length=25)
    private String login;

    @Column(name = "password_hash", nullable = false, length=60)
    private String passwordHash;

    @Column(name = "name", nullable = false, length=50)
    private String name;

    @Column(name = "surname", nullable = false, length=50)
    private String surname;

    @Column(name = "patronymic", nullable = false, length=50)
    private String patronymic;

    @Column(name = "phone_number", nullable = false, length=13)
    private String phoneNumber;

    @Column(name = "role", nullable = false, length=10)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "is_allowed_to_login", nullable = false)
    @Convert(converter = TrueFalseConverter.class)
    private Boolean isAllowedToLogin;

    public enum Role{
        OWNER,
        ADMIN,
        CUSTOMER
    }


    @Override
    public String toString(){
        return String.format("User{ID: %d, login: %s, name: %s, surname: %s, patronymic: %s, " +
                        "phone number: %s, role: %s, is allowed to login: %s}",
                getId(),
                getLogin(),
                getName(),
                getSurname(),
                getPatronymic(),
                getPhoneNumber(),
                getRole().name(),
                getIsAllowedToLogin().toString());
    }
}
