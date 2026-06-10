package com.jcaa.usersmanagement.guarderia.model;

import com.jcaa.usersmanagement.guarderia.valueobjects.RegistroConsumoId;

import java.time.LocalDate;
import java.util.Objects;

public class RegistroConsumo {
    private final RegistroConsumoId id;
    private final Nino nino;
    private final Menu menu;
    private final LocalDate fecha;

    public RegistroConsumo(RegistroConsumoId id, Nino nino, Menu menu, LocalDate fecha) {
        this.id = Objects.requireNonNull(id, "El id no puede ser nulo");
        this.nino = Objects.requireNonNull(nino, "El campo no puede ser nulo");
        this.menu = Objects.requireNonNull(menu, "El menu no puede ser nulo");
        this.fecha = Objects.requireNonNull(fecha, "La fecha no puede ser nula");
        if (fecha.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de consumo no puede ser posterior a hoy");
        }
    }

    //getters
    public RegistroConsumoId getId() {
        return id;
    }

    public Nino getNino() {
        return nino;
    }

    public Menu getMenu() {
        return menu;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistroConsumo that = (RegistroConsumo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
