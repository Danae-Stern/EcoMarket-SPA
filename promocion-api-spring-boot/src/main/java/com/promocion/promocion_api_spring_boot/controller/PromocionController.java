package com.promocion.promocion_api_spring_boot.controller;

import com.promocion.promocion_api_spring_boot.dto.PromocionDTO;
import com.promocion.promocion_api_spring_boot.service.PromocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.Link;


import java.util.List;

@RestController
@RequestMapping("/api/promocion")
public class PromocionController {

    @Autowired
    private PromocionService service;

    @PostMapping
    public ResponseEntity<PromocionDTO> guardar(@RequestBody PromocionDTO dto) {
        return ResponseEntity.ok(service.guardar(dto));
    }

    @GetMapping
    public ResponseEntity<List<PromocionDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromocionDTO> buscar(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PromocionDTO> actualizar(@PathVariable Integer id, @RequestBody PromocionDTO dto) {
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
    public ResponseEntity<PromocionDTO> obtenerHATEOAS(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(dto -> {
                    dto.add(linkTo(methodOn(PromocionController.class).obtenerHATEOAS(id)).withSelfRel());
                    dto.add(linkTo(methodOn(PromocionController.class).obtenerTodosHATEOAS()).withRel("todos"));
                    dto.add(linkTo(methodOn(PromocionController.class).eliminar(id)).withRel("eliminar"));

                    // URLs desde configuración
                    dto.add(Link.of("http://localhost:8888/api/proxy/metodo_pago/" + dto.getId()).withSelfRel());
                    dto.add(Link.of("http://localhost:8888/api/proxy/metodo_pago/" + dto.getId()).withRel("Modificar HATEOAS").withType("PUT"));
                    dto.add(Link.of("http://localhost:8888/api/proxy/metodo_pago/" + dto.getId()).withRel("Eliminar HATEOAS").withType("DELETE"));

                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }



    //METODO HATEOAS para listar todos los productos utilizando HATEOAS
    @GetMapping("/hateoas")
    public List<PromocionDTO> obtenerTodosHATEOAS() {
        List<PromocionDTO> lista = service.listar();

        for (PromocionDTO dto : lista) {
            //link url de la misma API
            dto.add(linkTo(methodOn(PromocionController.class).obtenerHATEOAS(dto.getId())).withSelfRel());

            //link HATEOAS para API Gateway "A mano"
            dto.add(Link.of("http://localhost:8888/api/proxy/promocion").withRel("Get todos HATEOAS"));
            dto.add(Link.of("http://localhost:8888/api/proxy/promocion/" + dto.getId()).withRel("Crear HATEOAS").withType("POST"));
        }

        return lista;
    }
}
