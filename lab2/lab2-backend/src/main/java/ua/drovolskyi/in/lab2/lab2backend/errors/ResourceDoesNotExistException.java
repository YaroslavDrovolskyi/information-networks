package ua.drovolskyi.in.lab2.lab2backend.errors;

public class ResourceDoesNotExistException extends RuntimeException{

    public ResourceDoesNotExistException(String message) {
        super(message);
    }
}