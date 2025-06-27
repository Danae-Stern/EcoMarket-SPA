package com.inventario.inventario_api_spring_boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioDTO extends RepresentationModel<InventarioDTO> {
    private Integer id;
    private Integer idProducto;
    private Integer stockDisponible;
    private String ubicacionBodega;

}
