package com.portex.miantorcha.infraestructura.controlador.consulta.miembro;

import com.portex.miantorcha.dominio.modelo.dto.DtoMiembro;
import com.portex.miantorcha.infraestructura.configuracion.SeguridadMiembro;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ConsultaControladorMiembroTest {

    @Test
    void listarDebeDelegarAlManejador() {
        ManejadorListarMiembro manejador = Mockito.mock(ManejadorListarMiembro.class);
        DtoMiembro miembro = new DtoMiembro(1L, "u-1", "Juan", "Perez", "juan@test.com", "3001112233", "lider", 10L, 20L, LocalDateTime.now(), LocalDateTime.now());
        when(manejador.listar()).thenReturn(List.of(miembro));

        SeguridadMiembro seguridadMiembro = Mockito.mock(SeguridadMiembro.class);
        ConsultaControladorMiembro controlador = new ConsultaControladorMiembro(manejador, seguridadMiembro);

        ResponseEntity<List<DtoMiembro>> resultado = controlador.listar();

        assertEquals(1, resultado.getBody().size());
        assertEquals(200, resultado.getStatusCodeValue());
        verify(manejador).listar();
    }

    @Test
    void consultarPorIdDebeDelegarAlManejador() {
        ManejadorListarMiembro manejador = Mockito.mock(ManejadorListarMiembro.class);
        DtoMiembro miembro = new DtoMiembro(2L, "u-2", "Maria", "Lopez", "maria@test.com", "3002223344", "miembro", 11L, 21L, LocalDateTime.now(), LocalDateTime.now());
        when(manejador.consultarPorId(2L)).thenReturn(miembro);

        SeguridadMiembro seguridadMiembro = Mockito.mock(SeguridadMiembro.class);
        ConsultaControladorMiembro controlador = new ConsultaControladorMiembro(manejador, seguridadMiembro);

        ResponseEntity<DtoMiembro> resultado = controlador.consultarPorId(2L, null);

        assertEquals(2L, resultado.getBody().getId());
        assertEquals(200, resultado.getStatusCodeValue());
        verify(manejador).consultarPorId(2L);
    }
}
