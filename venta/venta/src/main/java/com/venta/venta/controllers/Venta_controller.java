package com.venta.venta.controllers;

import com.venta.venta.dto.Venta_dto;
import com.venta.venta.services.Venta_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/ventas")
public class Venta_controller {

    @Autowired
    private Venta_service service;

    @PostMapping
    public ResponseEntity<Venta_dto> crear(@RequestBody Venta_dto dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<Venta_dto>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta_dto> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta_dto> actualizar(@PathVariable Integer id, @RequestBody Venta_dto dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    //Se agrega para el hateaos
     @GetMapping("/hateoas/{id}")
    public Venta_dto obtenerHATEOAS(@PathVariable Integer id) {
        Venta_dto dto = service.obtenerPorId(id);

        //Agrega enlaces al dto
        dto.add(linkTo(methodOn(Venta_controller.class).obtenerHATEOAS(id)).withSelfRel());
        dto.add(linkTo(methodOn(Venta_controller.class).obtenerTodosHATEOAS()).withRel("todos"));
        dto.add(linkTo(methodOn(Venta_controller.class).eliminar(id)).withRel("eliminar"));

        return dto;
    }

    @GetMapping("/hateoas")
    public List<Venta_dto> obtenerTodosHATEOAS() {
        List<Venta_dto> lista = service.listar();

        for (Venta_dto dto : lista) {
            dto.add(linkTo(methodOn(Venta_controller.class).obtenerHATEOAS(dto.getId_venta())).withSelfRel());
        }

        return lista;
    }
}