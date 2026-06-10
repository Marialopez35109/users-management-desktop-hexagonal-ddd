package com.jcaa.usersmanagement.guarderia.valueobjects;

public record NombreCompleto(String valor) {

    public NombreCompleto{
        if(valor == null || valor.isBlank()){
            throw new IllegalArgumentException("El nombre completo no puede estar vacio");
        }
        String trimmed = valor.trim();
        if (trimmed.length() < 3){
            throw new IllegalArgumentException("El nombre completo debe tener al menos 3 caracteres");
        }
        if(!trimmed.matches("^[a-zA-ZáéíóúüñÁÉÍÓÚÜÑ\\s]+$")){
            throw new IllegalArgumentException("Solo letras y espacios");
        }
    }
}
