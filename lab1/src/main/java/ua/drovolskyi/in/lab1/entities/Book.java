package ua.drovolskyi.in.lab1.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isbn", nullable = true)
    private String isbn;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "number_of_pages", nullable = false)
    private Integer numberOfPages;

    @Column(name = "publishing_year", nullable = false)
    private Integer publishingYear;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}