package com.example.Ordenes.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Importaciones de tus modelos, DTOs y el Service
import com.example.Ordenes.Model.OrdenesModel;
import com.example.Ordenes.Service.OrdenesService;
import com.example.Ordenes.Dto.OrdenesDto;
import com.example.Ordenes.Dto.OrdenConUsuarioDto;

@RestController
@RequestMapping("/api/ordenes") // Asegúrate de que esta sea la ruta base que usabas antes
public class OrdenesController {

    @Autowired
    private OrdenesService ordenesService;

    // 1. GET ALL
    @GetMapping
    public List<OrdenesModel> obtenerTodas() {
        return ordenesService.obtenerTodas();
    }

    // 2. GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<OrdenesModel> obtenerPorId(@PathVariable Long id) {
        return ordenesService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. POST (CREAR)
    @PostMapping
    public ResponseEntity<OrdenesModel> crearOrden(@RequestBody OrdenesDto dto) {
        return ResponseEntity.ok(ordenesService.crearOrden(dto));
    }

    // 4. PUT (ACTUALIZAR)
    @PutMapping("/{id}")
    public ResponseEntity<OrdenesModel> actualizarOrden(@PathVariable Long id, @RequestBody OrdenesDto dto) {
        return ordenesService.actualizarOrden(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. DELETE (ELIMINAR)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOrden(@PathVariable Long id) {
        if (ordenesService.eliminarOrden(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // 🔥 NUEVA RUTA: Esta es la que exige la pauta para la comunicación entre Microservicios
    @GetMapping("/{id}/detalle-completo")
    public ResponseEntity<OrdenConUsuarioDto> obtenerOrdenConUsuario(@PathVariable Long id) {
        return ordenesService.obtenerOrdenConUsuario(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}