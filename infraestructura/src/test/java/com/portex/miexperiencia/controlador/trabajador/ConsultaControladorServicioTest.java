package com.portex.miexperiencia.controlador.trabajador;

import com.portex.ApplicationMock;
import com.portex.compartido.infraestructura.seguridad.jwt.JwtTokenManager;
import com.portex.miexperiencia.infraestructura.controlador.consulta.trabajador.ConsultaControladorTrabajador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorTrabajador.class)
@ContextConfiguration(classes= ApplicationMock.class)
@ActiveProfiles("test")
public class ConsultaControladorServicioTest {

    private String tokenPrueba;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        JwtTokenManager jwtTokenManager = new JwtTokenManager();
        this.tokenPrueba = jwtTokenManager.crear("admin");
    }

    @Test
    public void listarServiciosTest() throws Exception {

        mockMvc.perform(get("/servicios")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenPrueba)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombre", is("Mantenimiento de PC")));
    }

    @Test
    public void consultarServiciosPorTrabajador() throws Exception {
        mockMvc.perform(get("/servicios/trabajador/{id}",1L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenPrueba)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombre", is("Mantenimiento de PC")));
    }

    @Test
    public void consultarServiciosPorId() throws Exception {
        mockMvc.perform(get("/servicios/{id}",1L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenPrueba)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));

    }

}










