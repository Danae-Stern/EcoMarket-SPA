package com.soporte.soporte_api_spring_boot.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoporteDTO extends RepresentationModel<SoporteDTO> {
    private Integer id;
    private Integer idUsuario;
    private String tipoTicket;
    private String descripcion;
    private String estado;
    private Date fechaCreacion;
    private Date fechaResolucion;
}
