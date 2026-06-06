package com.example.Soporte.Controller;

import com.example.Soporte.Dto.SoporteDto;
import com.example.Soporte.Model.SoporteModel;
import com.example.Soporte.Service.SoporteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/soporte")
public class SoporteController {

    @Autowired
    private SoporteService soporteService;

    // Listar todos (GET http://localhost:8089/api/soporte)
    @GetMapping
    public ResponseEntity<List<SoporteModel>> listar() {
        log.info("[API REST] Petición GET recibida en /api/soporte");
        return ResponseEntity.ok(soporteService.obtenerTodos());
    }

    // Buscar por ID (GET http://localhost:8089/api/soporte/{id})
    @GetMapping("/{id}")
    public ResponseEntity<SoporteModel> buscarPorId(@PathVariable Long id) {
        log.info("[API REST] Buscando ticket ID: {}", id);
        return soporteService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear ticket (POST http://localhost:8089/api/soporte)
    @PostMapping
    public ResponseEntity<SoporteModel> guardar(@Valid @RequestBody SoporteDto dto) {
        log.info("[API REST] Petición POST para abrir caso de soporte");
        return new ResponseEntity<>(soporteService.crearTicket(dto), HttpStatus.CREATED);
    }

    // Actualizar ticket (PUT http://localhost:8089/api/soporte/{id})
    @PutMapping("/{id}")
    public ResponseEntity<SoporteModel> actualizar(@PathVariable Long id, @Valid @RequestBody SoporteDto dto) {
        log.info("[API REST] Petición PUT para actualizar caso ID: {}", id);
        return soporteService.actualizarTicket(id, dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Eliminar ticket (DELETE http://localhost:8089/api/soporte/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("[API REST] Petición DELETE para borrar caso ID: {}", id);
        if (soporteService.eliminarTicket(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}