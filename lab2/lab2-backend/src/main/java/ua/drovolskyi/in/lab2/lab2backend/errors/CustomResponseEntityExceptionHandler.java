package ua.drovolskyi.in.lab2.lab2backend.errors;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.ConstraintViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;


/**
 * Class that intercepts each unhandled exception and sends error to HTTP-client, that caused this exception
 */
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = LogManager.getLogger();

    /**
     * Handles exception caused by failed validation
     * @param ex the exception to handle
     * @param headers the headers to be written to the response
     * @param status the selected response status
     * @param request the current request
     * @return
     */
    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        List<ApiError.ErrorMessage> errorMessages = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errorMessages.add(new ApiError.ErrorMessage(
                    /*error.getField() + ": " + */ error.getDefaultMessage())
            );
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errorMessages.add(new ApiError.ErrorMessage(
                    /*error.getObjectName() + ": " +*/ error.getDefaultMessage())
            );
        }

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, errorMessages);

        return handleExceptionInternal(
                ex, apiError, headers, apiError.getStatus(), request);
    }

    /**
     * Handles exception when endpoint input parameter is not readable (for example, invalid string for enum)
     * @param ex the exception to handle
     * @param headers the headers to use for the response
     * @param status the status code to use for the response
     * @param request the current request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
                new ApiError.ErrorMessage("Input parameters are not readable"));
        log.error(apiError.errorMessagesToString());

        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Nullable
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
                new ApiError.ErrorMessage(ex.getMessage()));
        log.error(apiError.errorMessagesToString());

        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ IllegalArgumentException.class, JsonProcessingException.class})
    protected ResponseEntity<Object> handleIllegalArgumentException(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
                new ApiError.ErrorMessage(ex.getMessage()));
        log.error(apiError.errorMessagesToString());

        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ ResourceDoesNotExistException.class })
    protected ResponseEntity<Object> handleResourceDoesNotExistException(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,
                new ApiError.ErrorMessage(ex.getMessage()));
        log.error(apiError.errorMessagesToString());

        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }


    /**
     This handler is triggered when user has role, that not allowed to call endpoint
     */
/*    @ExceptionHandler({ AccessDeniedException.class })
    protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {

        ApiError apiError = new ApiError(HttpStatus.FORBIDDEN,
                new ApiError.ErrorMessage(ex.getMessage()));
        log.error(apiError.errorMessagesToString());

        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }*/

    /**
     * This handles another exceptions
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({ RuntimeException.class })
    protected ResponseEntity<Object> handleRuntimeException(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                new ApiError.ErrorMessage(ex.getMessage()));

        log.error(ex.getMessage());
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }


}
