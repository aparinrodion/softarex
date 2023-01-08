package com.softarex.portal.exceptions;

public class WrongLoginCredentialsException extends RuntimeException {
    public WrongLoginCredentialsException(String message) {
        super(message);
    }
}
