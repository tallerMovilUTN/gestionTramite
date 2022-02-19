package com.gestion.tramite.service;

import com.gestion.tramite.entidad.Contabilidad;
import com.gestion.tramite.entidad.Movimientos;
import com.gestion.tramite.repositorio.ContactoRepositorio;
import com.gestion.tramite.repositorio.MovimientosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MovimientosServiceImpl  implements MovimientosService {

    @Autowired
    private final MovimientosRepository repo;

    @Override
    public List<Movimientos> listAllMovimientos(Long idContabilidad)
    {
        return repo.listAllMovimientosByIdContabilidad(idContabilidad);
    }

    @Override
    public Movimientos getMovimiento(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Movimientos createMovimientos(List<Movimientos> movimientos) {
        //a1.setEstado(1);
        Movimientos a1 = null;
        for (Movimientos mov:movimientos) 
        {
            //mov.setFechaMovimiento(new Date());
            a1 =  repo.save(mov);
        }
        return a1;
    }

    @Override
    public Movimientos nuevoMovimiento(Movimientos mov) {
        //a1.setEstado(1);
        return repo.save(mov);
    }

    @Override
    public Movimientos updateMovimiento(Movimientos a1) {
        return repo.save(a1);
    }

    @Override
    public Movimientos deleteMovimiento(Long id) {
        Movimientos a1 = getMovimiento(id);
        if (null == a1){
            return null;
        }
        a1.setEstado(0);
        repo.delete(a1);
        return a1;
    }

    @Override
    public void borrarMovimiento(Long id) {
        Movimientos a1 = getMovimiento(id);
        repo.delete(a1);
    }
}
