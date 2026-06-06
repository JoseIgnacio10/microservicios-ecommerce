package com.example.Producto.Controller;

import com.example.Producto.Dto.ProductoDto;
import com.example.Producto.Model.ProductoModel;
import com.example.Producto.Service.ProductoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Listar todo (GET http://localhost:8081/api/productos)
    @GetMapping
    public ResponseEntity<List<ProductoModel>> listar() {
        log.info("[API REST] Petición GET en /api/productos");
        return ResponseEntity.ok(productoService.obtenerTodos());
    }

    // Buscar por ID (GET http://localhost:8081/api/productos/{id})
    @GetMapping("/{id}")
    public ResponseEntity<ProductoModel> buscarPorId(@PathVariable Long id) {
        log.info("[API REST] Petición GET para buscar ID: {}", id);
        return productoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Guardar nuevo producto (POST http://localhost:8081/api/productos)
    @PostMapping
    public ResponseEntity<ProductoModel> guardar(@Valid @RequestBody ProductoDto dto) {
        log.info("[API REST] Petición POST para guardar producto");
        return new ResponseEntity<>(productoService.crearProducto(dto), HttpStatus.CREATED);
    }

    // Actualizar producto existente (PUT http://localhost:8081/api/productos/{id})
    @PutMapping("/{id}")
    public ResponseEntity<ProductoModel> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoDto dto) {
        log.info("[API REST] Petición PUT para actualizar ID: {}", id);
        return productoService.actualizarProducto(id, dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Eliminar producto (DELETE http://localhost:8081/api/productos/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("[API REST] Petición DELETE para borrar ID: {}", id);
        if (productoService.eliminarProducto(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}