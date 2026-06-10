package com.jcaa.usersmanagement.guarderia.consultas.application.service;

import com.jcaa.usersmanagement.guarderia.consultas.application.port.in.ConsultasGuarderiaUseCase;
import com.jcaa.usersmanagement.guarderia.consultas.dto.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultasGuarderiaService implements ConsultasGuarderiaUseCase {

    private final Connection connection;

    public ConsultasGuarderiaService(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<NinoEstadoDto> listarNinosConEstado() {
        String sql = "SELECT nino_id, matricula, nombre_completo, estado, fecha_baja FROM ninos";
        List<NinoEstadoDto> lista = new ArrayList<>();
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new NinoEstadoDto(
                        rs.getString("nino_id"),
                        rs.getString("matricula"),
                        rs.getString("nombre_completo"),
                        rs.getString("estado"),
                        rs.getString("fecha_baja")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error en listarNinosConEstado", e);
        }
        return lista;
    }

    @Override
    public List<PersonaAutorizadaDto> listarPersonasAutorizadasPorNino(String ninoId) {
        String sql = "SELECT pa.dni, pa.nombre_completo, pa.telefono, pa.direccion, pa.relacion " +
                "FROM personas_autorizadas pa " +
                "JOIN ninos n ON pa.nino_id = n.id " +
                "WHERE n.nino_id = ?";
        List<PersonaAutorizadaDto> lista = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, ninoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new PersonaAutorizadaDto(
                            rs.getString("dni"),
                            rs.getString("nombre_completo"),
                            rs.getString("telefono"),
                            rs.getString("direccion"),
                            rs.getString("relacion")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error en listarPersonasAutorizadasPorNino: " + ninoId, e);
        }
        return lista;
    }

    @Override
    public List<MenuConPlatosDto> listarMenusConPlatos() {
        String sql = "SELECT m.menu_id, m.nombre AS menu_nombre, p.nombre AS plato_nombre " +
                "FROM menus m LEFT JOIN platos p ON m.id = p.menu_id ORDER BY m.menu_id";
        List<MenuConPlatosDto> resultado = new ArrayList<>();
        String currentId = null;
        String currentNombre = null;
        List<String> platos = new ArrayList<>();
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                String menuId = rs.getString("menu_id");
                String menuNombre = rs.getString("menu_nombre");
                String plato = rs.getString("plato_nombre");
                if (!menuId.equals(currentId)) {
                    if (currentId != null) {
                        resultado.add(new MenuConPlatosDto(currentId, currentNombre, new ArrayList<>(platos)));
                        platos.clear();
                    }
                    currentId = menuId;
                    currentNombre = menuNombre;
                }
                if (plato != null) platos.add(plato);
            }
            if (currentId != null) resultado.add(new MenuConPlatosDto(currentId, currentNombre, platos));
        } catch (SQLException e) {
            throw new RuntimeException("Error en listarMenusConPlatos", e);
        }
        return resultado;
    }

    @Override
    public List<NinoAlergiaDto> listarNinosConAlergias() {
        String sql = "SELECT n.nino_id, n.nombre_completo, i.nombre AS ingrediente " +
                "FROM ninos n " +
                "JOIN alergias a ON n.id = a.nino_id " +
                "JOIN ingredientes i ON a.ingrediente_id = i.id " +
                "ORDER BY n.nino_id";
        List<NinoAlergiaDto> resultado = new ArrayList<>();
        String currentId = null;
        String currentNombre = null;
        List<String> ingredientes = new ArrayList<>();
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                String ninoId = rs.getString("nino_id");
                String nombre = rs.getString("nombre_completo");
                String ingrediente = rs.getString("ingrediente");
                if (!ninoId.equals(currentId)) {
                    if (currentId != null) {
                        resultado.add(new NinoAlergiaDto(currentId, currentNombre, new ArrayList<>(ingredientes)));
                        ingredientes.clear();
                    }
                    currentId = ninoId;
                    currentNombre = nombre;
                }
                ingredientes.add(ingrediente);
            }
            if (currentId != null) resultado.add(new NinoAlergiaDto(currentId, currentNombre, ingredientes));
        } catch (SQLException e) {
            throw new RuntimeException("Error en listarNinosConAlergias", e);
        }
        return resultado;
    }

    @Override
    public List<CostoMensualDto> calcularCostoMensualPorNino(int mes, int anio) {
        // Valores fijos; idealmente vendrían de una tabla de configuración
        BigDecimal costoFijo = new BigDecimal("50000");
        BigDecimal costoPorComida = new BigDecimal("5000");

        String sql = "SELECT n.nino_id, n.nombre_completo, COUNT(rc.id) AS dias_consumo " +
                "FROM ninos n " +
                "LEFT JOIN registros_consumo rc ON n.id = rc.nino_id " +
                "WHERE MONTH(rc.fecha) = ? AND YEAR(rc.fecha) = ? " +
                "GROUP BY n.id";
        List<CostoMensualDto> resultado = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, mes);
            ps.setInt(2, anio);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int dias = rs.getInt("dias_consumo");
                    BigDecimal subtotal = costoPorComida.multiply(BigDecimal.valueOf(dias));
                    BigDecimal total = costoFijo.add(subtotal);
                    resultado.add(new CostoMensualDto(
                            rs.getString("nino_id"),
                            rs.getString("nombre_completo"),
                            costoFijo,
                            dias,
                            costoPorComida,
                            subtotal,
                            total
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error en calcularCostoMensualPorNino", e);
        }
        return resultado;
    }
}