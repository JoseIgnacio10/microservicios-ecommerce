package com.example.Usuario.Service;

import com.example.Usuario.Dto.UsuarioDto;
import com.example.Usuario.Model.UsuarioModel;
import com.example.Usuario.Repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository; // Ya acoplado con el nombre correcto

    // 1. READ ALL
    public List<UsuarioModel> obtenerTodos() {
        log.info("[LOG - Usuario] Consultando listado completo de usuarios registrados");
        return usuarioRepository.findAll();
    }

    // 2. READ BY ID
    public Optional<UsuarioModel> obtenerPorId(Long id) {
        log.info("[LOG - Usuario] Buscando usuario con ID: {}", id);
        return usuarioRepository.findById(id);
    }

    // 3. CREATE
    public UsuarioModel crearUsuario(UsuarioDto dto) {
        log.info("[LOG - Usuario] Registrando un nuevo usuario con email: {}", dto.getEmail());
        UsuarioModel usuario = new UsuarioModel();
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());
        return usuarioRepository.save(usuario);
    }

    // 4. UPDATE
    public Optional<UsuarioModel> actualizarUsuario(Long id, UsuarioDto dto) {
        log.info("[LOG - Usuario] Actualizando datos del usuario con ID: {}", id);
        return usuarioRepository.findById(id).map(existente -> {
            existente.setNombre(dto.getNombre());
            existente.setEmail(dto.getEmail());
            existente.setPassword(dto.getPassword());
            return usuarioRepository.save(existente);
        });
    }

    // 5. DELETE
    public boolean eliminarUsuario(Long id) {
        log.info("[LOG - Usuario] Dando de baja al usuario con ID: {}", id);
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}