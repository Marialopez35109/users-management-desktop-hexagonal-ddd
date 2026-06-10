package com.jcaa.usersmanagement.guarderia.valueobjects;
import java.util.UUID;

public record NinoId(String valor) {
    public NinoId(){
        this(UUID.randomUUID().toString());
    }
    public NinoId{
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("El id no puede estar vacio");
        }
    }
    public static NinoId random(){
        return new NinoId(UUID.randomUUID().toString());
    }
}
