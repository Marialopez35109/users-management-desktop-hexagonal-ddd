package com.jcaa.usersmanagement.guarderia.valueobjects;
import java.util.UUID;

public record MenuId(String valor) {
    public MenuId{
        if(valor== null || valor.isBlank()){
            throw new IllegalArgumentException("El ID del menú no puede esta vacio");
        }
    }
    public static MenuId random(){
        return new MenuId(UUID.randomUUID().toString());
    }
}
