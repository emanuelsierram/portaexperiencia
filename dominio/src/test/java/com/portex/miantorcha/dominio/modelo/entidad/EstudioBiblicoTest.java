package com.portex.miantorcha.dominio.modelo.entidad;

import com.portex.compartido.dominio.excepcion.ExcepcionValorObligatorio;
import com.portex.miantorcha.testdatabuilder.EstudioBiblicoTestDataBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstudioBiblicoTest {

    @Test
    void crearEstudioBiblicoExitoso() {
        // Arrange & Act
        EstudioBiblico estudio = new EstudioBiblicoTestDataBuilder().build();

        // Assert
        assertNotNull(estudio);
        assertEquals("Juan Perez", estudio.getNombrePersona());
        assertEquals("por dar", estudio.getEstado());
    }

    @Test
    void validarNombreObligatorio() {
        // Arrange
        EstudioBiblicoTestDataBuilder builder = new EstudioBiblicoTestDataBuilder().conNombrePersona(null);

        // Act & Assert
        ExcepcionValorObligatorio excepcion = assertThrows(ExcepcionValorObligatorio.class, builder::build);
        assertEquals("El nombre de la persona es obligatorio", excepcion.getMessage());
    }

    @Test
    void validarDireccionObligatoria() {
        // Arrange
        EstudioBiblicoTestDataBuilder builder = new EstudioBiblicoTestDataBuilder().conDireccionPersona(null);

        // Act & Assert
        ExcepcionValorObligatorio excepcion = assertThrows(ExcepcionValorObligatorio.class, builder::build);
        assertEquals("La dirección de la persona es obligatoria", excepcion.getMessage());
    }

    @Test
    void asignarEstadoPorDefectoSiVieneVacio() {
        // Arrange & Act
        EstudioBiblico estudio = new EstudioBiblicoTestDataBuilder().conEstado("   ").build();

        // Assert
        assertEquals("por dar", estudio.getEstado(), "Debería asignar 'por dar' por defecto");
    }

    @Test
    void asignarLeccionCalculadaExitosa() {
        // Arrange
        EstudioBiblico estudio = new EstudioBiblicoTestDataBuilder().build();

        // Act
        estudio.asignarLeccionCalculada(5);

        // Assert
        assertEquals(5, estudio.getLeccion());
    }

    @Test
    void fallaAlAsignarLeccionInvalida() {
        // Arrange
        EstudioBiblico estudio = new EstudioBiblicoTestDataBuilder().build();

        // Act & Assert
        IllegalArgumentException excepcionMenor = assertThrows(IllegalArgumentException.class, () -> estudio.asignarLeccionCalculada(0));
        assertEquals("La lección debe estar entre 1 y 20", excepcionMenor.getMessage());

        IllegalArgumentException excepcionMayor = assertThrows(IllegalArgumentException.class, () -> estudio.asignarLeccionCalculada(21));
        assertEquals("La lección debe estar entre 1 y 20", excepcionMayor.getMessage());
    }
}