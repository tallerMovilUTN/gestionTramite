package com.gestion.tramite.service;

import com.gestion.tramite.entidad.TipoNotificacion;
import com.gestion.tramite.entidad.TipoTramite;
import com.gestion.tramite.repositorio.TipoNotificacionRepository;
import com.gestion.tramite.repositorio.TipoTramiteRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoNotificacionImpl implements TipoNotificacionService {

    @Autowired
    private final TipoNotificacionRepository repo;


    @Override
    public List<TipoNotificacion> listAllTipoNotificacion() {
        return repo.findAll();
    }

    @Override
    public TipoNotificacion getTipoNotificacion(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public TipoNotificacion createTipoNotificacion(TipoNotificacion a1) {
        a1.setEstado(1);
        return repo.save(a1);
    }

    @Override
    public TipoNotificacion updateTipoNotificacion(TipoNotificacion a1) {
        TipoNotificacion tipoBD = getTipoNotificacion(a1.getId());
        if (null == tipoBD){
            return null;
        }
        tipoBD.setColor(a1.getColor());
        tipoBD.setDescripcion(a1.getDescripcion());
        tipoBD.setEstado(a1.getEstado());
        return tipoBD;
    }



    @Override
    public TipoNotificacion deleteTipoNotificacion(Long id) {
        TipoNotificacion tipoBD = getTipoNotificacion(id);

        if (null == tipoBD){
            return null;
        }
        repo.delete(tipoBD);
        return tipoBD;
    }
}
