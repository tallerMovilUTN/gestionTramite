package com.gestion.tramite.service;

import com.gestion.tramite.entidad.Notificacion;
import com.gestion.tramite.entidad.TipoNotificacion;
import com.gestion.tramite.repositorio.NotificacionRepository;
import com.gestion.tramite.repositorio.TipoNotificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacionServiceImpl implements NotificacionService{

    @Autowired
    private final NotificacionRepository repo;

    @Override
    public List<Notificacion> listAllNotificacion() {
        return repo.findAll();
    }

    @Override
    public Notificacion getNotificacion(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Notificacion createNotificacion(Notificacion a1) {
        a1.setEstado(1);
        return repo.save(a1);
    }

    @Override
    public Notificacion updateNotificacion(Notificacion a1) {
        Notificacion notiBD = getNotificacion(a1.getId());
        if (null == notiBD){
            return null;
        }
        notiBD.setTipoNotificacion(a1.getTipoNotificacion());
        notiBD.setMensaje(a1.getMensaje());
        notiBD.setTipoTramite(a1.getTipoTramite());
        notiBD.setTiempo(a1.getTiempo());
        notiBD.setEstado(a1.getEstado());
        return notiBD;
    }

    @Override
    public Notificacion deleteNotificacion(Long id) {
        Notificacion notiBD = getNotificacion(id);

        if (null == notiBD){
            return null;
        }
        repo.delete(notiBD);
        return notiBD;
    }
}
