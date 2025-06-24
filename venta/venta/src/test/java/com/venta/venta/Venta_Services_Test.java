package com.venta.venta;

import com.venta.venta.dto.Venta_dto;
import com.venta.venta.models.Venta_model;
import com.venta.venta.repository.Venta_repository;
import com.venta.venta.services.Venta_service;

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

public class Venta_Services_Test {

    @Mock
    private Venta_repository ventaRepository;

    @InjectMocks
    private Venta_service ventaService;

    @Test
    public void testCrearVenta() {

        Venta_dto dto = new Venta_dto(
                null,
                10,      
                20,     
                LocalDate.now()
        );

        Venta_model guardado = new Venta_model(
                1,      
                dto.getId_cliente(),
                dto.getId_vendedor(),
                dto.getFecha_venta()
        );

        when(ventaRepository.save(any(Venta_model.class))).thenReturn(guardado);

        Venta_dto resultado = ventaService.crear(dto);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId_venta());
        assertEquals(10, resultado.getId_cliente());
        assertEquals(20, resultado.getId_vendedor());

        verify(ventaRepository, times(1)).save(any(Venta_model.class));
    }

}
