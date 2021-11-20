package com.gestion.tramite.service;



import com.gestion.tramite.entidad.Contacto;


import java.util.List;

public interface ContactoService {

    public List<Contacto> listAllContactos();
    public List<Contacto> getContactos(Integer idPersona);
    public Contacto getContacto(Integer id);
    public Contacto createContacto(Contacto a1);
    public Contacto updateContacto(Contacto a1);
    public Contacto deleteContacto(Integer id);
    public void borrarContacto(Integer id);
}
