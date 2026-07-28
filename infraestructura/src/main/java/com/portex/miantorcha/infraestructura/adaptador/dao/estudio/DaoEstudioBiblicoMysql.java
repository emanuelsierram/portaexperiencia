package com.portex.miantorcha.infraestructura.adaptador.dao.estudio;

import com.portex.compartido.infraestructura.jbdc.CustomNamedParameterJdbcTemplate;
import com.portex.miantorcha.dominio.modelo.dto.DtoEstudioBiblico;
import com.portex.miantorcha.dominio.modelo.dto.DtoHistoricoLeccion;
import com.portex.miantorcha.dominio.puerto.dao.DaoEstudioBiblico;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoEstudioBiblicoMysql implements DaoEstudioBiblico {

    private static final String SQL_EXISTE_TELEFONO = "SELECT COUNT(1) FROM mi_antorcha.estudio_biblico WHERE telefono_persona = :telefonoPersona";

    private final CustomNamedParameterJdbcTemplate jdbcTemplate;

    public DaoEstudioBiblicoMysql(CustomNamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<DtoEstudioBiblico> consultarActualesPorMiembro(Long idUsuarioAsignado) {
        // HU: mirar mi estudio biblico actual (asumimos que en curso o por dar)
        String sql = "SELECT * FROM mi_antorcha.estudio_biblico WHERE id_usuario_asignado = :idUsuarioAsignado AND estado IN ('por dar', 'en curso')";

        MapSqlParameterSource parametros = new MapSqlParameterSource();
        parametros.addValue("idUsuarioAsignado", idUsuarioAsignado);

        return this.jdbcTemplate.getNamedParameterJdbcTemplate().query(sql, parametros, new MapeoEstudioBiblico());
    }

    @Override
    public List<DtoEstudioBiblico> consultarDisponiblesPorDar() {
        // HU: mirar todos los estudios bíblicos disponibles con estado por hacer (por dar y no asignados)
        String sql = "SELECT * FROM mi_antorcha.estudio_biblico WHERE estado = 'por dar' AND id_usuario_asignado IS NULL";

        return this.jdbcTemplate.getNamedParameterJdbcTemplate().query(sql, new MapeoEstudioBiblico());
    }

    @Override
    public DtoEstudioBiblico consultarPorId(Long id) {
        String sql = "SELECT * FROM mi_antorcha.estudio_biblico WHERE id = :id";

        MapSqlParameterSource parametros = new MapSqlParameterSource();
        parametros.addValue("id", id);

        List<DtoEstudioBiblico> resultados = this.jdbcTemplate.getNamedParameterJdbcTemplate().query(sql, parametros, new MapeoEstudioBiblico());
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    @Override
    public boolean existeTelefono(String telefonoPersona) {
        MapSqlParameterSource parametros = new MapSqlParameterSource("telefonoPersona", telefonoPersona);
        Integer count = this.jdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(SQL_EXISTE_TELEFONO, parametros, Integer.class);
        return count != null && count > 0;
    }

    @Override
    public List<DtoHistoricoLeccion> consultarHistoricoPorEstudio(Long idEstudioBiblico) {
        String sql = "SELECT id, contador_semana, id_estudio_biblico, fecha_estudio, id_actividad " +
                "FROM mi_antorcha.historico_lecciones WHERE id_estudio_biblico = :idEstudioBiblico " +
                "ORDER BY contador_semana ASC";

        MapSqlParameterSource parametros = new MapSqlParameterSource();
        parametros.addValue("idEstudioBiblico", idEstudioBiblico);

        return this.jdbcTemplate.getNamedParameterJdbcTemplate().query(sql, parametros, new MapeoHistoricoLeccion());
    }
}