package com.envios.envios.controllers;

import com.envios.envios.dto.Envio_dto;
import com.envios.envios.services.Envio_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/envios")
public class Envio_controller {

    @Autowired
    private Envio_service service;

    @PostMapping
    public ResponseEntity<Envio_dto> crear(@RequestBody Envio_dto dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<Envio_dto>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio_dto> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envio_dto> actualizar(@PathVariable Integer id, @RequestBody Envio_dto dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hateoas/{id}")
    public ResponseEntity<Envio_dto> obtenerHateoas(@PathVariable Integer id) {
    Envio_dto dto = service.obtenerPorId(id);

    dto.add(linkTo(methodOn(Envio_controller.class).obtenerHateoas(id)).withSelfRel());
    dto.add(linkTo(methodOn(Envio_controller.class).listar()).withRel("todos"));
    dto.add(linkTo(methodOn(Envio_controller.class).eliminar(id)).withRel("eliminar"));

    return ResponseEntity.ok(dto);
    }

}