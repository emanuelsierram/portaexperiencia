package com.portex.miantorcha.infraestructura.adaptador.repositorio;

import com.portex.compartido.infraestructura.jbdc.CustomNamedParameterJdbcTemplate;
import com.portex.miantorcha.dominio.modelo.entidad.EstudioBiblico;
import com.portex.miantorcha.dominio.modelo.entidad.HistoricoLeccion;
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
    public Long crear(EstudioBiblico estudioBiblico) {
        String sql = "INSERT INTO mi_antorcha.estudio_biblico (id, nombre_persona, telefono_persona, direccion_persona, estado, leccion, id_usuario_asignado, persona_que_reporta, id_grupo) " +
                "VALUES (:id, :nombrePersona, :telefonoPersona, :direccionPersona, :estado, :leccion, :idUsuarioAsignado, :personaQueReporta, :idGrupo)";
        return this.jdbcTemplate.crear(estudioBiblico, sql);
    }

    @Override
    public void actualizar(EstudioBiblico estudioBiblico) {
        String sql = "UPDATE mi_antorcha.estudio_biblico SET nombre_persona = :nombrePersona, telefono_persona = :telefonoPersona, " +
                "direccion_persona = :direccionPersona, estado = :estado, leccion = :leccion, " +
                "id_usuario_asignado = :idUsuarioAsignado, persona_que_reporta = :personaQueReporta, id_grupo = :idGrupo " +
                "WHERE id = :id";

        this.jdbcTemplate.actualizar(estudioBiblico, sql);
    }

    @Override
    public void eliminar(Long id) {
        String sql = "DELETE FROM mi_antorcha.estudio_biblico WHERE id = :id";
        MapSqlParameterSource parametros = new MapSqlParameterSource();
        parametros.addValue("id", id);
        this.jdbcTemplate.getNamedParameterJdbcTemplate().update(sql, parametros);
    }

    @Override
    public void registrarLeccion(HistoricoLeccion historicoLeccion) {
        String sql = "INSERT INTO mi_antorcha.historico_lecciones (id, contador_semana, id_estudio_biblico, fecha_estudio, id_actividad) " +
                "VALUES (:id, :contadorSemana, :idEstudioBiblico, :fechaEstudio, :idActividad)";

        this.jdbcTemplate.crear(historicoLeccion, sql);
    }
}