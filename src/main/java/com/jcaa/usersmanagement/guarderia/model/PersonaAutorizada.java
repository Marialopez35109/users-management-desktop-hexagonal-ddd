package com.jcaa.usersmanagement.guarderia.model;

import com.jcaa.usersmanagement.guarderia.valueobjects.*;
import com.jcaa.usersmanagement.guarderia.valueobjects.PersonaAutorizadaId;
import com.jcaa.usersmanagement.guarderia.enums.TipoRelacion;

import java.util.Objects;

public class PersonaAutorizada {

    private final PersonaAutorizadaId id;
    private Dni dni;
    private NombreCompleto nombreCompleto;
    private Telefono telefono;
    private Direccion direccion;
    private TipoRelacion relacion;

    public PersonaAutorizada(PersonaAutorizadaId id, Dni dni, NombreCompleto nombreComepleto, Telefono telefono, Direccion direccion, TipoRelacion relacion){
        this.id = Objects.requireNonNull(id, "El ID no puede ser nulo");
        this.dni = Objects.requireNonNull(dni, "El DNI no puede ser nulo");
        this.nombreCompleto = Objects.requireNonNull(nombreCompleto, "El nombre no puede ser nulo");
        this.telefono =Objects.requireNonNull(telefono,"El telefono no puede ser nulo");
        this.direccion =Objects.requireNonNull(direccion,"La direccion no puede ser nnula");
        this.relacion=Objects.requireNonNull(relacion,"La relacion no puede ser nula");
    }
    public void actualizarTelefono(Telefono nuevoTelefono){
        this.telefono=Objects.requireNonNull(nuevoTelefono, "El telefono no puede ser nulo");
    }
    public void actualizarDireccion(Direccion nuevaDireccion){
        this.direccion=Objects.requireNonNull(nuevaDireccion, "La direccion no puede ser nula");
    }
    public PersonaAutorizadaId getId(){
        return id;
    }
    public Dni getDni(){
    return dni;
    }
    public NombreCompleto getNombreCompleto(){
    return nombreCompleto;
    }
    public Telefono getTelefono(){
        return telefono;
    }
    public Direccion getDireccion(){
        return direccion;
    }
    public TipoRelacion getRelacion(){
        return relacion;
    }
    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || getClass() != o.getClass()) return false;
        PersonaAutorizada that = (PersonaAutorizada) o;
        return Objects.equals(id,that.id);
    }
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}
