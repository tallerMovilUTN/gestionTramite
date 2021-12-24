package com.gestion.tramite.repositorio;

import com.gestion.tramite.entidad.Archivo;
import com.gestion.tramite.entidad.Notificacion;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

    @Query(value ="SELECT id, tiempo, idGestionTramite, mensaje, fechaAlta, idTipoNotificacion, estado FROM gestionciudadania.notificaciones where date(now())=DATE_ADD(fechaAlta, INTERVAL tiempo DAY);" , nativeQuery=true)

//    @Query(value = "SELECT * FROM event WHERE sport=:sport;", nativeQuery=true)

   //@Query("SELECT id, tiempo, idGestionTramite, mensaje, fechaAlta, idTipoNotificacion, estado FROM gestionciudadania.notificaciones where date ( now() )==DATE_ADD (fechaAlta, INTERVAL tiempo DAY)")
    public List<Notificacion> getNotificacionesTiempo();
}