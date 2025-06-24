package com.pago.pago;

import com.pago.pago.dto.Pago_dto;
import com.pago.pago.models.Pago_model;
import com.pago.pago.repository.Pago_repository;
import com.pago.pago.services.Pago_service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Pago_Services_Test {

    @Mock
    private Pago_repository pagoRepository;

    @InjectMocks
    private Pago_service pagoService;

    @Test
    public void testCrearPago() {

        Pago_dto dto = new Pago_dto(
                null,
                LocalDate.now(),
                10000,
                "REF123",
                "PENDIENTE",
                1,
                2
        );

        Pago_model entidadGuardada = new Pago_model(
                1,
                dto.getFecha_pago(),
                dto.getMonto(),
                dto.getReferencia_pago(),
                dto.getEstado_pago(),
                dto.getId_metodo(),
                dto.getId_venta()
        );

        when(pagoRepository.save(any(Pago_model.class))).thenReturn(entidadGuardada);


        Pago_dto resultado = pagoService.crear(dto);


        assertNotNull(resultado);
        assertEquals(1, resultado.getId_pago());
        assertEquals("REF123", resultado.getReferencia_pago());
        verify(pagoRepository, times(1)).save(any(Pago_model.class));
    }

}
