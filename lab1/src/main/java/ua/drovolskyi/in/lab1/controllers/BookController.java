package ua.drovolskyi.in.lab1.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BookController {

    @GetMapping("/books")
    public ModelAndView getBooks(Model model) {
        model.addAttribute("book", "Some BOOK");
        return new ModelAndView("view-books"); // return a view template 'view-books'
        // Spring MVC will look for /WEB-INF/jsp/view-books.jsp file
    }
}

// how to return .jsp: https://www.youtube.com/watch?v=BSPq2YgIwwE
