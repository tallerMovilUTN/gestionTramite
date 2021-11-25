package com.gestion.tramite.service;


import com.gestion.tramite.entidad.GestionTramite;

import java.util.List;
import java.util.Optional;

public interface GestionTramiteService {

    public List<GestionTramite> listAllGestionTramite();
    public GestionTramite getGestionTramite(Long id);
    public GestionTramite createGestionTramite(GestionTramite a1);
    public GestionTramite updateGestionTramite(GestionTramite a1);
    public GestionTramite deleteGestionTramite(Long id);
    public void borrarGestionTramite(Long id);
}
