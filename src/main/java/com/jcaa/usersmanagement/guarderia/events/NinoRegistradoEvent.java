package com.jcaa.usersmanagement.guarderia.events;
import java.time.Instant;
import java.util.Objects;

public record NinoRegistradoEvent(String ninoId, String matricula, Instant fechaOcurrencia){
    public NinoRegistradoEvent(String ninoId, String matricula){
        this(ninoId,matricula,Instant.now());
    }
    public NinoRegistradoEvent{
        Objects.requireNonNull(ninoId, "El id del niño no puede ser nulo");
        Objects.requireNonNull(matricula, "La matricula no puede ser nula");
        Objects.requireNonNull(fechaOcurrencia, "La fecha del evento no puede ser nula");
    }
}




