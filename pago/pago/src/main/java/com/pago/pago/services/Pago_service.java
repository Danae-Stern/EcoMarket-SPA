package com.pago.pago.services;

import com.pago.pago.dto.Pago_dto;
import com.pago.pago.models.Pago_model;
import com.pago.pago.repository.Pago_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Pago_service {

    @Autowired
    private Pago_repository pagoRepository;

    private Pago_dto toDTO(Pago_model pago) {
        return new Pago_dto(
                pago.getId_pago(),
                pago.getFecha_pago(),
                pago.getMonto(),
                pago.getReferencia_pago(),
                pago.getEstado_pago(),
                pago.getId_metodo(),
                pago.getId_venta()
        );
    }

    private Pago_model toEntity(Pago_dto dto) {
    return new Pago_model(
        dto.getId_pago(),
        dto.getFecha_pago(),
        dto.getMonto(),
        dto.getReferencia_pago(),
        dto.getEstado_pago(),
        dto.getId_metodo(),
        dto.getId_venta()
    );
    }

    public Pago_dto crear(Pago_dto dto) {
        return toDTO(pagoRepository.save(toEntity(dto)));
    }

    public List<Pago_dto> listar() {
        return pagoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Pago_dto obtenerPorId(Integer id) {
        Pago_model pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
        return toDTO(pago);
    }

    public Pago_dto actualizar(Integer id, Pago_dto dto) {
        Pago_model existente = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));

        existente.setFecha_pago(dto.getFecha_pago());
        existente.setMonto(dto.getMonto());
        existente.setReferencia_pago(dto.getReferencia_pago());
        existente.setEstado_pago(dto.getEstado_pago());
        existente.setId_metodo(dto.getId_metodo());
        existente.setId_venta(dto.getId_venta());

        return toDTO(pagoRepository.save(existente));
    }

    public void eliminar(Integer id) {
        pagoRepository.deleteById(id);
    }
}
