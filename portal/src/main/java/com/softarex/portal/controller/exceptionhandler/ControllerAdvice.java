package com.softarex.portal.controller.exceptionhandler;

import com.softarex.portal.dto.ErrorMessageDto;
import com.softarex.portal.exceptions.UserNotFoundException;
import com.softarex.portal.exceptions.WrongLoginCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessageDto handleUserNotFoundException(UserNotFoundException exception) {
        return new ErrorMessageDto(exception.getMessage());
    }

    @ExceptionHandler(value = WrongLoginCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessageDto handleWrongCredentialsException(WrongLoginCredentialsException exception) {
        return new ErrorMessageDto(exception.getMessage());
    }
}
