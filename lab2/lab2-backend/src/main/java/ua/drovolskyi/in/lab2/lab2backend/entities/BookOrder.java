package ua.drovolskyi.in.lab2.lab2backend.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book_orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @Column(name="status", nullable=false, length=15)
    @Enumerated(EnumType.STRING)
    private Status status;


    public static enum Status {
        NEW, // customer created order
        SATISFIED, // customer took the book
        COMPLETED, // customer returned the book
        BOOK_LOST // customer took the book, and lost it
    }


    @Override
    public String toString(){
        return String.format("BookOrder{ID: %d, Book ID: %d, Customer ID: %d, status: %s}",
                getId(),
                getBook() != null ? getBook().getId() : -1,
                getCustomer() != null ? getCustomer().getId() : -1,
                getStatus().name());
    }
}
