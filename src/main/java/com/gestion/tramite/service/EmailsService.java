package com.gestion.tramite.service;

import com.gestion.tramite.entidad.Contacto;
import com.gestion.tramite.entidad.Emails;

import java.util.List;

public interface EmailsService {
    public List<Emails> listAllEmail();
    public List<Emails> getEmailsByIdPersona(Long idPersona);
    public Emails getEmail(Long id);
    public Emails createEmail(Emails a1);


}
