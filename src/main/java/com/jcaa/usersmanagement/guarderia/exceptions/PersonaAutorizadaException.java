package com.jcaa.usersmanagement.guarderia.exceptions;

public class PersonaAutorizadaException extends RuntimeException {
    public PersonaAutorizadaException(String message){
        super(message);
    }
    public PersonaAutorizadaException(String message, Throwable cause){
        super(message, cause);
    }
}
