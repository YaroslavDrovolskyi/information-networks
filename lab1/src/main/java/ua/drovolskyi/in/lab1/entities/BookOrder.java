package ua.drovolskyi.in.lab1.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "book_orders")
public class BookOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer; // customer who ordered the book

    @Column(name="state", nullable=false)
    @Enumerated(EnumType.STRING)
    private State state;


    public static enum State{
        NEW, // customer created order
        SATISFIED, // customer took the book
        COMPLETED // customer returned the book
    }
}
