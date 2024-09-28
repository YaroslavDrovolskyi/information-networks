package ua.drovolskyi.in.lab1.errors;

// if user is authenticated but tries to access endpoint that is not permitted for it
public class AccessDeniedException extends RuntimeException{

    public AccessDeniedException(String message) {
        super(message);
    }
}