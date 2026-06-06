package com.example.Notificaciones.Service;

import com.example.Notificaciones.Dto.NotificacionesDto;
import com.example.Notificaciones.Model.NotificacionesModel;
import com.example.Notificaciones.Repository.NotificacionesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class NotificacionesService {

    @Autowired
    private NotificacionesRepository notificacionesRepository;

    // 1. READ ALL
    public List<NotificacionesModel> obtenerTodas() {
        log.info("[LOG - Notificaciones] Consultando historial completo de alertas");
        return notificacionesRepository.findAll();
    }

    // 2. READ BY ID
    public Optional<NotificacionesModel> obtenerPorId(Long id) {
        log.info("[LOG - Notificaciones] Buscando alerta con ID: {}", id);
        return notificacionesRepository.findById(id);
    }

    // 3. CREATE
    public NotificacionesModel registrarNotificacion(NotificacionesDto dto) {
        log.info("[LOG - Notificaciones] Creando nueva notificación para Usuario ID: {}", dto.getUsuarioId());
        NotificacionesModel notificacion = new NotificacionesModel();
        notificacion.setUsuarioId(dto.getUsuarioId());
        notificacion.setTipo(dto.getTipo());
        notificacion.setMensaje(dto.getMensaje());
        return notificacionesRepository.save(notificacion);
    }

    // 4. UPDATE
    public Optional<NotificacionesModel> actualizarNotificacion(Long id, NotificacionesDto dto) {
        log.info("[LOG - Notificaciones] Modificando datos de la notificación ID: {}", id);
        return notificacionesRepository.findById(id).map(existente -> {
            existente.setUsuarioId(dto.getUsuarioId());
            existente.setTipo(dto.getTipo());
            existente.setMensaje(dto.getMensaje());
            return notificacionesRepository.save(existente);
        });
    }

    // 5. DELETE
    public boolean eliminarNotificacion(Long id) {
        log.info("[LOG - Notificaciones] Eliminando registro de alerta ID: {}", id);
        if (notificacionesRepository.existsById(id)) {
            notificacionesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}