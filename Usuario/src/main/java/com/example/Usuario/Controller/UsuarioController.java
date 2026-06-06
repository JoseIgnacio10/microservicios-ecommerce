package com.example.Usuario.Controller;

import com.example.Usuario.Dto.UsuarioDto;
import com.example.Usuario.Model.UsuarioModel;
import com.example.Usuario.Service.UsuarioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Listar todos (GET http://localhost:8080/api/usuarios)
    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listar() {
        log.info("[API REST] Petición GET en /api/usuarios");
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    // Buscar por ID (GET http://localhost:8080/api/usuarios/{id})
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> buscarPorId(@PathVariable Long id) {
        log.info("[API REST] Buscando usuario ID: {}", id);
        return usuarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear nuevo (POST http://localhost:8080/api/usuarios)
    @PostMapping
    public ResponseEntity<UsuarioModel> guardar(@Valid @RequestBody UsuarioDto dto) {
        log.info("[API REST] Petición POST para guardar usuario");
        return new ResponseEntity<>(usuarioService.crearUsuario(dto), HttpStatus.CREATED);
    }

    // Actualizar (PUT http://localhost:8080/api/usuarios/{id})
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioModel> actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioDto dto) {
        log.info("[API REST] Petición PUT para actualizar usuario ID: {}", id);
        return usuarioService.actualizarUsuario(id, dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Eliminar (DELETE http://localhost:8080/api/usuarios/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("[API REST] Petición DELETE para borrar usuario ID: {}", id);
        if (usuarioService.eliminarUsuario(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}