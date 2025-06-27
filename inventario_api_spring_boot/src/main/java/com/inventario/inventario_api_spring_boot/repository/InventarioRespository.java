package com.inventario.inventario_api_spring_boot.repository;

import com.inventario.inventario_api_spring_boot.model.Inventario;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRespository extends JpaRepository<Inventario, Integer> {

}
