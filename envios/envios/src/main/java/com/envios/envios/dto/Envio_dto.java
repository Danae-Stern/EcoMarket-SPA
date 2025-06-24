package com.envios.envios.dto;

import java.time.LocalDate;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false) 
public class Envio_dto extends RepresentationModel<Envio_dto>{
    private Integer id_envio;
    private Integer id_venta;
    private String direccion_envio;
    private String estado_envio;
    private LocalDate fecha_envio;
    private LocalDate fecha_entrega;
}