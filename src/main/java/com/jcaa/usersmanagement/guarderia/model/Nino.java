package com.jcaa.usersmanagement.guarderia.model;

import com.jcaa.usersmanagement.guarderia.valueobjects.*;
import com.jcaa.usersmanagement.guarderia.enums.EstadoNino;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Nino {
    private final NinoId id;
    private Matricula matricula;
    private NombreCompleto nombreCompleto;
    private FechaNacimiento fechaNacimiento;
    private FechaIngreso fechaIngreso;
    private LocalDate fechaBaja;
    private EstadoNino estado;
    private Set<Ingrediente> alergias;

    public Nino(NinoId id, Matricula matricula, NombreCompleto nombreCompleto, FechaNacimiento fechaNacimiento, FechaIngreso fechaIngreso) {
        this.id=Objects.requireNonNull(id,"EL ID no puede ser nulo");
        this.matricula = Objects.requireNonNull(matricula,"La matricula no puede ser nula");
        this.nombreCompleto = Objects.requireNonNull(nombreCompleto,"El nombre no puede ser nulo");
        this.fechaNacimiento = Objects.requireNonNull(fechaNacimiento,"La fecha de ingreso no puede ser nula");
        this.fechaIngreso = Objects.requireNonNull(fechaIngreso,"La fecha de ingreso no puede ser nula");
        this.estado = EstadoNino.ACTIVO;
        this.fechaBaja=null;
        this.alergias=new HashSet<>();
    }
    public void darDeBaja(LocalDate fecha) {
        if (fecha==null)throw new IllegalArgumentException("La fecha de baja no puede ser nula");

        if(this.estado==EstadoNino.INACTIVO){
            throw new IllegalArgumentException("El niño ya se encuentra inactivo");
        }
        if(fecha.isBefore(this.fechaIngreso.valor())){
            throw new IllegalArgumentException("La fecha de baja no puede ser anterior a la fecha de ingreso");
        }
        this.estado=EstadoNino.INACTIVO;
        this.fechaBaja=fecha;
    }
    public void actualizarMatricula(Matricula nuevaMatricula){
        this.matricula=Objects.requireNonNull(nuevaMatricula,"La nueva matricula no puede ser nula");
    }
    public void eliminarAlergia(Ingrediente ingrediente){
        this.alergias.remove(ingrediente);
    }
    public boolean esAlergicoA(Ingrediente ingrediente){
        return this.alergias.contains(ingrediente);
    }

// getters

    public NinoId getId() {
        return id;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public NombreCompleto getNombreCompleto() {
        return nombreCompleto;
    }

    public FechaNacimiento getFechaNacimiento() {
        return fechaNacimiento;
    }

    public FechaIngreso getFechaIngreso() {
        return fechaIngreso;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    public EstadoNino getEstado() {
        return estado;
    }

    public Set<Ingrediente> getAlergias() {
        return Collections.unmodifiableSet(alergias);
    }
    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if(o==null || getClass() != o.getClass()) return false;
        Nino nino=(Nino)o;
        return Objects.equals(id, nino.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
