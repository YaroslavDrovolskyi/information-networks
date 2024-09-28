package ua.drovolskyi.in.lab1.errors;

public class AuthException extends RuntimeException{

    public AuthException(String message) {
        super(message);
    }
}
