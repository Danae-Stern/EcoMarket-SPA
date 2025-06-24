package com.venta.venta.repository;

import com.venta.venta.models.Venta_model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Venta_repository extends JpaRepository<Venta_model, Integer> {

}