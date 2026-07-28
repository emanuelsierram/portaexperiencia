package com.portex.miantorcha.infraestructura.controlador.estudio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.portex.miantorcha.dominio.servicio.estudio.ServicioEliminarEstudioBiblico;
import com.portex.miantorcha.infraestructura.controlador.comando.estudio.ManejadorEliminarEstudioBiblico;

class ManejadorEliminarEstudioBiblicoTest {

    private ServicioEliminarEstudioBiblico servicioMock;
    private ManejadorEliminarEstudioBiblico manejador;

    @BeforeEach
    void setUp() {
        servicioMock = mock(ServicioEliminarEstudioBiblico.class);
        manejador = new ManejadorEliminarEstudioBiblico(servicioMock);
    }

    @Test
    void ejecutarDelegandoAlServicio() {
        Long idEstudio = 7L;

        manejador.ejecutar(idEstudio);

        verify(servicioMock, times(1)).ejecutar(idEstudio);
    }
}