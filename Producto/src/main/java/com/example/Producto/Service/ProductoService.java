package com.example.Producto.Service;

import com.example.Producto.Dto.ProductoDto;
import com.example.Producto.Model.ProductoModel;
import com.example.Producto.Repository.ProductoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // 1. READ ALL
    public List<ProductoModel> obtenerTodos() {
        log.info("[LOG - Producto] Solicitando todos los registros del catálogo");
        return productoRepository.findAll();
    }

    // 2. READ BY ID
    public Optional<ProductoModel> obtenerPorId(Long id) {
        log.info("[LOG - Producto] Buscando producto con ID: {}", id);
        return productoRepository.findById(id);
    }

    // 3. CREATE
    public ProductoModel crearProducto(ProductoDto dto) {
        log.info("[LOG - Producto] Insertando nuevo producto: {}", dto.getNombre());
        ProductoModel producto = new ProductoModel();
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        return productoRepository.save(producto);
    }

    // 4. UPDATE
    public Optional<ProductoModel> actualizarProducto(Long id, ProductoDto dto) {
        log.info("[LOG - Producto] Intentando actualizar producto con ID: {}", id);
        return productoRepository.findById(id).map(productoExistente -> {
            productoExistente.setNombre(dto.getNombre());
            productoExistente.setPrecio(dto.getPrecio());
            return productoRepository.save(productoExistente);
        });
    }

    // 5. DELETE
    public boolean eliminarProducto(Long id) {
        log.info("[LOG - Producto] Intentando eliminar producto con ID: {}", id);
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}