package com.portex.miantorcha.dominio.modelo.servicio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.portex.compartido.dominio.excepcion.ExcepcionSinDatos;
import com.portex.miantorcha.dominio.modelo.dto.DtoEstudioBiblico;
import com.portex.miantorcha.dominio.puerto.dao.DaoEstudioBiblico;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioEstudioBiblico;
import com.portex.miantorcha.dominio.servicio.estudio.ServicioEliminarEstudioBiblico;

class ServicioEliminarEstudioBiblicoTest {

    private RepositorioEstudioBiblico repositorioMock;
    private DaoEstudioBiblico daoMock;
    private ServicioEliminarEstudioBiblico servicio;

    @BeforeEach
    void setUp() {
        repositorioMock = mock(RepositorioEstudioBiblico.class);
        daoMock = mock(DaoEstudioBiblico.class);
        servicio = new ServicioEliminarEstudioBiblico(repositorioMock, daoMock);
    }

    @Test
    void eliminarEstudioBiblicoExitoso() {
        Long idEstudio = 1L;
        DtoEstudioBiblico dtoExistente = new DtoEstudioBiblico();

        when(daoMock.consultarPorId(idEstudio)).thenReturn(dtoExistente);

        servicio.ejecutar(idEstudio);

        verify(daoMock, times(1)).consultarPorId(idEstudio);
        verify(repositorioMock, times(1)).eliminar(idEstudio);
    }

    @Test
    void fallarAlEliminarEstudioBiblicoNoExistente() {
        Long idEstudio = 1L;

        when(daoMock.consultarPorId(idEstudio)).thenReturn(null);

        ExcepcionSinDatos excepcion = assertThrows(ExcepcionSinDatos.class, () -> servicio.ejecutar(idEstudio));

        assertEquals("El estudio bíblico que intenta eliminar no existe.", excepcion.getMessage());
        verify(repositorioMock, never()).eliminar(anyLong());
    }
}