package com.example.controllers.handler;

import com.example.exception.MessageException;
import com.example.exception.NotFoundException;
import com.example.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "ValidationException! Enter input arguments!")
    @ExceptionHandler(value = {ValidationException.class})
    protected void handlerValidationException(ValidationException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        logger.debug(e.getMessage(), e, badRequest, ZonedDateTime.now());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Data not found, change input parameter")
    @ExceptionHandler(value = {NotFoundException.class})
    protected void handlerNotFoundException(NotFoundException e) {
        HttpStatus notFoundRequest = HttpStatus.NOT_FOUND;
        logger.debug(e.getMessage(), e, notFoundRequest, ZonedDateTime.now());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Missing path variable")
    @ExceptionHandler(value = {MissingPathVariableException.class})
    protected void handlerMissingPathVariableException(MissingPathVariableException e) {
        logger.debug(e.getMessage(), e, HttpStatus.NOT_FOUND, ZonedDateTime.now());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Message bad request")
    @ExceptionHandler(value = {MessageException.class})
    protected void handlerMessageException(MessageException e) {
        logger.debug(e.getMessage(), e, HttpStatus.BAD_REQUEST, ZonedDateTime.now());
    }
}
