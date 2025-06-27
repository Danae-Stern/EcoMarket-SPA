package com.promocion.promocion_api_spring_boot.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromocionDTO extends RepresentationModel<PromocionDTO> {
    private Integer id;
    private String nomPromocion;
    private String descripcion;
    private Float descuentoPromocion;
    private Date fechaInicioPromocion;
    private Date fechaFinPromocion;
    private String activaPromocion;
    private String tipoAplicacion;
    private Integer idVendedor;
}
