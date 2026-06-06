package com.example.Tracking.Controller;

import com.example.Tracking.Dto.TrackingDto;
import com.example.Tracking.Model.TrackingModel;
import com.example.Tracking.Service.TrackingService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/tracking")
public class TrackingController {

    @Autowired
    private TrackingService trackingService;

    // Listar todo
    @GetMapping
    public ResponseEntity<List<TrackingModel>> listar() {
        log.info("[API REST] Petición GET en /api/tracking");
        return ResponseEntity.ok(trackingService.obtenerTodos());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<TrackingModel> buscarPorId(@PathVariable Long id) {
        log.info("[API REST] Buscando ruta con ID: {}", id);
        return trackingService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear punto
    @PostMapping
    public ResponseEntity<TrackingModel> guardar(@Valid @RequestBody TrackingDto dto) {
        log.info("[API REST] Petición POST para agregar punto de rastreo");
        return new ResponseEntity<>(trackingService.crearPunto(dto), HttpStatus.CREATED);
    }

    // Actualizar punto
    @PutMapping("/{id}")
    public ResponseEntity<TrackingModel> actualizar(@PathVariable Long id, @Valid @RequestBody TrackingDto dto) {
        log.info("[API REST] Petición PUT para actualizar ruta ID: {}", id);
        return trackingService.actualizarPunto(id, dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Eliminar punto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("[API REST] Petición DELETE para borrar ruta ID: {}", id);
        if (trackingService.eliminarPunto(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}