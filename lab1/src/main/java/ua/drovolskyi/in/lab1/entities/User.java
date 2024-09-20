package ua.drovolskyi.in.lab1.entities;

import jakarta.persistence.*;
import org.hibernate.type.TrueFalseConverter;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_admin", nullable = false)
    @Convert(converter = TrueFalseConverter.class)
    private Boolean isAdmin;
}
