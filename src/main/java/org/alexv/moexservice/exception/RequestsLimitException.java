package org.alexv.moexservice.exception;

public class RequestsLimitException extends RuntimeException {
    public RequestsLimitException(String message) {
        super(message);
    }
}
