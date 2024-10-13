package ua.drovolskyi.in.lab2.lab2backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.drovolskyi.in.lab2.lab2backend.entities.Book;

@SpringBootApplication
public class Lab2BackendApplication {

	public static void main(String[] args) {
		Book b = new Book(1L, "isbn", "title", "authors", 100, 2000, 100);
		System.out.println(b);


		SpringApplication.run(Lab2BackendApplication.class, args);
	}

}
