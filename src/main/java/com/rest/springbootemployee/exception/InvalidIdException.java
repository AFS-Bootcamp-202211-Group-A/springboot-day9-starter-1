package com.rest.springbootemployee.exception;

public class InvalidIdException extends RuntimeException {
    public InvalidIdException() {
        super("The id is invalid");
    }
}
