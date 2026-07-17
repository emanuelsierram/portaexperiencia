package com.portex.miantorcha.dominio.modelo.servicio;

import com.portex.compartido.dominio.excepcion.ExcepcionSinDatos;
import com.portex.miantorcha.dominio.modelo.dto.DtoEstudioBiblico;
import com.portex.miantorcha.dominio.modelo.entidad.EstudioBiblico;
import com.portex.miantorcha.dominio.puerto.dao.DaoEstudioBiblico;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioEstudioBiblico;
import com.portex.miantorcha.dominio.servicio.estudio.ServicioActualizarEstudioBiblico;
import com.portex.miantorcha.testdatabuilder.EstudioBiblicoTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ServicioActualizarEstudioBiblicoTest {

    private RepositorioEstudioBiblico repositorioMock;
    private DaoEstudioBiblico daoMock;
    private ServicioActualizarEstudioBiblico servicio;

    @BeforeEach
    void setUp() {
        repositorioMock = mock(RepositorioEstudioBiblico.class);
        daoMock = mock(DaoEstudioBiblico.class);
        servicio = new ServicioActualizarEstudioBiblico(repositorioMock, daoMock);
    }

    @Test
    void actualizarEstudioBiblicoExitoso() {
        // Arrange
        EstudioBiblico estudio = new EstudioBiblicoTestDataBuilder().build();
        DtoEstudioBiblico dtoExistente = new DtoEstudioBiblico();

        // El DAO simula que el estudio existe en la base de datos
        when(daoMock.consultarPorId(estudio.getId())).thenReturn(dtoExistente);

        // Act
        servicio.ejecutar(estudio);

        // Assert
        verify(daoMock, times(1)).consultarPorId(estudio.getId());
        verify(repositorioMock, times(1)).actualizar(estudio);
    }

    @Test
    void fallarAlActualizarEstudioBiblicoNoExistente() {
        // Arrange
        EstudioBiblico estudio = new EstudioBiblicoTestDataBuilder().build();

        // El DAO simula que el estudio NO existe (devuelve null)
        when(daoMock.consultarPorId(estudio.getId())).thenReturn(null);

        // Act & Assert
        ExcepcionSinDatos excepcion = assertThrows(ExcepcionSinDatos.class, () -> {
            servicio.ejecutar(estudio);
        });

        assertEquals("El estudio bíblico que intenta actualizar no existe.", excepcion.getMessage());
        verify(repositorioMock, never()).actualizar(any());
    }
}