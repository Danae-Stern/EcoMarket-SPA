package com.inventario.inventario_api_spring_boot.controller;


import com.inventario.inventario_api_spring_boot.dto.InventarioDTO;
import com.inventario.inventario_api_spring_boot.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {
    @Autowired
    private InventarioService service;
    @Autowired
    private Environment environment;

    @PostMapping
    public ResponseEntity<InventarioDTO> guardar(@RequestBody InventarioDTO dto){
        return ResponseEntity.ok(service.guardar(dto));
    }

    @GetMapping
    public ResponseEntity<List<InventarioDTO>> listar(){
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioDTO> buscar(@PathVariable Integer id){
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioDTO> actualizar(@PathVariable Integer id, @RequestBody InventarioDTO dto){
        return service.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        return service.eliminar(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    //HATEOAS
    @GetMapping("/hateoas/{id}")
    public ResponseEntity<InventarioDTO> obtenerHATEOAS(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(dto -> {
                    dto.add(linkTo(methodOn(InventarioController.class).obtenerHATEOAS(id)).withSelfRel());
                    dto.add(linkTo(methodOn(InventarioController.class).obtenerTodosHATEOAS()).withRel("todos"));
                    dto.add(linkTo(methodOn(InventarioController.class).eliminar(id)).withRel("eliminar"));

                    // URLs desde configuración
                    dto.add(Link.of("http://localhost:8888/api/proxy/inventario/" + dto.getId()).withSelfRel());
                    dto.add(Link.of("http://localhost:8888/api/proxy/inventario/" + dto.getId()).withRel("Modificar HATEOAS").withType("PUT"));
                    dto.add(Link.of("http://localhost:8888/api/proxy/inventario/" + dto.getId()).withRel("Eliminar HATEOAS").withType("DELETE"));

                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }



    //METODO HATEOAS para listar todos los productos utilizando HATEOAS
    @GetMapping("/hateoas")
    public List<InventarioDTO> obtenerTodosHATEOAS() {
        List<InventarioDTO> lista = service.listar();

        for (InventarioDTO dto : lista) {
            //link url de la misma API
            dto.add(linkTo(methodOn(InventarioController.class).obtenerHATEOAS(dto.getId())).withSelfRel());

            //link HATEOAS para API Gateway "A mano"
            dto.add(Link.of("http://localhost:8888/api/proxy/inventario").withRel("Get todos HATEOAS"));
            dto.add(Link.of("http://localhost:8888/api/proxy/inventario/" + dto.getId()).withRel("Crear HATEOAS").withType("POST"));
        }

        return lista;
    }
}

