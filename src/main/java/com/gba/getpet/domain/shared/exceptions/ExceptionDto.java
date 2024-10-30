package com.gba.getpet.domain.shared.exceptions;

import lombok.Getter;
import java.util.Date;
import java.util.List;

@Getter
public class ExceptionDto {

    private final List<String> errors;
    private final Date timestamp;
    private final String details;

    public ExceptionDto(String error, String details) {
        this.errors = List.of(error);
        this.details = details;
        this.timestamp = new Date();
    }
}
