package com.metodo_pago.metodo_pago_api_spring_boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetodoPagoDTO extends RepresentationModel<MetodoPagoDTO> {
    private Integer id;
    private String nomMetodo;
    private String descripcionMetodo;
    private String estadoMetodo;
}
