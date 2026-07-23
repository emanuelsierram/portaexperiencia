package com.portex.miantorcha.infraestructura.controlador.miembro;

import com.portex.compartido.aplicacion.ComandoRespuesta;
import com.portex.compartido.dominio.modelo.dto.DtoUsuario;
import com.portex.compartido.dominio.puerto.repositorio.RepositorioUsuario;
import com.portex.miantorcha.dominio.modelo.entidad.Miembro;
import com.portex.miantorcha.dominio.servicio.miembro.ServicioCrearMiembro;
import com.portex.miantorcha.infraestructura.controlador.comando.miembro.ComandoMiembro;
import com.portex.miantorcha.infraestructura.controlador.comando.miembro.FabricaMiembro;
import com.portex.miantorcha.infraestructura.controlador.comando.miembro.ManejadorCrearMiembro;
import com.portex.miantorcha.testdatabuilder.ComandoMiembroTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ManejadorCrearMiembroTest {

    private ServicioCrearMiembro servicioCrearMiembro;
    private RepositorioUsuario repositorioUsuario;
    private PasswordEncoder passwordEncoder;
    private ManejadorCrearMiembro manejadorCrearMiembro;

    private static final String TELEFONO = "3109876543";
    private static final String PASS_CIFRADA = "pass_cifrada";
    private static final String PASS = "secreta123";

    @BeforeEach
    public void setup() {
        servicioCrearMiembro = Mockito.mock(ServicioCrearMiembro.class);
        repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);

        FabricaMiembro fabricaMiembro = new FabricaMiembro();

        manejadorCrearMiembro = new ManejadorCrearMiembro(
                servicioCrearMiembro,
                passwordEncoder,
                fabricaMiembro,
                repositorioUsuario
                );
    }

    @Test
    public void validarOrquestacionAlCrearMiembro() {
        ComandoMiembro comando = new ComandoMiembroTestDataBuilder().build();
        String telefonoLimpio = TELEFONO;

        Mockito.when(passwordEncoder.encode(comando.getContrasena())).thenReturn(PASS_CIFRADA);
        Mockito.when(servicioCrearMiembro.ejecutar(Mockito.any(Miembro.class))).thenReturn(10L);

        // Act
        ComandoRespuesta<Long> respuesta = manejadorCrearMiembro.ejecutar(comando);

        // Assert
        Mockito.verify(passwordEncoder).encode(PASS);

        ArgumentCaptor<DtoUsuario> captorUsuario = ArgumentCaptor.forClass(DtoUsuario.class);
        Mockito.verify(repositorioUsuario).crear(captorUsuario.capture());
        Assertions.assertEquals(telefonoLimpio, captorUsuario.getValue().getUsuario());
        Assertions.assertEquals(PASS_CIFRADA, captorUsuario.getValue().getContrasena());

        Mockito.verify(repositorioUsuario).asignarRol(telefonoLimpio, "USER");

        Mockito.verify(servicioCrearMiembro).ejecutar(Mockito.any(Miembro.class));
        Assertions.assertEquals(10L, respuesta.getValor());
    }
}
