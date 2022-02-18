package com.gestion.tramite.service;

import com.gestion.tramite.entidad.Contabilidad;
import com.gestion.tramite.entidad.Contacto;
import com.gestion.tramite.repositorio.ContabilidadRepository;
import com.gestion.tramite.repositorio.ContactoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContabilidadServiceImpl implements ContabilidadService {

    @Autowired
    private final ContabilidadRepository repo;

    @Override
    public List<Contabilidad> listAllContabilidad() {
        return repo.findAll();
    }

    @Override
    public Contabilidad getContabilidad(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Contabilidad createContabilidad(Contabilidad a1) {
      //  a1.setEstado(1);
        a1.setFechaAlta(new Date());
        return repo.save(a1);
    }

    @Override
    public Contabilidad updateContabilidad(Contabilidad a1) {
        return repo.save(a1);
    }

    @Override
    public Contabilidad deleteContabilidad(Long id) {
        Contabilidad a1 = getContabilidad(id);
        if (null == a1){
            return null;
        }
        a1.setEstado(0);
        repo.delete(a1);
        return a1;
    }

    @Override
    public void borrarContabilidad(Long id) {
        Contabilidad a1 = getContabilidad(id);
        repo.delete(a1);
    }
}
