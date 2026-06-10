package com.jcaa.usersmanagement.guarderia.valueobjects;
import java.util.UUID;

public record PersonaAutorizadaId(String valor) {
    public PersonaAutorizadaId{
        if(valor==null || valor.isBlank()){
            throw new IllegalArgumentException("El ID de la persona autorizada no puede estar vacio.");
        }
    }

    public static PersonaAutorizadaId random(){
        return new PersonaAutorizadaId(UUID.randomUUID().toString());
    }
}
