package com.portex.miantorcha.dominio.modelo.servicio;

import com.portex.compartido.dominio.excepcion.ExcepcionDuplicidad;
import com.portex.compartido.dominio.excepcion.ExcepcionValorObligatorio;
import com.portex.compartido.dominio.puerto.repositorio.RepositorioUsuario;
import com.portex.miexperiencia.BasePrueba;
import com.portex.miantorcha.dominio.modelo.entidad.Miembro;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioMiembro;
import com.portex.miantorcha.dominio.servicio.miembro.ServicioCrearMiembro;
import com.portex.miantorcha.testdatabuilder.MiembroTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class ServicioCrearMiembroTest {

    private RepositorioMiembro repositorioMiembro;
    private ServicioCrearMiembro servicioCrearMiembro;

    @BeforeEach
    public void setup() {
        repositorioMiembro = Mockito.mock(RepositorioMiembro.class);
        servicioCrearMiembro = new ServicioCrearMiembro(repositorioMiembro);
    }

    @Test
    public void validarCreacionMiembroExitoso() {
        // Arrange
        Miembro miembro = new MiembroTestDataBuilder().build();
        Mockito.when(repositorioMiembro.existeTelefono(miembro.getEmail())).thenReturn(false);
        Mockito.when(repositorioMiembro.crear(miembro)).thenReturn(1L);

        // Act
        Long idGenerado = servicioCrearMiembro.ejecutar(miembro);

        // Assert
        ArgumentCaptor<Miembro> miembroArgumentCaptor = ArgumentCaptor.forClass(Miembro.class);
        Mockito.verify(repositorioMiembro).crear(miembroArgumentCaptor.capture());
        Assertions.assertEquals(1L, idGenerado);
        Assertions.assertEquals("Juan", miembroArgumentCaptor.getValue().getNombres());
    }

    @Test
    public void validarFallaPorEmailDuplicado() {
        // Arrange
        Miembro miembro = new MiembroTestDataBuilder().conTelefono("duplicado@email.com").build();
        Mockito.when(repositorioMiembro.existeTelefono(miembro.getTelefono())).thenReturn(true);

        // Act & Assert
        BasePrueba.assertThrows(() -> servicioCrearMiembro.ejecutar(miembro),
                ExcepcionDuplicidad.class,
                "Ya existe un miembro registrado con este telefono");
    }

    @Test
    public void validarFallaPorFaltaDeNombres() {
        // Arrange
        MiembroTestDataBuilder builder = new MiembroTestDataBuilder().conNombres(null);

        // Act & Assert
        BasePrueba.assertThrows(builder::build,
                ExcepcionValorObligatorio.class,
                "Se debe ingresar el nombre");
    }
}