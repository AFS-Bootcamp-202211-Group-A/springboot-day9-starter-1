package com.rest.springbootemployee.Exceptions;

public class NoCompanyFoundException extends RuntimeException {
    public NoCompanyFoundException() {
        super("No company found");
    }
}
