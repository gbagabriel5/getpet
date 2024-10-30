package com.gba.getpet.infraestructure.controllers;

import com.gba.getpet.domain.shared.exceptions.ExceptionDto;
import com.gba.getpet.domain.shared.exceptions.PetAlreadyExistsException;
import com.gba.getpet.domain.shared.exceptions.PetNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto handleException(Exception e, WebRequest request) {
        log.error(e.getMessage());
        return new ExceptionDto(e.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(PetAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handlePetAlreadyExistsException(Exception e, WebRequest request) {
        log.error(e.getMessage());
        return new ExceptionDto(e.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(PetNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handlePetNoFoundException(Exception e, WebRequest request) {
        log.error(e.getMessage());
        return new ExceptionDto(e.getMessage(), request.getDescription(false));
    }
}
