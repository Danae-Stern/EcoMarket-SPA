package com.inventario.inventario_api_spring_boot.service;

import com.inventario.inventario_api_spring_boot.dto.InventarioDTO;
import com.inventario.inventario_api_spring_boot.model.Inventario;
import com.inventario.inventario_api_spring_boot.repository.InventarioRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventarioService {

    @Autowired
    private InventarioRespository repository;

    public InventarioDTO guardar(InventarioDTO dto){
        Inventario inventario = toEntity(dto);
        Inventario saved = repository.save(inventario);
        return toDTO(saved);
    }

    public List<InventarioDTO> listar(){
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<InventarioDTO> obtenerPorId(Integer id){
        return repository.findById(id)
                .map(this::toDTO);

    }
    public Optional<InventarioDTO> actualizar(Integer id, InventarioDTO dto) {
        return repository.findById(id).map(inventario -> {
            inventario.setIdProducto(dto.getIdProducto());
            inventario.setStockDisponible(dto.getStockDisponible());
            inventario.setUbicacionBodega(dto.getUbicacionBodega());
            return toDTO(repository.save(inventario));
        });
    }
    public boolean eliminar(Integer id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    private InventarioDTO toDTO(Inventario inventario) {
        InventarioDTO dto = new InventarioDTO();
        dto.setId(inventario.getId());
        dto.setIdProducto(inventario.getIdProducto());
        dto.setStockDisponible(inventario.getStockDisponible());
        dto.setUbicacionBodega(inventario.getUbicacionBodega());
        return dto;
    }

    private Inventario toEntity(InventarioDTO dto) {
        Inventario inventario = new Inventario();
        inventario.setId(dto.getId());
        inventario.setIdProducto(dto.getIdProducto());
        inventario.setStockDisponible(dto.getStockDisponible());
        inventario.setUbicacionBodega(dto.getUbicacionBodega());
        return inventario;
    }



}
