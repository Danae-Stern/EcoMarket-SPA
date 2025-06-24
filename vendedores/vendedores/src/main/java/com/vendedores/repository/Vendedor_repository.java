package com.vendedores.repository;

import com.vendedores.models.Vendedor_model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Vendedor_repository extends JpaRepository<Vendedor_model, Integer> {

}
