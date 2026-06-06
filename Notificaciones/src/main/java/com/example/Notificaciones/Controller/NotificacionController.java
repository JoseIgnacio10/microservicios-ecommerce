package com.example.Notificaciones.Controller;

import com.example.Notificaciones.Dto.NotificacionesDto;
import com.example.Notificaciones.Model.NotificacionesModel;
import com.example.Notificaciones.Service.NotificacionesService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionesService notificacionesService;

    // Listar todas (GET http://localhost:8088/api/notificaciones)
    @GetMapping
    public ResponseEntity<List<NotificacionesModel>> listar() {
        log.info("[API REST] Petición GET recibida en /api/notificaciones");
        return ResponseEntity.ok(notificacionesService.obtenerTodas());
    }

    // Buscar por ID (GET http://localhost:8088/api/notificaciones/{id})
    @GetMapping("/{id}")
    public ResponseEntity<NotificacionesModel> buscarPorId(@PathVariable Long id) {
        log.info("[API REST] Petición GET para localizar alerta ID: {}", id);
        return notificacionesService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear / Enviar nueva (POST http://localhost:8088/api/notificaciones)
    @PostMapping
    public ResponseEntity<NotificacionesModel> enviar(@Valid @RequestBody NotificacionesDto dto) {
        log.info("[API REST] Petición POST para lanzar nueva notificación");
        return new ResponseEntity<>(notificacionesService.registrarNotificacion(dto), HttpStatus.CREATED);
    }

    // Actualizar existente (PUT http://localhost:8088/api/notificaciones/{id})
    @PutMapping("/{id}")
    public ResponseEntity<NotificacionesModel> actualizar(@PathVariable Long id, @Valid @RequestBody NotificacionesDto dto) {
        log.info("[API REST] Petición PUT para modificar alerta ID: {}", id);
        return notificacionesService.actualizarNotificacion(id, dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Eliminar por ID (DELETE http://localhost:8088/api/notificaciones/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("[API REST] Petición DELETE para borrar alerta ID: {}", id);
        if (notificacionesService.eliminarNotificacion(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}