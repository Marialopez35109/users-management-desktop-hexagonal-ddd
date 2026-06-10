package com.jcaa.usersmanagement.guarderia.consultas.dto;

import java.util.List;
public record MenuConPlatosDto(String menuId, String nombreMenu, List<String> platos) {}