package com.envios.envios.services;

import com.envios.envios.dto.Envio_dto;
import com.envios.envios.models.Envio_model;
import com.envios.envios.repository.Envio_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Envio_service {

    @Autowired
    private Envio_repository envioRepository;

    private Envio_dto toDTO(Envio_model envio) {
        return new Envio_dto(
                envio.getId_envio(),
                envio.getId_venta(),
                envio.getDireccion_envio(),
                envio.getEstado_envio(),
                envio.getFecha_envio(),
                envio.getFecha_entrega()
        );
    }

    private Envio_model toEntity(Envio_dto dto) {
        Envio_model envio = new Envio_model();
        envio.setId_envio(dto.getId_envio());
        envio.setId_venta(dto.getId_venta());
        envio.setDireccion_envio(dto.getDireccion_envio());
        envio.setEstado_envio(dto.getEstado_envio());
        envio.setFecha_envio(dto.getFecha_envio());
        envio.setFecha_entrega(dto.getFecha_entrega());
        return envio;
    }

    public Envio_dto crear(Envio_dto dto) {
        Envio_model envio = toEntity(dto);
        return toDTO(envioRepository.save(envio));
    }

    public List<Envio_dto> listar() {
        return envioRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Envio_dto obtenerPorId(Integer id) {
        Envio_model envio = envioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Envío no encontrado"));
        return toDTO(envio);
    }

    public Envio_dto actualizar(Integer id, Envio_dto dto) {
        Envio_model existente = envioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Envío no encontrado"));

        existente.setId_venta(dto.getId_venta());
        existente.setDireccion_envio(dto.getDireccion_envio());
        existente.setEstado_envio(dto.getEstado_envio());
        existente.setFecha_envio(dto.getFecha_envio());
        existente.setFecha_entrega(dto.getFecha_entrega());

        return toDTO(envioRepository.save(existente));
    }

    public void eliminar(Integer id) {
        envioRepository.deleteById(id);
    }
}