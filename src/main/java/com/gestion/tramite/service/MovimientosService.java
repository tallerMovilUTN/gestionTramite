package com.gestion.tramite.service;

import com.gestion.tramite.entidad.Contabilidad;
import com.gestion.tramite.entidad.Movimientos;

import java.util.List;

public interface MovimientosService
{
    public List<Movimientos> listAllMovimientos(Long idContabilidad);

    public Movimientos getMovimiento(Long id);
    public Movimientos nuevoMovimiento(Movimientos a1);
    public Movimientos createMovimientos(List<Movimientos> a1);
    public Movimientos updateMovimiento(Movimientos a1);
    public Movimientos deleteMovimiento(Long id);
    public void borrarMovimiento(Long id);

}
