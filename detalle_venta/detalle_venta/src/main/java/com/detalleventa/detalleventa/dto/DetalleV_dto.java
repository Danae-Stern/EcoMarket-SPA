package com.detalleventa.detalleventa.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false) 
public class DetalleV_dto extends RepresentationModel<DetalleV_dto>{
    private Integer id_detalle;
    private Integer id_venta;
    private Integer id_producto;
    private Integer cantidad;
    private Double precio_unitario;

}
