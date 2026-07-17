package com.portex.miantorcha.controlador.estudio;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.portex.ApplicationMock;
import com.portex.compartido.aplicacion.ComandoRespuesta;
import com.portex.compartido.dominio.excepcion.ExcepcionValorObligatorio;
import com.portex.compartido.infraestructura.seguridad.jwt.JwtTokenManager;
import com.portex.miantorcha.infraestructura.controlador.comando.estudio.ComandoControladorEstudioBiblico;
import com.portex.miantorcha.infraestructura.controlador.comando.estudio.ComandoEstudioBiblico;
import com.portex.miantorcha.infraestructura.controlador.comando.estudio.ComandoHistoricoLeccion;
import com.portex.miantorcha.infraestructura.controlador.comando.estudio.ManejadorActualizarEstudioBiblico;
import com.portex.miantorcha.infraestructura.controlador.comando.estudio.ManejadorRegistrarLeccion;
import com.portex.miantorcha.testdatabuilder.ComandoEstudioBiblicoTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorEstudioBiblico.class)
@ContextConfiguration(classes= ApplicationMock.class)
@ActiveProfiles("test")
public class ComandoControladorEstudioBiblicoTest {

    @Autowired
    private MockMvc mockMvc;

    // Registramos el módulo de JavaTime para que Jackson pueda serializar LocalDateTime sin errores
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    private String tokenPrueba;

    @MockBean
    private ManejadorActualizarEstudioBiblico manejadorActualizarEstudioBiblico;

    @MockBean
    private ManejadorRegistrarLeccion manejadorRegistrarLeccion;

    @BeforeEach
    public void setUp() {
        JwtTokenManager jwtTokenManager = new JwtTokenManager();
        this.tokenPrueba = jwtTokenManager.crear("admin");
    }

    @Test
    void crearEstudioBiblicoExitoso() throws Exception {
        // Arrange
        ComandoEstudioBiblico comando = new ComandoEstudioBiblicoTestDataBuilder().build();

        // Act & Assert
        mockMvc.perform(post("/api/estudios-biblicos")
                        .header("Authorization", "Bearer " + this.tokenPrueba)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comando)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valor").value(comando.getId()));
    }

    @Test
    void fallarAlCrearEstudioBiblicoSinDireccion() {
        // Arrange
        ComandoEstudioBiblico comando = new ComandoEstudioBiblicoTestDataBuilder()
                .conDireccionPersona(null)
                .build();

        // Act & Assert
        Exception excepcion = Assertions.assertThrows(Exception.class, () -> {
            mockMvc.perform(post("/api/estudios-biblicos")
                    .header("Authorization", "Bearer " + this.tokenPrueba)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(comando)));
        });

        Assertions.assertTrue(excepcion.getCause() instanceof ExcepcionValorObligatorio);
        Assertions.assertEquals("La dirección de la persona es obligatoria", excepcion.getCause().getMessage());
    }

    @Test
    void actualizarEstudioBiblicoExitoso() throws Exception {
        // Arrange
        Long idEstudio = 1L;
        ComandoEstudioBiblico comando = new ComandoEstudioBiblico();
        comando.setNombrePersona("Juan Pérez Modificado");
        comando.setDireccionPersona("Avenida Siempreviva 742");
        comando.setEstado("en curso");
        comando.setIdGrupo(1L);

        // Act & Assert
        mockMvc.perform(put("/api/estudios-biblicos/" + idEstudio)
                        .header("Authorization", "Bearer " + this.tokenPrueba)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comando)))
                .andExpect(status().isOk());

        verify(manejadorActualizarEstudioBiblico, times(1)).ejecutar(any(ComandoEstudioBiblico.class), eq(idEstudio));
    }

    @Test
    void registrarLeccionExitoso() throws Exception {
        // Arrange
        Long idEstudioAfectado = 15L;
        ComandoHistoricoLeccion comando = new ComandoHistoricoLeccion();
        comando.setContadorSemana(1);
        comando.setIdEstudioBiblico(idEstudioAfectado);
        comando.setFechaEstudio(LocalDateTime.now());
        comando.setIdActividad(99L);

        // Simulamos que el manejador responde con el ID del estudio afectado
        when(manejadorRegistrarLeccion.ejecutar(any(ComandoHistoricoLeccion.class)))
                .thenReturn(new ComandoRespuesta<>(idEstudioAfectado));

        // Act & Assert
        mockMvc.perform(post("/api/estudios-biblicos/lecciones")
                        .header("Authorization", "Bearer " + this.tokenPrueba)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comando)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valor").value(idEstudioAfectado));

        verify(manejadorRegistrarLeccion, times(1)).ejecutar(any(ComandoHistoricoLeccion.class));
    }
}