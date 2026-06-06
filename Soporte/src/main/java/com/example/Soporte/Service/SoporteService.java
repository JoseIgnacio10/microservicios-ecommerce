package com.example.Soporte.Service;

import com.example.Soporte.Dto.SoporteDto;
import com.example.Soporte.Model.SoporteModel;
import com.example.Soporte.Repository.SoporteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SoporteService {

    @Autowired
    private SoporteRepository soporteRepository;

    // 1. READ ALL
    public List<SoporteModel> obtenerTodos() {
        log.info("[LOG - Soporte] Obteniendo el listado de todos los tickets de asistencia");
        return soporteRepository.findAll();
    }

    // 2. READ BY ID
    public Optional<SoporteModel> obtenerPorId(Long id) {
        log.info("[LOG - Soporte] Buscando ticket de soporte ID: {}", id);
        return soporteRepository.findById(id);
    }

    // 3. CREATE
    public SoporteModel crearTicket(SoporteDto dto) {
        log.info("[LOG - Soporte] Abriendo nuevo ticket para el usuario ID: {}", dto.getUsuarioId());
        SoporteModel ticket = new SoporteModel();
        ticket.setUsuarioId(dto.getUsuarioId());
        ticket.setAsunto(dto.getAsunto());
        ticket.setDescripcion(dto.getDescripcion());
        ticket.setEstado(dto.getEstado());
        return soporteRepository.save(ticket);
    }

    // 4. UPDATE
    public Optional<SoporteModel> actualizarTicket(Long id, SoporteDto dto) {
        log.info("[LOG - Soporte] Modificando estado/datos del ticket ID: {}", id);
        return soporteRepository.findById(id).map(existente -> {
            existente.setUsuarioId(dto.getUsuarioId());
            existente.setAsunto(dto.getAsunto());
            existente.setDescripcion(dto.getDescripcion());
            existente.setEstado(dto.getEstado());
            return soporteRepository.save(existente);
        });
    }

    // 5. DELETE
    public boolean eliminarTicket(Long id) {
        log.info("[LOG - Soporte] Eliminando permanentemente el ticket ID: {}", id);
        if (soporteRepository.existsById(id)) {
            soporteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}