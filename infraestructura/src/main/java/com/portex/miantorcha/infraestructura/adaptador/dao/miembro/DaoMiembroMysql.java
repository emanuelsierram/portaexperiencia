package com.portex.miantorcha.infraestructura.adaptador.dao.miembro;

import com.portex.compartido.infraestructura.jbdc.CustomNamedParameterJdbcTemplate;
import com.portex.miantorcha.dominio.modelo.dto.DtoMiembro;
import com.portex.miantorcha.dominio.puerto.dao.DaoMiembro;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoMiembroMysql implements DaoMiembro {

    private static final String SQL_LISTAR = "SELECT id_miembro, usuario_id, nombres, apellidos, email, telefono, perfil, id_grupo_pequeno, id_anciano, fecha_creacion, fecha_actualizacion FROM mi_antorcha.miembros";
    private static final String SQL_CONSULTAR_POR_ID = "SELECT id_miembro, usuario_id, nombres, apellidos, email, telefono, perfil, id_grupo_pequeno, id_anciano, fecha_creacion, fecha_actualizacion FROM mi_antorcha.miembros WHERE id_miembro = :id";

    private final CustomNamedParameterJdbcTemplate jdbcTemplate;

    public DaoMiembroMysql(CustomNamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<DtoMiembro> listar() {
        return this.jdbcTemplate.getNamedParameterJdbcTemplate().query(SQL_LISTAR, new MapeoMiembro());
    }

    @Override
    public DtoMiembro consultarPorId(Long id) {
        MapSqlParameterSource parametros = new MapSqlParameterSource();
        parametros.addValue("id", id);

        List<DtoMiembro> resultados = this.jdbcTemplate.getNamedParameterJdbcTemplate().query(SQL_CONSULTAR_POR_ID, parametros, new MapeoMiembro());
        return resultados.isEmpty() ? null : resultados.get(0);
    }
}
