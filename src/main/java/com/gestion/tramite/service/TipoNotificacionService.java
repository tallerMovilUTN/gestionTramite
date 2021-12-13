package com.gestion.tramite.service;

import com.gestion.tramite.entidad.TipoNotificacion;
import com.gestion.tramite.entidad.TipoTramite;
import com.gestion.tramite.entidad.User;

import java.util.List;

public interface TipoNotificacionService {

    List<TipoNotificacion> listAllTipoNotificacion();
    TipoNotificacion getTipoNotificacion(Long id);
    TipoNotificacion createTipoNotificacion(TipoNotificacion a1);
    TipoNotificacion updateTipoNotificacion(TipoNotificacion a1);
    TipoNotificacion deleteTipoNotificacion(Long id);

}
