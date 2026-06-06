package com.example.Envios.Service;

import com.example.Envios.Dto.EnviosDto;
import com.example.Envios.Model.Envios;
import com.example.Envios.Repository.EnviosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EnviosService {

    @Autowired
    private EnviosRepository enviosRepository;

    // 1. READ ALL
    public List<Envios> obtenerTodos() {
        log.info("[LOG - Envios] Consultando el historial global de despachos");
        return enviosRepository.findAll();
    }

    // 2. READ BY ID
    public Optional<Envios> obtenerPorId(Long id) {
        log.info("[LOG - Envios] Localizando envío con ID: {}", id);
        return enviosRepository.findById(id);
    }

    // 3. CREATE
    public Envios crearEnvio(EnviosDto dto) {
        log.info("[LOG - Envios] Registrando despacho para la orden ID: {}", dto.getOrdenId());
        Envios envio = new Envios();
        envio.setOrdenId(dto.getOrdenId());
        envio.setDireccion(dto.getDireccion());
        envio.setEstado(dto.getEstado());
        return enviosRepository.save(envio);
    }

    // 4. ACTUALIZAR
    public Optional<Envios> actualizarEnvio(Long id, EnviosDto dto) {
        log.info("[LOG - Envios] Actualizando datos de la entrega ID: {}", id);
        return enviosRepository.findById(id).map(envioExistente -> {
            envioExistente.setOrdenId(dto.getOrdenId());
            envioExistente.setDireccion(dto.getDireccion());
            envioExistente.setEstado(dto.getEstado());
            return enviosRepository.save(envioExistente);
        });
    }

    // 5. ELIMINAR
    public boolean eliminarEnvio(Long id) {
        log.info("[LOG - Envios] Removiendo registro de despacho ID: {}", id);
        if (enviosRepository.existsById(id)) {
            enviosRepository.deleteById(id);
            return true;
        }
        return false;
    }
}