package com.soporte.soporte_api_spring_boot.controller;


import com.soporte.soporte_api_spring_boot.dto.SoporteDTO;
import com.soporte.soporte_api_spring_boot.model.Soporte;
import com.soporte.soporte_api_spring_boot.service.SoporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.Link;

import java.util.List;

@RestController
@RequestMapping("/api/soporte")
public class SoporteController {

    @Autowired
    private SoporteService service;

    @PostMapping
    public ResponseEntity<SoporteDTO> guardar(@RequestBody SoporteDTO dto) {
        return ResponseEntity.ok(service.guardar(dto));
    }

     @GetMapping
    public ResponseEntity<List<SoporteDTO>> listar() {return  ResponseEntity.ok(service.listar());}

    @GetMapping("/{id}")
    public ResponseEntity<SoporteDTO> buscar(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SoporteDTO> actualizar(@PathVariable Integer id, @RequestBody SoporteDTO dto) {
        return service.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/hateoas/{id}")
    public ResponseEntity<SoporteDTO> obtenerHATEOAS(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(dto -> {
                    dto.add(linkTo(methodOn(SoporteController.class).obtenerHATEOAS(id)).withSelfRel());
                    dto.add(linkTo(methodOn(SoporteController.class).obtenerTodosHATEOAS()).withRel("todos"));
                    dto.add(linkTo(methodOn(SoporteController.class).eliminar(id)).withRel("eliminar"));

                    // URLs desde configuración
                    dto.add(Link.of("http://localhost:8888/api/proxy/soporte/" + dto.getId()).withSelfRel());
                    dto.add(Link.of("http://localhost:8888/api/proxy/soporte/" + dto.getId()).withRel("Modificar HATEOAS").withType("PUT"));
                    dto.add(Link.of("http://localhost:8888/api/proxy/soporte/" + dto.getId()).withRel("Eliminar HATEOAS").withType("DELETE"));

                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }



    //METODO HATEOAS para listar todos los productos utilizando HATEOAS
    @GetMapping("/hateoas")
    public List<SoporteDTO> obtenerTodosHATEOAS() {
        List<SoporteDTO> lista = service.listar();

        for (SoporteDTO dto : lista) {
            //link url de la misma API
            dto.add(linkTo(methodOn(SoporteController.class).obtenerHATEOAS(dto.getId())).withSelfRel());

            //link HATEOAS para API Gateway "A mano"
            dto.add(Link.of("http://localhost:8888/api/proxy/soporte").withRel("Get todos HATEOAS"));
            dto.add(Link.of("http://localhost:8888/api/proxy/soporte/" + dto.getId()).withRel("Crear HATEOAS").withType("POST"));
        }

        return lista;
    }

}
