package com.example.Inventario.Controller;

import com.example.Inventario.Dto.InvenatarioDto;
import com.example.Inventario.Model.InventarioModel;
import com.example.Inventario.Service.InventarioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/inventarios")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    // Listar todo (GET http://localhost:8082/api/inventarios)
    @GetMapping
    public ResponseEntity<List<InventarioModel>> listar() {
        log.info("[API REST] Petición GET en /api/inventarios");
        return ResponseEntity.ok(inventarioService.obtenerTodos());
    }

    // Buscar por ID (GET http://localhost:8082/api/inventarios/{id})
    @GetMapping("/{id}")
    public ResponseEntity<InventarioModel> buscarPorId(@PathVariable Long id) {
        log.info("[API REST] Petición GET para buscar stock ID: {}", id);
        return inventarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Guardar nuevo stock (POST http://localhost:8082/api/inventarios)
    @PostMapping
    public ResponseEntity<InventarioModel> guardar(@Valid @RequestBody InvenatarioDto dto) {
        log.info("[API REST] Petición POST para guardar inventario");
        return new ResponseEntity<>(inventarioService.crearInventario(dto), HttpStatus.CREATED);
    }

    // Actualizar stock existente (PUT http://localhost:8082/api/inventarios/{id})
    @PutMapping("/{id}")
    public ResponseEntity<InventarioModel> actualizar(@PathVariable Long id, @Valid @RequestBody InvenatarioDto dto) {
        log.info("[API REST] Petición PUT para actualizar stock ID: {}", id);
        return inventarioService.actualizarInventario(id, dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Eliminar stock (DELETE http://localhost:8082/api/inventarios/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("[API REST] Petición DELETE para borrar stock ID: {}", id);
        if (inventarioService.eliminarInventario(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}