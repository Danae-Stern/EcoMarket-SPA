package com.metodo_pago.metodo_pago_api_spring_boot.repository;

import com.metodo_pago.metodo_pago_api_spring_boot.model.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago,Integer> {
}
