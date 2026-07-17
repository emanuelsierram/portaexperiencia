package com.portex.miantorcha.infraestructura.adaptador.dao.estudio;

import com.portex.miantorcha.dominio.modelo.dto.DtoEstudioBiblico;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoEstudioBiblico implements RowMapper<DtoEstudioBiblico> {

    @Override
    public DtoEstudioBiblico mapRow(ResultSet rs, int rowNum) throws SQLException {
        DtoEstudioBiblico dto = new DtoEstudioBiblico();
        dto.setId(rs.getLong("id"));
        dto.setNombrePersona(rs.getString("nombre_persona"));
        dto.setTelefonoPersona(rs.getString("telefono_persona"));
        dto.setDireccionPersona(rs.getString("direccion_persona"));
        dto.setEstado(rs.getString("estado"));
        dto.setLeccion(rs.getObject("leccion", Integer.class));
        dto.setPersonaQueReporta(rs.getString("persona_que_reporta"));
        dto.setIdUsuarioAsignado(rs.getObject("id_usuario_asignado", Long.class));
        dto.setIdGrupo(rs.getObject("id_grupo", Long.class));
        return dto;
    }
}