package com.vendedores.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vendedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vendedor_model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_vendedor;
    private Integer id_usuario;
    private String nombre_completo;
    private String rut;
    private String area_ventas;

}
