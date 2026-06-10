package com.jcaa.usersmanagement.guarderia.consultas.application.port.in;

import com.jcaa.usersmanagement.guarderia.consultas.dto.*;
import java.util.List;

public interface ConsultasGuarderiaUseCase {
    List<NinoEstadoDto> listarNinosConEstado();
    List<PersonaAutorizadaDto> listarPersonasAutorizadasPorNino(String ninoId);
    List<MenuConPlatosDto> listarMenusConPlatos();
    List<NinoAlergiaDto> listarNinosConAlergias();
    List<CostoMensualDto> calcularCostoMensualPorNino(int mes, int anio);
}