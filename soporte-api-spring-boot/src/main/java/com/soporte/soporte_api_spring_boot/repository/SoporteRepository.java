package com.soporte.soporte_api_spring_boot.repository;

import com.soporte.soporte_api_spring_boot.model.Soporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoporteRepository extends JpaRepository<Soporte, Integer> {
}
