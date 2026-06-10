package com.jcaa.usersmanagement.guarderia.model;

import com.jcaa.usersmanagement.guarderia.valueobjects.MenuId;
import com.jcaa.usersmanagement.guarderia.valueobjects.Plato;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Menu {
    private final MenuId id;
    private String nombre;
    private List<Plato> platos;
    private boolean activo;

    public Menu(MenuId id, String nombre) {
        this.id=Objects.requireNonNull(id, "El id no puede ser nulo");
        this.nombre=Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        this.platos=new ArrayList<>();
        this.activo=true;
    }
    public void agregarPlato(Plato plato) {
        this.platos.add(Objects.requireNonNull(plato, "El plato debe ser nulo"));
    }
    public void eliminarPLato(Plato plato) {
        this.platos.remove(plato);
    }
    public void desactivar(){
        this.activo=false;
    }
    public void activar(){
        this.activo=true;
    }

    public MenuId getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Plato> getPlatos() {
        return Collections.unmodifiableList(platos);
    }

    public boolean isActivo() {
        return activo;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(id, menu.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
