package com.gestion.tramite.service;

import com.gestion.tramite.entidad.TipoTramite;

import java.util.List;

public interface TipoTramiteService {

     List<TipoTramite> listAllTipoTramite();
     TipoTramite getTipoTramite(Integer id);
     TipoTramite createTipoTramite(TipoTramite a1);
     TipoTramite updateTipoTramite(TipoTramite a1);
      TipoTramite deleteTipoTramite(Integer id);
}
