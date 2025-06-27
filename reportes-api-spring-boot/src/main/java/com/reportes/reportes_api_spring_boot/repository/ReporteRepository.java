package com.reportes.reportes_api_spring_boot.repository;

import com.reportes.reportes_api_spring_boot.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte,Integer> {
}
