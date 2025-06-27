package com.reportes.reportes_api_spring_boot.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReporteDTO extends RepresentationModel {
    private Integer id;
    private String tipoReporte;
    private Date fechaGeneracion;
    private String descripcion;
    private String jsonDatos;

}
