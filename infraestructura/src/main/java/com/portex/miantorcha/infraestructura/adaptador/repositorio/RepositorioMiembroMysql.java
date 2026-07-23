package com.portex.miantorcha.infraestructura.adaptador.repositorio;

import com.portex.compartido.infraestructura.jbdc.CustomNamedParameterJdbcTemplate;
import com.portex.miantorcha.dominio.modelo.entidad.Miembro;
import com.portex.miantorcha.dominio.modelo.dto.DtoMiembro;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioMiembro;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioMiembroMysql implements RepositorioMiembro {

    private static final String SQL_CREAR = "INSERT INTO mi_antorcha.miembros (usuario_id, nombres, apellidos, email, telefono, perfil, id_grupo_pequeno, id_anciano, fecha_creacion, fecha_actualizacion) VALUES (:usuarioId, :nombres, :apellidos, :email, :telefono, :perfil, :idGrupoPequeno, :idAnciano, :fechaCreacion, :fechaActualizacion)";
    private static final String SQL_ACTUALIZAR = "UPDATE mi_antorcha.miembros SET nombres = :nombres, apellidos = :apellidos, email = :email, perfil = :perfil, id_grupo_pequeno = :idGrupoPequeno, id_anciano = :idAnciano, fecha_actualizacion = :fechaActualizacion WHERE id_miembro = :id";
    private static final String SQL_ELIMINAR = "DELETE FROM mi_antorcha.miembros WHERE id_miembro = :id";
    private static final String SQL_EXISTE_TELEFONO = "SELECT COUNT(1) FROM mi_antorcha.miembros WHERE telefono = :telefono";

    private final CustomNamedParameterJdbcTemplate customJdbcTemplate;

    public RepositorioMiembroMysql(CustomNamedParameterJdbcTemplate customJdbcTemplate) {
        this.customJdbcTemplate = customJdbcTemplate;
    }

    @Override
    public Long crear(Miembro miembro) {
        return this.customJdbcTemplate.crear(miembro, SQL_CREAR);
    }

    @Override
    public void actualizar(Miembro miembro) {
        this.customJdbcTemplate.actualizar(miembro, SQL_ACTUALIZAR);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource parametros = new MapSqlParameterSource("id", id);
        this.customJdbcTemplate.getNamedParameterJdbcTemplate().update(SQL_ELIMINAR, parametros);
    }

    @Override
    public boolean existeTelefono(String telefono) {
        MapSqlParameterSource parametros = new MapSqlParameterSource("telefono", telefono);
        Integer count = this.customJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(SQL_EXISTE_TELEFONO, parametros, Integer.class);
        return count != null && count > 0;
    }
}