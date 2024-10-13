package ua.drovolskyi.in.lab2.lab2backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ua.drovolskyi.in.lab2.lab2backend.entities.Book;
import ua.drovolskyi.in.lab2.lab2backend.repositories.BookOrderRepository;
import ua.drovolskyi.in.lab2.lab2backend.repositories.BookRepository;

@SpringBootApplication
public class Lab2BackendApplication {
	static BookRepository bookRepository;
	static BookOrderRepository bookOrderRepository;

	public static void main(String[] args) {
		Book b = new Book(1L, "isbn", "title", "authors", 100, 2000, 100);
		System.out.println(b);

		bookRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));


		SpringApplication.run(Lab2BackendApplication.class, args);
	}

}
