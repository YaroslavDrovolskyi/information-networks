package ua.drovolskyi.in.lab2.lab2backend.entities;

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

    @Column(name = "username", unique = true, nullable = false, length=25)
    private String username;

    @Column(name = "password_hash", nullable = false, length=60)
    private String passwordHash;

    @Column(name = "full_name", nullable = false, length=80)
    private String fullName;

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
                getUsername(),
                getFullName(),
                getPhoneNumber(),
                getRole().name(),
                getIsAllowedToLogin().toString());
    }
}
