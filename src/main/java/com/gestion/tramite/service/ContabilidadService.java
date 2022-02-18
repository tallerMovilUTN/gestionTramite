package com.gestion.tramite.service;

import com.gestion.tramite.entidad.Contabilidad;
import com.gestion.tramite.entidad.Contacto;

import java.util.List;

public interface ContabilidadService
{
    public List<Contabilidad> listAllContabilidad();
    public Contabilidad getContabilidad(Long id);
    public Contabilidad createContabilidad(Contabilidad a1);
    public Contabilidad updateContabilidad(Contabilidad a1);
    public Contabilidad deleteContabilidad(Long id);
    public void borrarContabilidad(Long id);

}
