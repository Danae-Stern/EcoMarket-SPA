package com.venta.venta.services;

import com.venta.venta.dto.Venta_dto;
import com.venta.venta.models.Venta_model;
import com.venta.venta.repository.Venta_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Venta_service {

    @Autowired
    private Venta_repository ventaRepository;

    private Venta_dto toDTO(Venta_model venta) {
        return new Venta_dto(
                venta.getId_venta(),
                venta.getId_cliente(),
                venta.getId_vendedor(),
                venta.getFecha_venta()
        );
    }

    private Venta_model toEntity(Venta_dto dto) {
        Venta_model venta = new Venta_model();
        venta.setId_venta(dto.getId_venta());
        venta.setId_cliente(dto.getId_cliente());
        venta.setId_vendedor(dto.getId_vendedor());
        venta.setFecha_venta(dto.getFecha_venta());
        return venta;
    }

    public Venta_dto crear(Venta_dto dto) {
        Venta_model venta = toEntity(dto);
        return toDTO(ventaRepository.save(venta));
    }

    public List<Venta_dto> listar() {
        return ventaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Venta_dto obtenerPorId(Integer id) {
        Venta_model venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        return toDTO(venta);
    }

    public Venta_dto actualizar(Integer id, Venta_dto dto) {
        Venta_model existente = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        existente.setId_cliente(dto.getId_cliente());
        existente.setId_vendedor(dto.getId_vendedor());
        existente.setFecha_venta(dto.getFecha_venta());
        return toDTO(ventaRepository.save(existente));
    }

    public void eliminar(Integer id) {
        ventaRepository.deleteById(id);
    }
}