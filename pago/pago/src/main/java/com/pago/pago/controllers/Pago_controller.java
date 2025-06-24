package com.pago.pago.controllers;

import com.pago.pago.dto.Pago_dto;
import com.pago.pago.services.Pago_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/pagos")
public class Pago_controller {

    @Autowired
    private Pago_service pagoService;

    @PostMapping
    public ResponseEntity<Pago_dto> crear(@RequestBody Pago_dto dto) {
        return ResponseEntity.ok(pagoService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<Pago_dto>> listar() {
        return ResponseEntity.ok(pagoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago_dto> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(pagoService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago_dto> actualizar(@PathVariable Integer id, @RequestBody Pago_dto dto) {
        return ResponseEntity.ok(pagoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        pagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hateoas/{id}")
    public ResponseEntity<Pago_dto> obtenerHATEOAS(@PathVariable Integer id) {
    Pago_dto dto = pagoService.obtenerPorId(id);

    dto.add(linkTo(methodOn(Pago_controller.class).obtenerHATEOAS(id)).withSelfRel());
    dto.add(linkTo(methodOn(Pago_controller.class).listar()).withRel("todos"));
    dto.add(linkTo(methodOn(Pago_controller.class).eliminar(id)).withRel("eliminar"));

    return ResponseEntity.ok(dto);
    }
}
