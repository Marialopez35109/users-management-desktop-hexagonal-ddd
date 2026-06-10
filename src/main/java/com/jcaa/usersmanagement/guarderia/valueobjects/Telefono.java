package com.jcaa.usersmanagement.guarderia.valueobjects;

public record Telefono(String valor) {
    public Telefono{
        if(valor == null || !valor.matches("\\d{7,15}")){
            throw new IllegalArgumentException("Telefono invalido. Solo numeros de 7-15 digitos");
        }
    }
}
