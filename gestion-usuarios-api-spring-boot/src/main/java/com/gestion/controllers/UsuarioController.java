package com.gestion.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.dto.CrearUsuarioRequest;
import com.gestion.dto.UsuarioDTO;
import com.gestion.models.Usuario;
import com.gestion.services.UsuarioService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping
    public List<UsuarioDTO> getAll() {
        return service.listarUsuarios();
    }

    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody CrearUsuarioRequest request) {
        Usuario creado = service.crearUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> editarUsuario(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO actualizado = service.actualizarUsuario(id, usuarioDTO);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
    try {
        UsuarioDTO usuario = service.buscarUsuarioPorId(id);

        usuario.add(
            linkTo(methodOn(UsuarioController.class).getById(id)).withSelfRel(),
            linkTo(methodOn(UsuarioController.class).getAll()).withRel("todos"),
            linkTo(methodOn(UsuarioController.class).editarUsuario(id, usuario)).withRel("editar"),
            linkTo(methodOn(UsuarioController.class).eliminar(id)).withRel("eliminar")
        );

        return ResponseEntity.ok(usuario);
    } catch (RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(Map.of("mensaje", ex.getMessage()));
    }
}

}
