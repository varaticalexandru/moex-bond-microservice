package org.alexv.moexservice.exception;

public class BondNotFoundException extends RuntimeException{
    public BondNotFoundException(String message) {
        super(message);
    }
}
