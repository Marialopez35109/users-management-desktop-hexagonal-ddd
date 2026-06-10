package com.jcaa.usersmanagement.guarderia;

import com.jcaa.usersmanagement.guarderia.consultas.application.service.ConsultasGuarderiaService;
import com.jcaa.usersmanagement.guarderia.consultas.dto.*;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.config.DatabaseConfig;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.config.DatabaseConnectionFactory;
import com.jcaa.usersmanagement.infrastructure.config.AppProperties;
import java.sql.Connection;
import java.util.List;

public class PruebaConsultas {

    public static void main(String[] args) {
        AppProperties properties = new AppProperties();
        DatabaseConfig config = new DatabaseConfig(
                properties.get("db.host"),
                properties.getInt("db.port"),
                properties.get("db.name"),
                properties.get("db.username"),
                properties.get("db.password")
        );
        Connection connection = DatabaseConnectionFactory.createConnection(config);

        ConsultasGuarderiaService consultas = new ConsultasGuarderiaService(connection);

        System.out.println("=== CONSULTA 1: Niños con estado ===");
        List<NinoEstadoDto> ninos = consultas.listarNinosConEstado();
        ninos.forEach(n -> System.out.println(n.ninoId() + " | " + n.matricula() + " | " + n.nombreCompleto() + " | " + n.estado()));

        System.out.println("\n=== CONSULTA 2: Personas autorizadas por niño (ejemplo con ninoId='nino1') ===");
        List<PersonaAutorizadaDto> autorizadas = consultas.listarPersonasAutorizadasPorNino("nino1");
        autorizadas.forEach(a -> System.out.println(a.dni() + " | " + a.nombreCompleto() + " | " + a.relacion()));

        System.out.println("\n=== CONSULTA 3: Menús con platos ===");
        List<MenuConPlatosDto> menus = consultas.listarMenusConPlatos();
        menus.forEach(m -> {
            System.out.println("Menú: " + m.nombreMenu() + " (" + m.menuId() + ")");
            m.platos().forEach(p -> System.out.println("  - " + p));
        });

        System.out.println("\n=== CONSULTA 4: Niños con alergias ===");
        List<NinoAlergiaDto> alergias = consultas.listarNinosConAlergias();
        alergias.forEach(a -> {
            System.out.println(a.nombreCompleto() + " (ID: " + a.ninoId() + ")");
            a.ingredientesProhibidos().forEach(i -> System.out.println("  - " + i));
        });

        System.out.println("\n=== CONSULTA 5: Costo mensual por niño (mes=6, año=2026) ===");
        List<CostoMensualDto> costos = consultas.calcularCostoMensualPorNino(6, 2026);
        costos.forEach(c -> System.out.printf("%s | %s | Fijo: $%.2f | Días: %d | Total: $%.2f%n",
                c.ninoId(), c.nombreCompleto(), c.costoFijo(), c.diasConsumo(), c.total()));
    }
}