package com.jcaa.usersmanagement.guarderia.factory;

import com.jcaa.usersmanagement.guarderia.valueobjects.*;
import com.jcaa.usersmanagement.guarderia.model.Nino;

import java.time.LocalDate;

public class NinoFactory {
    public static Nino crearNino(String matriculaStr,String nombreStr, LocalDate fechaNacimiento, LocalDate fechaIngreso){
        Matricula matricula = new Matricula(matriculaStr);
        NombreCompleto nombre = new NombreCompleto(nombreStr);
        FechaNacimiento fechaNac= new FechaNacimiento(fechaNacimiento);
        FechaIngreso fechaIng= new FechaIngreso(fechaIngreso);
        NinoId id = NinoId.random();

        return new Nino(id,matricula,nombre,fechaNac,fechaIng);
    }
}
