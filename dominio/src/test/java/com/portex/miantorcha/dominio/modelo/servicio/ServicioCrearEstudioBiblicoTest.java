package com.portex.miantorcha.dominio.modelo.servicio;

import com.portex.miantorcha.dominio.modelo.entidad.EstudioBiblico;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioEstudioBiblico;
import com.portex.miantorcha.dominio.servicio.estudio.ServicioCrearEstudioBiblico;
import com.portex.miantorcha.testdatabuilder.EstudioBiblicoTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ServicioCrearEstudioBiblicoTest {

    private RepositorioEstudioBiblico repositorioMock;
    private ServicioCrearEstudioBiblico servicio;

    @BeforeEach
    void setUp() {
        repositorioMock = Mockito.mock(RepositorioEstudioBiblico.class);
        servicio = new ServicioCrearEstudioBiblico(repositorioMock);
    }

    @Test
    void crearEstudioSiempreAsignaLeccionUno() {
        // Arrange
        EstudioBiblico estudio = new EstudioBiblicoTestDataBuilder().build();

        // Act
        servicio.ejecutar(estudio);

        // Assert
        assertEquals(1, estudio.getLeccion(), "Al crear un nuevo estudio bíblico, la lección por defecto debe ser 1");

        verify(repositorioMock, times(1)).crear(estudio);
    }
}