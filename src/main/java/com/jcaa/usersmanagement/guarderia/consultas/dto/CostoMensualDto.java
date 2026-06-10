package com.jcaa.usersmanagement.guarderia.consultas.dto;

import java.math.BigDecimal;
public record CostoMensualDto(String ninoId, String nombreCompleto, BigDecimal costoFijo, int diasConsumo, BigDecimal costoPorComida, BigDecimal subtotalComidas, BigDecimal total) {}