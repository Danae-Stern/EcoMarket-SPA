package com.reportes.reportes_api_spring_boot.controller;

import com.reportes.reportes_api_spring_boot.dto.ReporteDTO;
import com.reportes.reportes_api_spring_boot.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.Link;

import java.util.List;

@RestController
@RequestMapping("/api/reporte")
public class ReporteController {

    @Autowired
    private ReporteService service;

    @PostMapping
    public ResponseEntity<ReporteDTO> guardar(@RequestBody ReporteDTO dto) {
        return ResponseEntity.ok(service.guardar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ReporteDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteDTO> buscar(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReporteDTO> actualizar(@PathVariable Integer id, @RequestBody ReporteDTO dto) {
        return service.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ReporteDTO> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/hateoas/{id}")
    public ResponseEntity<ReporteDTO> obtenerHATEOAS(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(dto -> {
                    dto.add(linkTo(methodOn(ReporteController.class).obtenerHATEOAS(id)).withSelfRel());
                    dto.add(linkTo(methodOn(ReporteController.class).obtenerTodosHATEOAS()).withRel("todos"));
                    dto.add(linkTo(methodOn(ReporteController.class).eliminar(id)).withRel("eliminar"));

                    // URLs desde configuración
                    dto.add(Link.of("http://localhost:8888/api/proxy/reporte/" + dto.getId()).withSelfRel());
                    dto.add(Link.of("http://localhost:8888/api/proxy/reporte/" + dto.getId()).withRel("Modificar HATEOAS").withType("PUT"));
                    dto.add(Link.of("http://localhost:8888/api/proxy/reporte/" + dto.getId()).withRel("Eliminar HATEOAS").withType("DELETE"));

                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }



    //METODO HATEOAS para listar todos los productos utilizando HATEOAS
    @GetMapping("/hateoas")
    public List<ReporteDTO> obtenerTodosHATEOAS() {
        List<ReporteDTO> lista = service.listar();

        for (ReporteDTO dto : lista) {
            //link url de la misma API
            dto.add(linkTo(methodOn(ReporteController.class).obtenerHATEOAS(dto.getId())).withSelfRel());

            //link HATEOAS para API Gateway "A mano"
            dto.add(Link.of("http://localhost:8888/api/proxy/reporte").withRel("Get todos HATEOAS"));
            dto.add(Link.of("http://localhost:8888/api/proxy/reporte/" + dto.getId()).withRel("Crear HATEOAS").withType("POST"));
        }

        return lista;
    }


}
