package com.example.Envios.Controller;

import com.example.Envios.Dto.EnviosDto;
import com.example.Envios.Model.Envios;
import com.example.Envios.Service.EnviosService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/envios")
public class EnviosController {

    @Autowired
    private EnviosService enviosService;

    // Listar todo
    @GetMapping
    public ResponseEntity<List<Envios>> listar() {
        log.info("[API REST] Petición GET recibida en /api/envios");
        return ResponseEntity.ok(enviosService.obtenerTodos());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Envios> buscarPorId(@PathVariable Long id) {
        log.info("[API REST] Buscando envío con ID: {}", id);
        return enviosService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Guardar nuevo
    @PostMapping
    public ResponseEntity<Envios> guardar(@Valid @RequestBody EnviosDto dto) {
        log.info("[API REST] Petición POST para dar de alta un despacho");
        return new ResponseEntity<>(enviosService.crearEnvio(dto), HttpStatus.CREATED);
    }

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Envios> actualizar(@PathVariable Long id, @Valid @RequestBody EnviosDto dto) {
        log.info("[API REST] Petición PUT para modificar despacho ID: {}", id);
        return enviosService.actualizarEnvio(id, dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("[API REST] Petición DELETE para borrar el despacho ID: {}", id);
        if (enviosService.eliminarEnvio(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}