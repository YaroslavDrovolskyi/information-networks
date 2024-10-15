package ua.drovolskyi.in.lab2.lab2backend.errors;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ApiError {

    private HttpStatus status;
    private List<ErrorMessage> errorMessages;

    public ApiError(HttpStatus status, List<ErrorMessage> errorMessages) {
        this.status = status;
        this.errorMessages = errorMessages;
    }

    public ApiError(HttpStatus status, ErrorMessage errorMessage) {
        this.status = status;
        this.errorMessages = Arrays.asList(errorMessage);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public List<ErrorMessage> getErrorMessages() {
        return errorMessages;
    }

    public String errorMessagesToString(){
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        for(ErrorMessage errorMessage : getErrorMessages()){
            sb.append(errorMessage.toString());
            sb.append(", ");
        }
        if(!errorMessages.isEmpty()){
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");

        return sb.toString();
    }

    public static class ErrorMessage{
        private String text;

        public ErrorMessage(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        @Override
        public String toString(){
            return String.format("{'%s'}", getText());
        }
    }
}