package com.detalleventa.detalleventa.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalleventa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleV_model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_detalle;
    private Integer id_venta;
    private Integer id_producto;
    private Integer cantidad;
    private Double precio_unitario;

}
