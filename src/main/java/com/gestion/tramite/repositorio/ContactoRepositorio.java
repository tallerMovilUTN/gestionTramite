package com.gestion.tramite.repositorio;


import com.gestion.tramite.entidad.Contacto;
import com.gestion.tramite.entidad.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactoRepositorio extends JpaRepository<Contacto, Long> {
//Integer id
    @Query("select c from Contacto c where c.persona.id = :idPersona")
    public List<Contacto> getContactos(@Param("idPersona") Long idPersona);



}
