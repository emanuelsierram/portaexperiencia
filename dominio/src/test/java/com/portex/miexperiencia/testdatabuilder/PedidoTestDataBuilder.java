package com.portex.miexperiencia.testdatabuilder;

import com.portex.miexperiencia.dominio.modelo.entidad.Pedido;

import java.time.LocalDateTime;

public class PedidoTestDataBuilder {

    private Long id;
    private String nombre;
    private String descripcion;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFinal;
    private Double presupuesto;
    private Long idCliente;
    private Long idTrabajador;
    private Long idValoracion;


    public PedidoTestDataBuilder(){
        nombre="Pedido de prueba";
        descripcion="Descripción de prueba";
        estado="solicitado";
        fechaCreacion=LocalDateTime.now();
        fechaInicio= LocalDateTime.of(2026, 8, 12, 8, 15);
        fechaFinal= LocalDateTime.of(2026, 11, 11, 8, 15);
        presupuesto=100.0;
        idCliente=1L;
        idTrabajador=1L;
        idValoracion=1L;
    }

    public PedidoTestDataBuilder conFechas(LocalDateTime fechaInicio, LocalDateTime fechaFinal){
        this.fechaInicio = fechaInicio;
        this.fechaFinal=fechaFinal;
        return this;
    }

    public PedidoTestDataBuilder conFechaInicio(LocalDateTime fechaInicio){
        this.fechaInicio = fechaInicio;
        return this;
    }

    public Pedido build(){return new Pedido(id,nombre,descripcion,fechaInicio,fechaFinal,presupuesto,idCliente,idTrabajador,idValoracion);}

}
