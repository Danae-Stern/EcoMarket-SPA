package com.soporte.soporte_api_spring_boot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "soporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Soporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    private Integer id;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "tipo_ticket")
    private String tipoTicket;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private String estado;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Column(name = "fecha_resolucion")
    private Date fechaResolucion;


}
