package com.example.Resenas.Service;

import com.example.Resenas.Dto.ResenasDto;
import com.example.Resenas.Model.ResenasModel;
import com.example.Resenas.Repository.ResenasRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ResenasService {

    @Autowired
    private ResenasRepository resenasRepository;

    // 1. READ ALL
    public List<ResenasModel> obtenerTodas() {
        log.info("[LOG - Resenas] Obteniendo el listado completo de calificaciones y reseñas");
        return resenasRepository.findAll();
    }

    // 2. READ BY ID
    public Optional<ResenasModel> obtenerPorId(Long id) {
        log.info("[LOG - Resenas] Buscando reseña con ID: {}", id);
        return resenasRepository.findById(id);
    }

    // 3. CREATE
    public ResenasModel crearResena(ResenasDto dto) {
        log.info("[LOG - Resenas] Registrando nueva opinión para el producto ID: {}", dto.getProductoId());
        ResenasModel resena = new ResenasModel();
        resena.setProductoId(dto.getProductoId());
        resena.setUsuarioId(dto.getUsuarioId());
        resena.setCalificacion(dto.getCalificacion());
        resena.setComentario(dto.getComentario());
        return resenasRepository.save(resena);
    }

    // 4. UPDATE
    public Optional<ResenasModel> actualizarResena(Long id, ResenasDto dto) {
        log.info("[LOG - Resenas] Editando los datos de la reseña con ID: {}", id);
        return resenasRepository.findById(id).map(existente -> {
            existente.setProductoId(dto.getProductoId());
            existente.setUsuarioId(dto.getUsuarioId());
            existente.setCalificacion(dto.getCalificacion());
            existente.setComentario(dto.getComentario());
            return resenasRepository.save(existente);
        });
    }

    // 5. DELETE
    public boolean eliminarResena(Long id) {
        log.info("[LOG - Resenas] Eliminando del sistema la reseña con ID: {}", id);
        if (resenasRepository.existsById(id)) {
            resenasRepository.deleteById(id);
            return true;
        }
        return false;
    }
}