package com.gestion.tramite.service;

import com.gestion.tramite.entidad.Contacto;
import com.gestion.tramite.entidad.GestionTramite;
import com.gestion.tramite.repositorio.GestionTramiteRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GestionTramiteServiceImpl implements GestionTramiteService
{
    private final GestionTramiteRepositorio repo;


    @Override
    public List<GestionTramite> listAllGestionTramite() {
        return repo.findAll();
    }

    @Override
    public GestionTramite getGestionTramite(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public GestionTramite createGestionTramite(GestionTramite a1) {
        a1.setEstado(1);
        a1.setFechaAlta(new Date());
        return repo.save(a1);
    }

    @Override
    public GestionTramite updateGestionTramite(GestionTramite a1) {
        GestionTramite catDB = getGestionTramite(a1.getId());
        if (null == catDB){
            return null;
        }
        //catDB.setDescripcion(a1.getDescripcion());
        catDB.setEstado(a1.getEstado());

        return repo.save(catDB);
    }

    @Override
    public GestionTramite deleteGestionTramite(Long id) {
        GestionTramite a1 = getGestionTramite(id);
        if (null == a1){
            return null;
        }
        a1.setEstado(0);

        return repo.save(a1);
    }

    @Override
    public GestionTramite borrarGestionTramite(Long id) {
        GestionTramite a1 = getGestionTramite(id);

        if (null == a1){
            return null;
        }

        repo.delete(a1);

        return a1;
    }



}
