package com.example.Pagos.Service;

import com.example.Pagos.Dto.PagosDto;
import com.example.Pagos.Model.PagosModel;
import com.example.Pagos.Repository.PagosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PagosService {

    @Autowired
    private PagosRepository pagosRepository;

    // 1. READ ALL
    public List<PagosModel> obtenerTodos() {
        log.info("[LOG - Pagos] Consultando la lista histórica de transacciones");
        return pagosRepository.findAll();
    }

    // 2. READ BY ID
    public Optional<PagosModel> obtenerPorId(Long id) {
        log.info("[LOG - Pagos] Buscando registro de pago con ID: {}", id);
        return pagosRepository.findById(id);
    }

    // 3. CREATE
    public PagosModel registrarPago(PagosDto dto) {
        log.info("[LOG - Pagos] Procesando nuevo pago para la orden ID: {}", dto.getOrdenId());
        PagosModel pago = new PagosModel();
        pago.setOrdenId(dto.getOrdenId());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setEstado(dto.getEstado());
        return pagosRepository.save(pago);
    }

    // 4. UPDATE
    public Optional<PagosModel> actualizarPago(Long id, PagosDto dto) {
        log.info("[LOG - Pagos] Intentando modificar datos del pago con ID: {}", id);
        return pagosRepository.findById(id).map(existente -> {
            existente.setOrdenId(dto.getOrdenId());
            existente.setMetodoPago(dto.getMetodoPago());
            existente.setEstado(dto.getEstado());
            return pagosRepository.save(existente);
        });
    }

    // 5. DELETE
    public boolean eliminarPago(Long id) {
        log.info("[LOG - Pagos] Intentando eliminar el registro de pago con ID: {}", id);
        if (pagosRepository.existsById(id)) {
            pagosRepository.deleteById(id);
            return true;
        }
        return false;
    }
}