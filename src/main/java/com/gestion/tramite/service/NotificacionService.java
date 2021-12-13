package com.gestion.tramite.service;

import com.gestion.tramite.entidad.Notificacion;

import java.util.List;

public interface NotificacionService {

    List<Notificacion> listAllNotificacion();
    Notificacion getNotificacion(Long id);
    Notificacion createNotificacion(Notificacion a1);
    Notificacion updateNotificacion(Notificacion a1);
    Notificacion deleteNotificacion(Long id);
}
