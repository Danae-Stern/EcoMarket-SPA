package com.promocion.promocion_api_spring_boot.repository;

import com.promocion.promocion_api_spring_boot.model.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocionRepository extends JpaRepository<Promocion, Integer> {
}
