package ua.drovolskyi.in.lab1.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@RestController
@SessionAttributes({"userId", "userLogin"})
public class MainController {
    @GetMapping("/")
    public ModelAndView getMainPage(){

        // return .jsp with result
        ModelAndView modelAndView = new ModelAndView("home"); // /WEB-INF/jsp/home.jsp
        return modelAndView;
    }

    
    @GetMapping("/about")
    public ModelAndView getAboutPage(){

        // return .jsp with result
        ModelAndView modelAndView = new ModelAndView("about"); // /WEB-INF/jsp/about.jsp
        return modelAndView;
    }
}
