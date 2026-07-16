package com.portex.miantorcha.infraestructura.adaptador.repositorio;

import com.portex.compartido.infraestructura.jbdc.CustomNamedParameterJdbcTemplate;
import com.portex.miantorcha.dominio.modelo.entidad.EstudioBiblico;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioEstudioBiblico;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioEstudioBiblicoMysql implements RepositorioEstudioBiblico {

    private final CustomNamedParameterJdbcTemplate jdbcTemplate;

    public RepositorioEstudioBiblicoMysql(CustomNamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void crear(EstudioBiblico estudioBiblico) {
        // Los nombres de los parámetros (:id, :nombrePersona) deben coincidir exactamente con
        // los nombres de los atributos/getters en la clase EstudioBiblico.
        String sql = "INSERT INTO estudio_biblico (id, nombre_persona, telefono_persona, direccion_persona, estado, leccion, id_usuario_asignado, persona_que_reporta, id_grupo) " +
                "VALUES (:id, :nombrePersona, :telefonoPersona, :direccionPersona, :estado, :leccion, :idUsuarioAsignado, :personaQueReporta, :idGrupo)";

        this.jdbcTemplate.crear(estudioBiblico, sql);
    }

    @Override
    public void actualizar(EstudioBiblico estudioBiblico) {
        String sql = "UPDATE estudio_biblico SET nombre_persona = :nombrePersona, telefono_persona = :telefonoPersona, " +
                "direccion_persona = :direccionPersona, estado = :estado, leccion = :leccion, " +
                "id_usuario_asignado = :idUsuarioAsignado, persona_que_reporta = :personaQueReporta, id_grupo = :idGrupo " +
                "WHERE id = :id";

        this.jdbcTemplate.actualizar(estudioBiblico, sql);
    }

    @Override
    public void eliminar(String id) {
        String sql = "DELETE FROM estudio_biblico WHERE id = :id";
        MapSqlParameterSource parametros = new MapSqlParameterSource();
        parametros.addValue("id", id);

        this.jdbcTemplate.getNamedParameterJdbcTemplate().update(sql, parametros);
    }

    @Override
    public int contarLeccionesPorPersona(String nombrePersona, String telefonoPersona) {
        String sql = "SELECT COUNT(*) FROM historico_lecciones WHERE nombre_persona = :nombrePersona AND telefono_persona = :telefonoPersona";
        MapSqlParameterSource parametros = new MapSqlParameterSource();
        parametros.addValue("nombrePersona", nombrePersona);
        parametros.addValue("telefonoPersona", telefonoPersona);

        Integer cantidad = this.jdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sql, parametros, Integer.class);
        return cantidad != null ? cantidad : 0;
    }
}