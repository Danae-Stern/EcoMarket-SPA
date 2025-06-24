package com.envios.envios.repository;

import com.envios.envios.models.Envio_model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Envio_repository extends JpaRepository<Envio_model, Integer> {
    
}