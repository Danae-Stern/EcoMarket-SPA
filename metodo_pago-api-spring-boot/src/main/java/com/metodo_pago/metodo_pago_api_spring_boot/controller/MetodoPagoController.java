package com.metodo_pago.metodo_pago_api_spring_boot.controller;

import com.metodo_pago.metodo_pago_api_spring_boot.dto.MetodoPagoDTO;
import com.metodo_pago.metodo_pago_api_spring_boot.model.MetodoPago;
import com.metodo_pago.metodo_pago_api_spring_boot.repository.MetodoPagoRepository;
import com.metodo_pago.metodo_pago_api_spring_boot.service.MetodoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.Link;

import java.util.List;

@RestController
@RequestMapping("/api/metodo_pago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService service;

    @PostMapping
    public ResponseEntity<MetodoPagoDTO> guardar(@RequestBody MetodoPagoDTO dto) {
        return ResponseEntity.ok(service.guardar(dto));
    }

    @GetMapping
    public ResponseEntity<List<MetodoPagoDTO>> listar() {return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPagoDTO> buscar(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoPagoDTO> actualizar(@PathVariable Integer id, @RequestBody MetodoPagoDTO dto) {
        return service.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
    //HATEOAS
    @GetMapping("/hateoas/{id}")
    public ResponseEntity<MetodoPagoDTO> obtenerHATEOAS(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(dto -> {
                    dto.add(linkTo(methodOn(MetodoPagoController.class).obtenerHATEOAS(id)).withSelfRel());
                    dto.add(linkTo(methodOn(MetodoPagoController.class).obtenerTodosHATEOAS()).withRel("todos"));
                    dto.add(linkTo(methodOn(MetodoPagoController.class).eliminar(id)).withRel("eliminar"));

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
    public List<MetodoPagoDTO> obtenerTodosHATEOAS() {
        List<MetodoPagoDTO> lista = service.listar();

        for (MetodoPagoDTO dto : lista) {
            //link url de la misma API
            dto.add(linkTo(methodOn(MetodoPagoController.class).obtenerHATEOAS(dto.getId())).withSelfRel());

            //link HATEOAS para API Gateway "A mano"
            dto.add(Link.of("http://localhost:8888/api/proxy/metodo_pago").withRel("Get todos HATEOAS"));
            dto.add(Link.of("http://localhost:8888/api/proxy/metodo_pago/" + dto.getId()).withRel("Crear HATEOAS").withType("POST"));
        }

        return lista;
    }
}
