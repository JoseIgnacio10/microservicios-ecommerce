package com.example.Resenas.Controller;

import com.example.Resenas.Dto.ResenasDto;
import com.example.Resenas.Model.ResenasModel;
import com.example.Resenas.Service.ResenasService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/resenas")
public class ResenasController {

    @Autowired
    private ResenasService resenasService;

    // Listar todas (GET http://localhost:8087/api/resenas)
    @GetMapping
    public ResponseEntity<List<ResenasModel>> listar() {
        log.info("[API REST] Petición GET recibida en /api/resenas");
        return ResponseEntity.ok(resenasService.obtenerTodas());
    }

    // Buscar por ID (GET http://localhost:8087/api/resenas/{id})
    @GetMapping("/{id}")
    public ResponseEntity<ResenasModel> buscarPorId(@PathVariable Long id) {
        log.info("[API REST] Buscando comentario por ID: {}", id);
        return resenasService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear una nueva reseña (POST http://localhost:8087/api/resenas)
    @PostMapping
    public ResponseEntity<ResenasModel> guardar(@Valid @RequestBody ResenasDto dto) {
        log.info("[API REST] Petición POST para añadir una reseña");
        return new ResponseEntity<>(resenasService.crearResena(dto), HttpStatus.CREATED);
    }

    // Actualizar reseña existente (PUT http://localhost:8087/api/resenas/{id})
    @PutMapping("/{id}")
    public ResponseEntity<ResenasModel> actualizar(@PathVariable Long id, @Valid @RequestBody ResenasDto dto) {
        log.info("[API REST] Petición PUT para actualizar la reseña ID: {}", id);
        return resenasService.actualizarResena(id, dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Borrar reseña (DELETE http://localhost:8087/api/resenas/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("[API REST] Petición DELETE para remover la reseña ID: {}", id);
        if (resenasService.eliminarResena(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}