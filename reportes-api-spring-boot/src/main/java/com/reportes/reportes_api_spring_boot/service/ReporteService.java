package com.reportes.reportes_api_spring_boot.service;

import com.reportes.reportes_api_spring_boot.dto.ReporteDTO;
import com.reportes.reportes_api_spring_boot.model.Reporte;
import com.reportes.reportes_api_spring_boot.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReporteService {
    @Autowired
    private ReporteRepository repository;

    public ReporteDTO guardar (ReporteDTO dto) {
        Reporte reporte = toEntity(dto);
        Reporte saved = repository.save(reporte);
        return toDTO(saved);

    }

    public List<ReporteDTO> listar () {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ReporteDTO> obtenerPorId(Integer id) {
        return repository.findById(id)
                .map(this::toDTO);
    }

    public Optional<ReporteDTO> actualizar (Integer id, ReporteDTO dto) {
        return repository.findById(id).map(reporte -> {
            reporte.setTipoReporte(dto.getTipoReporte());
            reporte.setFechaGeneracion(dto.getFechaGeneracion());
            reporte.setDescripcion(dto.getDescripcion());
            reporte.setJsonDatos(dto.getJsonDatos());
            return toDTO(repository.save(reporte));
        });
    }
    public boolean eliminar(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }


    private ReporteDTO toDTO(Reporte reporte) {
        ReporteDTO dto = new ReporteDTO();
        dto.setId(reporte.getId());
        dto.setTipoReporte(reporte.getTipoReporte());
        dto.setFechaGeneracion(reporte.getFechaGeneracion());
        dto.setDescripcion(reporte.getDescripcion());
        dto.setJsonDatos(reporte.getJsonDatos());
        return dto;
    }

    private Reporte toEntity(ReporteDTO dto) {
        Reporte reporte = new Reporte();
        reporte.setId(dto.getId());
        reporte.setTipoReporte(dto.getTipoReporte());
        reporte.setFechaGeneracion(dto.getFechaGeneracion());
        reporte.setDescripcion(dto.getDescripcion());
        reporte.setJsonDatos(dto.getJsonDatos());
        return reporte;
    }
}
