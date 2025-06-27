package com.soporte.soporte_api_spring_boot.service;

import com.soporte.soporte_api_spring_boot.dto.SoporteDTO;
import com.soporte.soporte_api_spring_boot.model.Soporte;
import com.soporte.soporte_api_spring_boot.repository.SoporteRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SoporteService {

    @Autowired
    private SoporteRepository repository;

    public SoporteDTO guardar(SoporteDTO dto) {
        Soporte soporte = toEntity(dto);
        Soporte saved = repository.save(soporte);
        return toDTO(saved);
    }

    public List<SoporteDTO> listar() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<SoporteDTO> obtenerPorId(Integer id) {
        return repository.findById(id)
                .map(this::toDTO);
    }

    public Optional<SoporteDTO> actualizar(Integer id, SoporteDTO dto) {
        return repository.findById(id).map(soporte -> {
            soporte.setIdUsuario(dto.getIdUsuario());
            soporte.setTipoTicket(dto.getTipoTicket());
            soporte.setDescripcion(dto.getDescripcion());
            soporte.setEstado(dto.getEstado());
            soporte.setFechaCreacion(dto.getFechaCreacion());
            soporte.setFechaResolucion(dto.getFechaResolucion());
            return toDTO(repository.save(soporte));
        });
    }

    public boolean eliminar(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }



    private SoporteDTO toDTO(Soporte soporte) {
        SoporteDTO dto = new SoporteDTO();
        dto.setId(soporte.getId());
        dto.setIdUsuario(soporte.getIdUsuario());
        dto.setTipoTicket(soporte.getTipoTicket());
        dto.setDescripcion(soporte.getDescripcion());
        dto.setEstado(soporte.getEstado());
        dto.setFechaCreacion(soporte.getFechaCreacion());
        dto.setFechaResolucion(soporte.getFechaResolucion());
        return dto;
    }

    private Soporte toEntity(SoporteDTO dto) {
        Soporte soporte = new Soporte();
        soporte.setId(dto.getId());
        soporte.setIdUsuario(dto.getIdUsuario());
        soporte.setTipoTicket(dto.getTipoTicket());
        soporte.setDescripcion(dto.getDescripcion());
        soporte.setEstado(dto.getEstado());
        soporte.setFechaCreacion(dto.getFechaCreacion());
        soporte.setFechaResolucion(dto.getFechaResolucion());
        return soporte;
    }
}
