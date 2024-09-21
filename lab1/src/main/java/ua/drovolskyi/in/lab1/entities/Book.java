package ua.drovolskyi.in.lab1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isbn", nullable = true, length=13)
    private String isbn;

    @Column(name = "title", nullable = false, length=50)
    private String title;

    @Column(name = "number_of_pages", nullable = false)
    private Integer numberOfPages;

    @Column(name = "publishing_year", nullable = false)
    private Integer publishingYear;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}