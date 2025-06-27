package com.inventario.inventario_api_spring_boot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "inventario")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventario", nullable = false)
    private Integer id;

    private Integer idProducto;

    @Column(name = "stock_disponible")
    private Integer stockDisponible;

    @Size(max = 100)
    @Column(name = "ubicacion_bodega", length = 100)
    private String ubicacionBodega;

}