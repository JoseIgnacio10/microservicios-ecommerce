package com.example.Inventario.Service;

import com.example.Inventario.Dto.InvenatarioDto;
import com.example.Inventario.Model.InventarioModel;
import com.example.Inventario.Repository.InvenatarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class InventarioService {

    @Autowired
    private InvenatarioRepository invenatarioRepository;

    // 1. READ ALL
    public List<InventarioModel> obtenerTodos() {
        log.info("[LOG - Inventario] Solicitando stock completo de productos");
        return invenatarioRepository.findAll();
    }

    // 2. READ BY ID
    public Optional<InventarioModel> obtenerPorId(Long id) {
        log.info("[LOG - Inventario] Buscando registro de stock con ID: {}", id);
        return invenatarioRepository.findById(id);
    }

    // 3. CREATE
    public InventarioModel crearInventario(InvenatarioDto dto) {
        log.info("[LOG - Inventario] Creando nuevo registro de stock para Producto ID: {}", dto.getProductoId());
        InventarioModel inventario = new InventarioModel();
        inventario.setProductoId(dto.getProductoId());
        inventario.setCantidad(dto.getCantidad());
        return invenatarioRepository.save(inventario);
    }

    // 4. UPDATE
    public Optional<InventarioModel> actualizarInventario(Long id, InvenatarioDto dto) {
        log.info("[LOG - Inventario] Actualizando stock del registro con ID: {}", id);
        return invenatarioRepository.findById(id).map(existente -> {
            existente.setProductoId(dto.getProductoId());
            existente.setCantidad(dto.getCantidad());
            return invenatarioRepository.save(existente);
        });
    }

    // 5. DELETE
    public boolean eliminarInventario(Long id) {
        log.info("[LOG - Inventario] Eliminando registro de stock con ID: {}", id);
        if (invenatarioRepository.existsById(id)) {
            invenatarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}