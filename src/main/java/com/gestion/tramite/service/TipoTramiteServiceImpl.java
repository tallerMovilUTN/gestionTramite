package com.gestion.tramite.service;

import com.gestion.tramite.entidad.TipoTramite;
import com.gestion.tramite.repositorio.TipoTramiteRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoTramiteServiceImpl implements TipoTramiteService
{
    @Autowired
    private final TipoTramiteRepositorio repo;


    @Override
    public List<TipoTramite> listAllTipoTramite() {
        return repo.findAll();
    }

    @Override
    public TipoTramite getTipoTramite(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public TipoTramite createTipoTramite(TipoTramite a1) {
        a1.setEstado(1);
        return repo.save(a1);
    }

    @Override
    public TipoTramite updateTipoTramite(TipoTramite a1) {

        System.out.println("updateTipoTramite en serviceImpl 1");
        TipoTramite catDB = getTipoTramite(a1.getId());
        if (null == catDB){
            return null;
        }
        //catDB.setDescripcion(a1.getDescripcion());
        System.out.println("updateTipoTramite en serviceImpl 2");
        catDB.setEstado(1);
        System.out.println("updateTipoTramite en serviceImpl 3");
        catDB.setTipo(a1.getTipo());
        System.out.println("updateTipoTramite en serviceImpl 4");
        catDB.setDescripcion(a1.getDescripcion());
        System.out.println("updateTipoTramite en serviceImpl 5");
        catDB.setPresupuesto(a1.getPresupuesto());
        System.out.println("updateTipoTramite en serviceImpl 6");

        return repo.save(catDB);
    }

    @Override
    public TipoTramite deleteTipoTramite(Long id) {

        System.out.println("deleteTipoTramite en serviceImpl 1");
        TipoTramite catDB = getTipoTramite(id);

        System.out.println("deleteTipoTramite en serviceImpl 2");
        if (null == catDB){
            return null;
        }
        System.out.println("deleteTipoTramite en serviceImpl 3");
        //catDB.setEstado(0);
        System.out.println("deleteTipoTramite en serviceImpl 4");
        repo.delete(catDB);
        System.out.println("deleteTipoTramite en serviceImpl 5");

      //return repo.save(catDB);
        return catDB;

    }
}
