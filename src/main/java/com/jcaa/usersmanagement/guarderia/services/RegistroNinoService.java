package com.jcaa.usersmanagement.guarderia.services;

import com.jcaa.usersmanagement.guarderia.exceptions.NinoException;
import com.jcaa.usersmanagement.guarderia.model.Nino;

public class RegistroNinoService {
    private final ValidadorMatriculaSpecification validadorUnicidad;

    public RegistroNinoService(ValidadorMatriculaSpecification validadorUnicidad){
        this.validadorUnicidad = validadorUnicidad;
    }
    public void registrarNino(Nino nino) throws NinoException{
        if(!validadorUnicidad.esUnica(nino.getMatricula())){
            throw new NinoException("La matricula" + nino.getMatricula().valor() + "ya existe en el sistema");
        }
    }
}
