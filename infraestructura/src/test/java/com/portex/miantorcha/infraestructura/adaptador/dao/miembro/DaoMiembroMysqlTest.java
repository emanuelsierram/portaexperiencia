package com.portex.miantorcha.infraestructura.adaptador.dao.miembro;

import com.portex.compartido.infraestructura.jbdc.CustomNamedParameterJdbcTemplate;
import com.portex.miantorcha.dominio.modelo.dto.DtoMiembro;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DaoMiembroMysqlTest {

    @Test
    void listarDebeRetornarTodosLosMiembros() {
        CustomNamedParameterJdbcTemplate jdbcTemplate = Mockito.mock(CustomNamedParameterJdbcTemplate.class);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = Mockito.mock(NamedParameterJdbcTemplate.class);
        when(jdbcTemplate.getNamedParameterJdbcTemplate()).thenReturn(namedParameterJdbcTemplate);

        DtoMiembro miembro = new DtoMiembro(1L, "u-1", "Juan", "Perez", "juan@test.com", "3001112233", "lider", 10L, 20L, LocalDateTime.now(), LocalDateTime.now());
        when(namedParameterJdbcTemplate.query(eq("SELECT id_miembro, usuario_id, nombres, apellidos, email, telefono, perfil, id_grupo_pequeno, id_anciano, fecha_creacion, fecha_actualizacion FROM mi_antorcha.miembros"), any(RowMapper.class))).thenReturn(List.of(miembro));

        DaoMiembroMysql dao = new DaoMiembroMysql(jdbcTemplate);

        List<DtoMiembro> resultado = dao.listar();

        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getId());
        verify(namedParameterJdbcTemplate).query(eq("SELECT id_miembro, usuario_id, nombres, apellidos, email, telefono, perfil, id_grupo_pequeno, id_anciano, fecha_creacion, fecha_actualizacion FROM mi_antorcha.miembros"), any(RowMapper.class));
    }

    @Test
    void consultarPorIdDebeRetornarUnMiembro() {
        CustomNamedParameterJdbcTemplate jdbcTemplate = Mockito.mock(CustomNamedParameterJdbcTemplate.class);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = Mockito.mock(NamedParameterJdbcTemplate.class);
        when(jdbcTemplate.getNamedParameterJdbcTemplate()).thenReturn(namedParameterJdbcTemplate);

        DtoMiembro miembro = new DtoMiembro(2L, "u-2", "Maria", "Lopez", "maria@test.com", "3002223344", "miembro", 11L, 21L, LocalDateTime.now(), LocalDateTime.now());
        when(namedParameterJdbcTemplate.query(eq("SELECT id_miembro, usuario_id, nombres, apellidos, email, telefono, perfil, id_grupo_pequeno, id_anciano, fecha_creacion, fecha_actualizacion FROM mi_antorcha.miembros WHERE id_miembro = :id"), any(MapSqlParameterSource.class), any(RowMapper.class))).thenReturn(List.of(miembro));

        DaoMiembroMysql dao = new DaoMiembroMysql(jdbcTemplate);

        DtoMiembro resultado = dao.consultarPorId(2L);

        assertEquals(2L, resultado.getId());
        assertEquals("Maria", resultado.getNombres());
    }

    @Test
    void consultarPorUsuarioIdDebeRetornarUnMiembro() {
        CustomNamedParameterJdbcTemplate jdbcTemplate = Mockito.mock(CustomNamedParameterJdbcTemplate.class);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = Mockito.mock(NamedParameterJdbcTemplate.class);
        when(jdbcTemplate.getNamedParameterJdbcTemplate()).thenReturn(namedParameterJdbcTemplate);

        DtoMiembro miembro = new DtoMiembro(9L, "3002539848", "Maria", "Lopez", "maria@test.com", "3002223344", "miembro", 11L, 21L, LocalDateTime.now(), LocalDateTime.now());
        when(namedParameterJdbcTemplate.query(eq("SELECT id_miembro, usuario_id, nombres, apellidos, email, telefono, perfil, id_grupo_pequeno, id_anciano, fecha_creacion, fecha_actualizacion FROM mi_antorcha.miembros WHERE usuario_id = :usuarioId"), any(MapSqlParameterSource.class), any(RowMapper.class))).thenReturn(List.of(miembro));

        DaoMiembroMysql dao = new DaoMiembroMysql(jdbcTemplate);

        DtoMiembro resultado = dao.consultarPorUsuarioId("3002539848");

        assertEquals(9L, resultado.getId());
        assertEquals("3002539848", resultado.getUsuarioId());
    }
}
