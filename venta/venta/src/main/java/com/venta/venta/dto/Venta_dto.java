package com.venta.venta.dto;

import java.time.LocalDate;
import lombok.*;

import org.springframework.hateoas.RepresentationModel;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false) //Para que no salga la advertencia
public class Venta_dto extends RepresentationModel<Venta_dto> {

    private Integer id_venta;
    private Integer id_cliente;
    private Integer id_vendedor;
    private LocalDate fecha_venta;

}
