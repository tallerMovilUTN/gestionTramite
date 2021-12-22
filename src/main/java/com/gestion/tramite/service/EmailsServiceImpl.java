package com.gestion.tramite.service;

import com.gestion.tramite.entidad.Emails;
import com.gestion.tramite.repositorio.ContactoRepositorio;
import com.gestion.tramite.repositorio.EmailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailsServiceImpl implements EmailsService{

    @Autowired
    private final EmailsRepository repo;

    @Override
    public List<Emails> listAllEmail() {
        return repo.findAll();
    }

    @Override
    public List<Emails> getEmailsByIdPersona(Long idPersona) {
        return repo.getEmailsByIdPersona(idPersona);
    }

    @Override
    public Emails getEmail(Long id)
    {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Emails createEmail(Emails a1) {
        a1.setFechaEnvio(new Date());
        return repo.save(a1);
    }
}
