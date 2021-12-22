package com.gestion.tramite.repositorio;

import com.gestion.tramite.entidad.Contacto;
import com.gestion.tramite.entidad.Emails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmailsRepository extends JpaRepository<Emails, Long> {

    @Query("select e from Emails e where e.persona.id = :idPersona order by e.fechaEnvio desc")
    public List<Emails> getEmailsByIdPersona(@Param("idPersona") Long idPersona);

}
