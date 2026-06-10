package com.jcaa.usersmanagement.guarderia.valueobjects;
import java.util.List;

public record Plato(String nombre, List<Ingrediente> ingredientes) {
    public Plato{
        if(nombre==null || nombre.isBlank()){
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if(ingredientes==null || ingredientes.isEmpty()){
            throw new IllegalArgumentException("El plato debe tener al menos un ingrediente");
        }
    }
}
