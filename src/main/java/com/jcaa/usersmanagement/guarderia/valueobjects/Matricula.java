package com.jcaa.usersmanagement.guarderia.valueobjects;

public record Matricula(String valor) {
    public Matricula{
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("La matricula no puede estar vacia");
        }
        if (!valor.matches("^[A-Z0-9]{3,10}$")){
            throw new IllegalArgumentException("Matricula invalida, Usar 3-10 caracteres ");
        }
    }

}
