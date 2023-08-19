package com.sistemacar.parkapi.exception;

public class EntityExistsException extends RuntimeException {
    public EntityExistsException(String msg) {
        super(msg);
    }
}
