package com.jcaa.usersmanagement.guarderia.services;
import com.jcaa.usersmanagement.guarderia.valueobjects.Matricula;

@FunctionalInterface
public interface ValidadorMatriculaSpecification {
    boolean esUnica(Matricula matricula);

}
