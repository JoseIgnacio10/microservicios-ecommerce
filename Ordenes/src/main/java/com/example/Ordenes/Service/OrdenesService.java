package com.example.Ordenes.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;

// Importaciones de tus modelos y DTOs
import com.example.Ordenes.Model.OrdenesModel;
import com.example.Ordenes.Repository.OrdenesRepository;
import com.example.Ordenes.Dto.OrdenesDto;
import com.example.Ordenes.Dto.OrdenConUsuarioDto;
import com.example.Ordenes.Dto.UsuarioJsonDto;

@Slf4j
@Service
public class OrdenesService {

    @Autowired
    private OrdenesRepository ordenesRepository;

    // Herramienta para conectarnos por HTTP con el puerto 8080
    @Autowired
    private RestTemplate restTemplate;

    // 1. READ ALL
    public List<OrdenesModel> obtenerTodas() {
        log.info("[LOG - Ordenes] Consultando el historial completo de órdenes");
        return ordenesRepository.findAll();
    }

    // 2. READ BY ID
    public Optional<OrdenesModel> obtenerPorId(Long id) {
        log.info("[LOG - Ordenes] Buscando orden con ID: {}", id);
        return ordenesRepository.findById(id);
    }

    // 3. CREATE
    public OrdenesModel crearOrden(OrdenesDto dto) {
        log.info("[LOG - Ordenes] Registrando nueva orden para el usuario ID: {}", dto.getUsuarioId());
        OrdenesModel orden = new OrdenesModel();
        orden.setUsuarioId(dto.getUsuarioId());
        orden.setTotal(dto.getTotal());
        orden.setEstado(dto.getEstado());
        return ordenesRepository.save(orden);
    }

    // 4. UPDATE
    public Optional<OrdenesModel> actualizarOrden(Long id, OrdenesDto dto) {
        log.info("[LOG - Ordenes] Intentando actualizar orden con ID: {}", id);
        return ordenesRepository.findById(id).map(ordenExistente -> {
            ordenExistente.setUsuarioId(dto.getUsuarioId());
            ordenExistente.setTotal(dto.getTotal());
            ordenExistente.setEstado(dto.getEstado());
            return ordenesRepository.save(ordenExistente);
        });
    }

    // 5. DELETE
    public boolean eliminarOrden(Long id) {
        log.info("[LOG - Ordenes] Intentando eliminar orden con ID: {}", id);
        if (ordenesRepository.existsById(id)) {
            ordenesRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //  >Comunicación por HTTP con el microservicio de Usuarios
    public Optional<OrdenConUsuarioDto> obtenerOrdenConUsuario(Long id) {
        log.info("[LOG - Ordenes] Buscando orden completa con datos de usuario remoto para ID: {}", id);

        return ordenesRepository.findById(id).map(orden -> {
            OrdenConUsuarioDto dto = new OrdenConUsuarioDto();
            dto.setId(orden.getId());
            dto.setUsuarioId(orden.getUsuarioId());
            dto.setTotal(orden.getTotal());
            dto.setEstado(orden.getEstado());

            // Cruzamos los cables con el puerto 8080 del MS de Usuarios
            String url = "http://localhost:8080/api/usuarios/" + orden.getUsuarioId();
            try {
                UsuarioJsonDto usuarioRemoto = restTemplate.getForObject(url, UsuarioJsonDto.class);
                dto.setUsuario(usuarioRemoto);
            } catch (Exception e) {
                log.error("[LOG - Ordenes] No se pudo conectar con Usuarios. Motivo: {}", e.getMessage());
                dto.setUsuario(null); // Si el otro ms está apagado, no se cae la app, responde null
            }
            return dto;
        });
    }
}