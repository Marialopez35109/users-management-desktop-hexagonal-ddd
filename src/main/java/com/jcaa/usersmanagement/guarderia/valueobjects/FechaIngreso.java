package com.jcaa.usersmanagement.guarderia.valueobjects;
import java.time.LocalDate;

public record FechaIngreso(LocalDate valor) {

    public FechaIngreso {
        if(valor==null){
            throw new IllegalArgumentException("La fecha de ingreso no puede ser nula");
        }
        if (valor.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de ingreso no puede ser futura");
        }
    }

}
