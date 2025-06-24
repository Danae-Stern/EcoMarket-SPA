package com.vendedores.services;

import com.vendedores.dto.Vendedor_dto;
import com.vendedores.models.Vendedor_model;
import com.vendedores.repository.Vendedor_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Vendedor_service {

    @Autowired
    private Vendedor_repository vendedorRepository;

    private Vendedor_dto toDTO(Vendedor_model vendedor) {
        return new Vendedor_dto(
                vendedor.getId_vendedor(),
                vendedor.getId_usuario(),
                vendedor.getNombre_completo(),
                vendedor.getRut(),
                vendedor.getArea_ventas()
        );
    }

    private Vendedor_model toEntity(Vendedor_dto dto) {
        Vendedor_model vendedor = new Vendedor_model();
        vendedor.setId_vendedor(dto.getId_vendedor());
        vendedor.setId_usuario(dto.getId_usuario());
        vendedor.setNombre_completo(dto.getNombre_completo());
        vendedor.setRut(dto.getRut());
        vendedor.setArea_ventas(dto.getAreas_ventas());
        return vendedor;
    }

    public Vendedor_dto crear(Vendedor_dto dto) {
        Vendedor_model vendedor = toEntity(dto);
        return toDTO(vendedorRepository.save(vendedor));
    }

    public List<Vendedor_dto> listar() {
        return vendedorRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Vendedor_dto obtenerPorId(Integer id) {
        Vendedor_model vendedor = vendedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));
        return toDTO(vendedor);
    }

    public Vendedor_dto actualizar(Integer id, Vendedor_dto dto) {
        Vendedor_model existente = vendedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));

        existente.setId_usuario(dto.getId_usuario());
        existente.setNombre_completo(dto.getNombre_completo());
        existente.setRut(dto.getRut());
        existente.setArea_ventas(dto.getAreas_ventas());

        return toDTO(vendedorRepository.save(existente));
    }

    public void eliminar(Integer id) {
        vendedorRepository.deleteById(id);
    }
}
