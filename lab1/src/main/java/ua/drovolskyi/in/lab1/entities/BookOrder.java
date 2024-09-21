package ua.drovolskyi.in.lab1.entities;

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

    @Column(name="state", nullable=false, length=15)
    @Enumerated(EnumType.STRING)
    private State state;


    public static enum State{
        NEW, // customer created order
        SATISFIED, // customer took the book
        COMPLETED // customer returned the book
    }


    @Override
    public String toString(){
        return String.format("BookOrder{ID: %d, Book ID: %d, Customer ID: %d, state: %s}",
                getId(),
                getBook() != null ? getBook().getId() : -1,
                getCustomer() != null ? getCustomer().getId() : -1,
                getState().name());
    }
}
