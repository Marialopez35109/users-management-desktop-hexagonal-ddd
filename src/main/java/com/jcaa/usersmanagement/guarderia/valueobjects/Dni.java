package com.jcaa.usersmanagement.guarderia.valueobjects;

public record Dni(String valor) {
    public Dni{
        if(valor == null || valor.isBlank()){
            throw new IllegalArgumentException("DNI invalido.");
        }
        if(!valor.matches("\\d{8,10}[A-Z]?")){
            throw new IllegalArgumentException("Debe tener entr 8-10 digitos y letras opcionalmente.");
        }
    }
}
