package com.gestion.tramite.repositorio;

import com.gestion.tramite.entidad.TipoNotificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoNotificacionRepository extends JpaRepository<TipoNotificacion, Long> {
}
