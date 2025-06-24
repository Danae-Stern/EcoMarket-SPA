package com.venta.venta.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Venta_model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_venta;
    private Integer id_cliente;
    private Integer id_vendedor;
    private LocalDate fecha_venta;
}
