package com.detalleventa.detalleventa.services;

import com.detalleventa.detalleventa.models.DetalleV_model;
import com.detalleventa.detalleventa.dto.DetalleV_dto;
import com.detalleventa.detalleventa.repository.DetalleV_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleV_services {

    @Autowired
    private DetalleV_repository repository;

    private DetalleV_dto toDTO(DetalleV_model model) {
        return new DetalleV_dto(
                model.getId_detalle(),
                model.getId_venta(),
                model.getId_producto(),
                model.getCantidad(),
                model.getPrecio_unitario()
        );
    }

    private DetalleV_model toEntity(DetalleV_dto dto) {
        DetalleV_model model = new DetalleV_model();
        model.setId_detalle(dto.getId_detalle());
        model.setId_venta(dto.getId_venta());
        model.setId_producto(dto.getId_producto());
        model.setCantidad(dto.getCantidad());
        model.setPrecio_unitario(dto.getPrecio_unitario());
        return model;
    }

    public DetalleV_dto crear(DetalleV_dto dto) {
        DetalleV_model model = toEntity(dto);
        return toDTO(repository.save(model));
    }

    public List<DetalleV_dto> listar() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public DetalleV_dto obtenerPorId(Integer id) {
        DetalleV_model model = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de venta no encontrado"));
        return toDTO(model);
    }

    public DetalleV_dto actualizar(Integer id, DetalleV_dto dto) {
        DetalleV_model existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de venta no encontrado"));

        existente.setId_venta(dto.getId_venta());
        existente.setId_producto(dto.getId_producto());
        existente.setCantidad(dto.getCantidad());
        existente.setPrecio_unitario(dto.getPrecio_unitario());

        return toDTO(repository.save(existente));
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
