package com.jcaa.usersmanagement.guarderia.consultas.dto;

import java.util.List;
public record NinoAlergiaDto(String ninoId, String nombreCompleto, List<String> ingredientesProhibidos) {}