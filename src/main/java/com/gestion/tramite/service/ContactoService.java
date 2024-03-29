package com.gestion.tramite.service;



import com.gestion.tramite.entidad.Contacto;


import java.util.List;

public interface ContactoService {

    public List<Contacto> listAllContactos();
    public List<Contacto> getContactosByIdPersona(Long idPersona);
    public List<Contacto> getOtrosContactosByIdPersona(Long idPersona);
    public Contacto getContactosByIdPersonaAndTipoRelacion(Long id,Long idTipoRelacion);
    public Contacto getContacto(Long id);
    public Contacto createContacto(Contacto a1);
    public Contacto updateContacto(Contacto a1);
    public Contacto deleteContacto(Long id);
    public void borrarContacto(Long id);
    public void borrarContactosByIdPersona(Long idPersona);
}
