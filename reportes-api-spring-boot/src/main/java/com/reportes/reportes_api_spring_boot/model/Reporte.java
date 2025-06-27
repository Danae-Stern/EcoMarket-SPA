package com.reportes.reportes_api_spring_boot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "reportes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte")
    private Integer id;

    @Column(name = "tipo_reporte")
    private String tipoReporte;

    @Column(name = "fecha_generacion")
    private Date fechaGeneracion;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "json_datos")
    private String jsonDatos;

}
