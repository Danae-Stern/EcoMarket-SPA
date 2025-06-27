package com.promocion.promocion_api_spring_boot.service;


import com.promocion.promocion_api_spring_boot.dto.PromocionDTO;
import com.promocion.promocion_api_spring_boot.model.Promocion;
import com.promocion.promocion_api_spring_boot.repository.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PromocionService {

    @Autowired
    private PromocionRepository repository;


    public PromocionDTO guardar(PromocionDTO dto) {
        Promocion promocion = toEntity(dto);
        Promocion saved = repository.save(promocion);
        return toDTO(saved);
    }

    public List<PromocionDTO> listar() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<PromocionDTO> obtenerPorId(Integer id) {
        return repository.findById(id)
                .map(this::toDTO);
    }

    public Optional<PromocionDTO> actualizar(Integer id, PromocionDTO dto) {
        return repository.findById(id).map(promocion -> {
            promocion.setNomPromocion(dto.getNomPromocion());
            promocion.setDescripcion(dto.getDescripcion());
            promocion.setDescuentoPromocion(dto.getDescuentoPromocion());
            promocion.setFechaInicioPromocion(dto.getFechaInicioPromocion());
            promocion.setFechaFinPromocion(dto.getFechaFinPromocion());
            promocion.setActivaPromocion(dto.getActivaPromocion());
            promocion.setTipoAplicacion(dto.getTipoAplicacion());
            promocion.setIdVendedor(dto.getIdVendedor());
            return toDTO(repository.save(promocion));
        });
    }

    public boolean eliminar(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }


    private PromocionDTO toDTO(Promocion promocion) {
        PromocionDTO dto = new PromocionDTO();
        dto.setId(promocion.getId());
        dto.setNomPromocion(promocion.getNomPromocion());
        dto.setDescripcion(promocion.getDescripcion());
        dto.setDescuentoPromocion(promocion.getDescuentoPromocion());
        dto.setFechaInicioPromocion(promocion.getFechaInicioPromocion());
        dto.setFechaFinPromocion(promocion.getFechaFinPromocion());
        dto.setActivaPromocion(promocion.getActivaPromocion());
        dto.setTipoAplicacion(promocion.getTipoAplicacion());
        dto.setIdVendedor(promocion.getIdVendedor());
        return dto;
    }

    private Promocion toEntity(PromocionDTO dto) {
        Promocion promocion = new Promocion();
        promocion.setId(dto.getId());
        promocion.setNomPromocion(dto.getNomPromocion());
        promocion.setDescripcion(dto.getDescripcion());
        promocion.setDescuentoPromocion(dto.getDescuentoPromocion());
        promocion.setFechaInicioPromocion(dto.getFechaInicioPromocion());
        promocion.setFechaFinPromocion(dto.getFechaFinPromocion());
        promocion.setActivaPromocion(dto.getActivaPromocion());
        promocion.setTipoAplicacion(dto.getTipoAplicacion());
        promocion.setIdVendedor(dto.getIdVendedor());
        return promocion;
    }
}
