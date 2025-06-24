package com.vendedores.controllers;

import com.vendedores.dto.Vendedor_dto;
import com.vendedores.services.Vendedor_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/vendedores")
public class Vendedor_controller {

    @Autowired
    private Vendedor_service service;

    @PostMapping
    public ResponseEntity<Vendedor_dto> crear(@RequestBody Vendedor_dto dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<Vendedor_dto>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendedor_dto> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendedor_dto> actualizar(@PathVariable Integer id, @RequestBody Vendedor_dto dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hateoas/{id}")
    public ResponseEntity<Vendedor_dto> obtenerHateoas(@PathVariable Integer id) {
    Vendedor_dto dto = service.obtenerPorId(id);

    dto.add(linkTo(methodOn(Vendedor_controller.class).obtenerHateoas(id)).withSelfRel());
    dto.add(linkTo(methodOn(Vendedor_controller.class).listar()).withRel("todos"));
    dto.add(linkTo(methodOn(Vendedor_controller.class).eliminar(id)).withRel("eliminar"));

    return ResponseEntity.ok(dto);
}

}