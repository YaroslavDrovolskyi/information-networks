package ua.drovolskyi.in.lab1;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ Exception.class })
    protected ModelAndView handleIllegalArgumentException(Exception ex, WebRequest request) {

        // return .jsp with result
        ModelAndView modelAndView = new ModelAndView("error"); // /WEB-INF/jsp/error.jsp
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }
}
