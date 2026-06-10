package com.jcaa.usersmanagement.guarderia.valueobjects;
import java.time.LocalDate;
import java.time.Period;

public record FechaNacimiento(LocalDate valor) {
    public FechaNacimiento{
        if (valor==null){
            throw new IllegalArgumentException("La fecha no puede ser nula");
        }
        if (valor.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha no puede ser futura a la fecha actual");
        }
        if(Period.between(valor, LocalDate.now()).toTotalMonths()<3){
            throw new IllegalArgumentException("El niño debe tener al menos 3 meses (fecha valida)");
        }
    }
}
