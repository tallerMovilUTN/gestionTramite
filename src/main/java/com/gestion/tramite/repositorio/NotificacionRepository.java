package com.gestion.tramite.repositorio;

import com.gestion.tramite.entidad.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
}