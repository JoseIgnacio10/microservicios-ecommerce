package com.example.Notificaciones.Repository;

import com.example.Notificaciones.Model.NotificacionesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionesRepository extends JpaRepository<NotificacionesModel, Long> {
}