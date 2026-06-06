package com.example.Pagos.Controller;

import com.example.Pagos.Dto.PagosDto;
import com.example.Pagos.Model.PagosModel;
import com.example.Pagos.Service.PagosService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/pagos")
public class PagosController {

    @Autowired
    private PagosService pagosService;

    // Listar todos (GET http://localhost:8085/api/pagos)
    @GetMapping
    public ResponseEntity<List<PagosModel>> listar() {
        log.info("[API REST] Petición GET en /api/pagos");
        return ResponseEntity.ok(pagosService.obtenerTodos());
    }

    // Buscar por ID (GET http://localhost:8085/api/pagos/{id})
    @GetMapping("/{id}")
    public ResponseEntity<PagosModel> buscarPorId(@PathVariable Long id) {
        log.info("[API REST] Petición GET para buscar pago ID: {}", id);
        return pagosService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Guardar nuevo pago (POST http://localhost:8085/api/pagos)
    @PostMapping
    public ResponseEntity<PagosModel> guardar(@Valid @RequestBody PagosDto dto) {
        log.info("[API REST] Petición POST para procesar pago");
        return new ResponseEntity<>(pagosService.registrarPago(dto), HttpStatus.CREATED);
    }

    // Actualizar pago existente (PUT http://localhost:8085/api/pagos/{id})
    @PutMapping("/{id}")
    public ResponseEntity<PagosModel> actualizar(@PathVariable Long id, @Valid @RequestBody PagosDto dto) {
        log.info("[API REST] Petición PUT para actualizar pago ID: {}", id);
        return pagosService.actualizarPago(id, dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Eliminar registro de pago (DELETE http://localhost:8085/api/pagos/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("[API REST] Petición DELETE para borrar registro de pago ID: {}", id);
        if (pagosService.eliminarPago(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}