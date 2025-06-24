package com.vendedores.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false) 
public class Vendedor_dto extends RepresentationModel<Vendedor_dto>{
    private Integer id_vendedor;
    private Integer id_usuario;
    private String nombre_completo;
    private String rut;
    private String areas_ventas;

}
