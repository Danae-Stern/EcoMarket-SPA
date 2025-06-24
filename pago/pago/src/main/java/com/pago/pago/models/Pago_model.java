package com.pago.pago.models;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago_model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pago;
    private LocalDate fecha_pago;
    private Integer monto;
    private String referencia_pago;
    private String estado_pago;
    private Integer id_metodo;
    private Integer id_venta;

}
