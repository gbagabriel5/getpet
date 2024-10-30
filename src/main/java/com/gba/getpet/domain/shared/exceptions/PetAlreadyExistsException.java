package com.gba.getpet.domain.shared.exceptions;

public class PetAlreadyExistsException extends RuntimeException {
    public PetAlreadyExistsException(String message) {
        super(message);
    }
}
