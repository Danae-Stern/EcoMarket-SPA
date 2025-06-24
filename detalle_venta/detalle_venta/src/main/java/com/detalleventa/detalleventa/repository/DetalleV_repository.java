package com.detalleventa.detalleventa.repository;

import com.detalleventa.detalleventa.models.DetalleV_model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleV_repository extends JpaRepository<DetalleV_model, Integer> {
}