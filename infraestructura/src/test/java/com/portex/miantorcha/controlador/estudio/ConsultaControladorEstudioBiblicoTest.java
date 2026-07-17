package com.portex.miantorcha.controlador.estudio;

import com.portex.ApplicationMock;
import com.portex.compartido.infraestructura.seguridad.jwt.JwtTokenManager;
import com.portex.miantorcha.dominio.modelo.dto.DtoEstudioBiblico;
import com.portex.miantorcha.dominio.modelo.dto.DtoHistoricoLeccion;
import com.portex.miantorcha.infraestructura.controlador.consulta.estudio.ConsultaControladorEstudioBiblico;
import com.portex.miantorcha.infraestructura.controlador.consulta.estudio.ManejadorListarEstudioBiblico;
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
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorEstudioBiblico.class)
@ContextConfiguration(classes = ApplicationMock.class)
@ActiveProfiles("test")
public class ConsultaControladorEstudioBiblicoTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ManejadorListarEstudioBiblico manejadorListarEstudioBiblico;

    private String tokenPrueba;

    @BeforeEach
    public void setUp() {
        JwtTokenManager jwtTokenManager = new JwtTokenManager();
        this.tokenPrueba = jwtTokenManager.crear("admin");
    }

    @Test
    void consultarActualesPorMiembroExitoso() throws Exception {
        // Arrange
        Long idUsuarioAsignado = 123L;

        DtoEstudioBiblico dto1 = new DtoEstudioBiblico();
        dto1.setId(1L);
        dto1.setNombrePersona("Maria Test");

        DtoEstudioBiblico dto2 = new DtoEstudioBiblico();
        dto2.setId(2L);
        dto2.setNombrePersona("Jose Test");

        List<DtoEstudioBiblico> respuestaSimulada = Arrays.asList(dto1, dto2);

        when(manejadorListarEstudioBiblico.consultarActualesPorMiembro(idUsuarioAsignado))
                .thenReturn(respuestaSimulada);

        // Act & Assert
        mockMvc.perform(get("/api/estudios-biblicos/actuales/" + idUsuarioAsignado)
                        .header("Authorization", "Bearer " + this.tokenPrueba)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombrePersona").value("Maria Test"))
                .andExpect(jsonPath("$[1].nombrePersona").value("Jose Test"));
    }

    @Test
    void consultarDisponiblesPorDarExitoso() throws Exception {
        // Arrange
        DtoEstudioBiblico dto = new DtoEstudioBiblico();
        dto.setId(10L);
        dto.setNombrePersona("Disponible 1");

        List<DtoEstudioBiblico> respuestaSimulada = Arrays.asList(dto);

        when(manejadorListarEstudioBiblico.consultarDisponiblesPorDar())
                .thenReturn(respuestaSimulada);

        // Act & Assert
        mockMvc.perform(get("/api/estudios-biblicos/disponibles")
                        .header("Authorization", "Bearer " + this.tokenPrueba)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(10));
    }

    @Test
    void consultarHistoricoLeccionesExitoso() throws Exception {
        // Arrange
        long idEstudio = 15L;

        DtoHistoricoLeccion leccion = new DtoHistoricoLeccion(
                100L,
                1,
                idEstudio,
                LocalDateTime.now(),
                99L
        );

        List<DtoHistoricoLeccion> respuestaSimulada = List.of(leccion);

        when(manejadorListarEstudioBiblico.consultarHistoricoLecciones(idEstudio))
                .thenReturn(respuestaSimulada);

        // Act & Assert
        mockMvc.perform(get("/api/estudios-biblicos/" + idEstudio + "/lecciones")
                        .header("Authorization", "Bearer " + this.tokenPrueba)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(100))
                .andExpect(jsonPath("$[0].contadorSemana").value(1))
                .andExpect(jsonPath("$[0].idEstudioBiblico").value(15))
                .andExpect(jsonPath("$[0].idActividad").value(99));
    }
}