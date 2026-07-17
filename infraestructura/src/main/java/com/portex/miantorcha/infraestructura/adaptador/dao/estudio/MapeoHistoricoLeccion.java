package com.portex.miantorcha.infraestructura.adaptador.dao.estudio;

import com.portex.miantorcha.dominio.modelo.dto.DtoHistoricoLeccion;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoHistoricoLeccion implements RowMapper<DtoHistoricoLeccion> {

    @Override
    public DtoHistoricoLeccion mapRow(ResultSet rs, int rowNum) throws SQLException {
        DtoHistoricoLeccion dto = new DtoHistoricoLeccion();
        dto.setId(rs.getLong("id"));
        dto.setContadorSemana(rs.getObject("contador_semana", Integer.class));
        dto.setIdEstudioBiblico(rs.getObject("id_estudio_biblico", Long.class));
        dto.setFechaEstudio(rs.getTimestamp("fecha_estudio").toLocalDateTime());
        dto.setIdActividad(rs.getObject("id_actividad", Long.class));
        return dto;
    }
}