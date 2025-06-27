package com.metodo_pago.metodo_pago_api_spring_boot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "metodo_pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetodoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_metodo")
    private Integer id;

    @Column(name = "nom_metodo")
    private String nomMetodo;

    @Column(name = "descripcion_metodo")
    private String descripcionMetodo;

    @Column(name = "estado_metodo")
    private String estadoMetodo;

}
