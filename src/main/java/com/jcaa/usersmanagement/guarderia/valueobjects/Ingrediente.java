package com.jcaa.usersmanagement.guarderia.valueobjects;

public record Ingrediente(String nombre) {
    public Ingrediente {

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("no puede estar vacio");
        }

        if (nombre.length() < 2) {
            throw new IllegalArgumentException("Debe tener al menos 2 caracteres");
        }
    }
}


