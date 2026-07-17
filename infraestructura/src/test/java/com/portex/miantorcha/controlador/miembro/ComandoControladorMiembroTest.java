package com.portex.miantorcha.controlador.miembro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portex.compartido.aplicacion.ComandoRespuesta;
import com.portex.ApplicationMock;
import com.portex.miantorcha.infraestructura.controlador.comando.miembro.ComandoControladorMiembro;
import com.portex.miantorcha.infraestructura.controlador.comando.miembro.ComandoMiembro;
import com.portex.miantorcha.infraestructura.controlador.comando.miembro.ManejadorCrearMiembro;
import com.portex.miantorcha.infraestructura.controlador.comando.miembro.ManejadorActualizarMiembro;
import com.portex.miantorcha.infraestructura.controlador.comando.miembro.ManejadorEliminarMiembro;
import com.portex.miantorcha.testdatabuilder.ComandoMiembroTestDataBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorMiembro.class)
@ContextConfiguration(classes = ApplicationMock.class)
@ActiveProfiles("test")
public class ComandoControladorMiembroTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Mockeamos los manejadores para no tocar la BD real
    @MockBean
    private ManejadorCrearMiembro manejadorCrearMiembro;
    @MockBean
    private ManejadorActualizarMiembro manejadorActualizarMiembro;
    @MockBean
    private ManejadorEliminarMiembro manejadorEliminarMiembro;

    @Test
    public void crearMiembroEndpointTest() throws Exception {
        // Arrange
        ComandoMiembro comando = new ComandoMiembroTestDataBuilder().build();
        ComandoRespuesta<Long> respuestaEsperada = new ComandoRespuesta<>(5L);

        Mockito.when(manejadorCrearMiembro.ejecutar(Mockito.any(ComandoMiembro.class))).thenReturn(respuestaEsperada);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/miembros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comando)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valor").value(5L)); // Verifica que devuelve el ID 5
    }
}