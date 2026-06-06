package com.example.Tracking.Service;

import com.example.Tracking.Dto.TrackingDto;
import com.example.Tracking.Model.TrackingModel;
import com.example.Tracking.Repository.TrackingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TrackingService {

    @Autowired
    private TrackingRepository trackingRepository;

    // 1. READ ALL
    public List<TrackingModel> obtenerTodos() {
        log.info("[LOG - Tracking] Solicitando historial completo de rutas");
        return trackingRepository.findAll();
    }

    // 2. READ BY ID
    public Optional<TrackingModel> obtenerPorId(Long id) {
        log.info("[LOG - Tracking] Buscando punto de control ID: {}", id);
        return trackingRepository.findById(id);
    }

    // 3. CREATE
    public TrackingModel crearPunto(TrackingDto dto) {
        log.info("[LOG - Tracking] Creando nueva actualización para Envio ID: {}", dto.getEnvioId());
        TrackingModel tracking = new TrackingModel();
        tracking.setEnvioId(dto.getEnvioId());
        tracking.setUbicacion(dto.getUbicacion());
        tracking.setEstado(dto.getEstado());
        return trackingRepository.save(tracking);
    }

    // 4. UPDATE
    public Optional<TrackingModel> actualizarPunto(Long id, TrackingDto dto) {
        log.info("[LOG - Tracking] Modificando punto de control ID: {}", id);
        return trackingRepository.findById(id).map(existente -> {
            existente.setEnvioId(dto.getEnvioId());
            existente.setUbicacion(dto.getUbicacion());
            existente.setEstado(dto.getEstado());
            return trackingRepository.save(existente);
        });
    }

    // 5. DELETE
    public boolean eliminarPunto(Long id) {
        log.info("[LOG - Tracking] Eliminando registro de ruta ID: {}", id);
        if (trackingRepository.existsById(id)) {
            trackingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}