package com.portex.miantorcha.infraestructura.adaptador.dao.miembro;

import com.portex.miantorcha.dominio.modelo.dto.DtoMiembro;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoMiembro implements RowMapper<DtoMiembro> {

    @Override
    public DtoMiembro mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DtoMiembro(
                rs.getLong("id_miembro"),
                rs.getString("usuario_id"),
                rs.getString("nombres"),
                rs.getString("apellidos"),
                rs.getString("email"),
                rs.getString("telefono"),
                rs.getString("perfil"),
                rs.getObject("id_grupo_pequeno") != null ? rs.getLong("id_grupo_pequeno") : null,
                rs.getObject("id_anciano") != null ? rs.getLong("id_anciano") : null,
                rs.getObject("fecha_creacion", LocalDateTime.class),
                rs.getObject("fecha_actualizacion", LocalDateTime.class)
        );
    }
}
