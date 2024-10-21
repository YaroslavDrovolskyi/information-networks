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
	public static void main(String[] args) {
		SpringApplication.run(Lab2BackendApplication.class, args);
	}

}

/*
D:\Repositories\information-networks\lab1\src\main\java/ua\drovolskyi\in\lab1\controllers
D:\Repositories\information-networks\lab2\lab2-backend\src\main\java/ua\drovolskyi\in\lab2\lab2backend\controllers

 */


/*
TODO:
	Security into ExceptionHandler - check
	add roles restrictions

	fill form about start
	cancelSatisfyBookOrder
	cancelCompleteBookOrder
	setBookLostBookOrder
 */
