package com.metodo_pago.metodo_pago_api_spring_boot.service;

import com.metodo_pago.metodo_pago_api_spring_boot.dto.MetodoPagoDTO;
import com.metodo_pago.metodo_pago_api_spring_boot.model.MetodoPago;
import com.metodo_pago.metodo_pago_api_spring_boot.repository.MetodoPagoRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository repository;

    public MetodoPagoDTO guardar(MetodoPagoDTO dto) {
        MetodoPago metodoPago = toEntity(dto);
        MetodoPago saved = repository.save(metodoPago);
        return toDTO(saved);
    }

    public List<MetodoPagoDTO> listar() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<MetodoPagoDTO> obtenerPorId(Integer id) {
        return repository.findById(id)
                .map(this::toDTO);
    }

    public Optional<MetodoPagoDTO> actualizar(Integer id, MetodoPagoDTO dto) {
        return repository.findById(id).map(metodoPago -> {
            metodoPago.setNomMetodo(dto.getNomMetodo());
            metodoPago.setDescripcionMetodo(dto.getDescripcionMetodo());
            metodoPago.setEstadoMetodo(dto.getEstadoMetodo());
            return toDTO(repository.save(metodoPago));
        });
    }

    public boolean eliminar(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }


    private MetodoPagoDTO toDTO(MetodoPago metodoPago) {
        MetodoPagoDTO dto = new MetodoPagoDTO();
        dto.setId(metodoPago.getId());
        dto.setNomMetodo(metodoPago.getNomMetodo());
        dto.setDescripcionMetodo(metodoPago.getDescripcionMetodo());
        dto.setEstadoMetodo(metodoPago.getEstadoMetodo());
        return dto;
    }

    private MetodoPago toEntity(MetodoPagoDTO dto) {
        MetodoPago metodoPago = new MetodoPago();
        metodoPago.setId(dto.getId());
        metodoPago.setNomMetodo(dto.getNomMetodo());
        metodoPago.setDescripcionMetodo(dto.getDescripcionMetodo());
        metodoPago.setEstadoMetodo(dto.getEstadoMetodo());
        return metodoPago;
    }
}
