package com.portex.miantorcha.dominio.modelo.servicio;

import com.portex.compartido.dominio.excepcion.ExcepcionSinDatos;
import com.portex.miantorcha.dominio.modelo.dto.DtoEstudioBiblico;
import com.portex.miantorcha.dominio.modelo.entidad.EstudioBiblico;
import com.portex.miantorcha.dominio.modelo.entidad.HistoricoLeccion;
import com.portex.miantorcha.dominio.puerto.dao.DaoEstudioBiblico;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioEstudioBiblico;
import com.portex.miantorcha.dominio.servicio.estudio.ServicioRegistrarLeccionEstudio;
import com.portex.miantorcha.testdatabuilder.EstudioBiblicoTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ServicioRegistrarLeccionEstudioTest {

    private RepositorioEstudioBiblico repositorioMock;
    private DaoEstudioBiblico daoMock;
    private ServicioRegistrarLeccionEstudio servicio;

    @BeforeEach
    void setUp() {
        repositorioMock = mock(RepositorioEstudioBiblico.class);
        daoMock = mock(DaoEstudioBiblico.class);
        servicio = new ServicioRegistrarLeccionEstudio(repositorioMock, daoMock);
    }

    @Test
    void registrarLeccionIncrementaContadorYPersisteExitosamente() {
        // Arrange
        // Construimos un estudio bíblico que inicia en lección 1 por defecto
        EstudioBiblico estudio = new EstudioBiblicoTestDataBuilder()
                .conId(45L)
                .build();
        estudio.asignarLeccionCalculada(1);

        HistoricoLeccion leccionHistorica = new HistoricoLeccion(null, 1, 45L, LocalDateTime.now(), 99L);
        DtoEstudioBiblico dtoExistente = new DtoEstudioBiblico();

        // El DAO simula que el estudio existe
        when(daoMock.consultarPorId(estudio.getId())).thenReturn(dtoExistente);

        // Act
        servicio.ejecutar(leccionHistorica, estudio);

        // Assert
        assertEquals(2, estudio.getLeccion(), "El contador de la lección del estudio bíblico debió incrementarse en 1");

        verify(repositorioMock, times(1)).registrarLeccion(leccionHistorica);
        verify(repositorioMock, times(1)).actualizar(estudio);
    }

    @Test
    void fallarAlRegistrarLeccionSiElEstudioBiblicoNoExiste() {
        // Arrange
        EstudioBiblico estudio = new EstudioBiblicoTestDataBuilder().conId(45L).build();
        HistoricoLeccion leccionHistorica = new HistoricoLeccion(null, 1, 45L, LocalDateTime.now(), 99L);

        when(daoMock.consultarPorId(estudio.getId())).thenReturn(null);

        // Act & Assert
        ExcepcionSinDatos excepcion = assertThrows(ExcepcionSinDatos.class, () -> {
            servicio.ejecutar(leccionHistorica, estudio);
        });

        assertEquals("El estudio bíblico asociado a la lección no existe.", excepcion.getMessage());

        verify(repositorioMock, never()).registrarLeccion(any());
        verify(repositorioMock, never()).actualizar(any());
    }

    @Test
    void fallarAlRegistrarLeccionSiSeSuperaElLimiteDeVeinteLecciones() {
        // Arrange
        EstudioBiblico estudio = new EstudioBiblicoTestDataBuilder().conId(45L).build();
        estudio.asignarLeccionCalculada(20);

        HistoricoLeccion leccionHistorica = new HistoricoLeccion(null, 1, 45L, LocalDateTime.now(), 99L);
        DtoEstudioBiblico dtoExistente = new DtoEstudioBiblico();

        when(daoMock.consultarPorId(estudio.getId())).thenReturn(dtoExistente);

        // Act & Assert
        IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> {
            servicio.ejecutar(leccionHistorica, estudio);
        });

        assertEquals("La lección debe estar entre 0 y 20", excepcion.getMessage());

        verify(repositorioMock, never()).registrarLeccion(any());
        verify(repositorioMock, never()).actualizar(any());
    }
}