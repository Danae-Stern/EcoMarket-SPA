package com.envios.envios.models;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "envios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Envio_model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_envio;
    private Integer id_venta;
    private String direccion_envio;
    private String estado_envio;
    private LocalDate fecha_envio;
    private LocalDate fecha_entrega;
}
