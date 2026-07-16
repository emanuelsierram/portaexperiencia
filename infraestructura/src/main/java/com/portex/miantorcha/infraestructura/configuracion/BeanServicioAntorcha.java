package com.portex.miantorcha.infraestructura.configuracion;

import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioEstudioBiblico;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioMiembro;
import com.portex.miantorcha.dominio.servicio.estudio.ServicioCrearEstudioBiblico;
import com.portex.miantorcha.dominio.servicio.miembro.ServicioActualizarMiembro;
import com.portex.miantorcha.dominio.servicio.miembro.ServicioCrearMiembro;
import com.portex.miantorcha.dominio.servicio.miembro.ServicioEliminarMiembro;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioAntorcha {

    @Bean
    public ServicioCrearMiembro servicioCrearMiembro(RepositorioMiembro repositorioMiembro) {
        return new ServicioCrearMiembro(repositorioMiembro);
    }
    @Bean
    public ServicioActualizarMiembro servicioActualizarMiembro(RepositorioMiembro repositorioMiembro) {
        return new ServicioActualizarMiembro(repositorioMiembro);
    }
    @Bean
    public ServicioEliminarMiembro servicioEliminarMiembro(RepositorioMiembro repositorioMiembro) {
        return new ServicioEliminarMiembro(repositorioMiembro);
    }

    @Bean
    public ServicioCrearEstudioBiblico servicioCrearEstudioBiblico(RepositorioEstudioBiblico repositorioEstudioBiblico) {
        return new ServicioCrearEstudioBiblico(repositorioEstudioBiblico);
    }
}
