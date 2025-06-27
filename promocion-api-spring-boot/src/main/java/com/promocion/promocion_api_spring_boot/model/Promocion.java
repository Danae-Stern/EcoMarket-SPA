package com.promocion.promocion_api_spring_boot.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "promocion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promocion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_promocion")
    private Integer id;

    @Column(name = "nom_promocion")
    private String nomPromocion;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "descuento_promocion")
    private Float descuentoPromocion;

    @Column(name = "fecha_inicio_promocion")
    private Date fechaInicioPromocion;

    @Column(name = "fecha_fin_promocion")
    private Date fechaFinPromocion;

    @Column(name = "activa_promocion")
    private String activaPromocion;

    @Column(name = "tipo_aplicacion")
    private String tipoAplicacion;

    @Column(name = "id_vendedor")
    private Integer idVendedor;


}
