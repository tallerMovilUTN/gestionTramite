package com.gestion.tramite.repositorio;


import com.gestion.tramite.entidad.Contacto;
import com.gestion.tramite.entidad.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ContactoRepositorio extends JpaRepository<Contacto, Long> {
//Integer id
    @Query("select c from Contacto c where c.persona.id = :idPersona")
    public List<Contacto> getContactosByIdPersona(@Param("idPersona") Long idPersona);


    @Query("select c from Contacto c where c.persona.id = :idPersona and c.tipoRelacion.id = :tipoRelacion")
    public Contacto getContactosByIdPersonaAndTipoRelacion(@Param("idPersona") Long idPersona,@Param("tipoRelacion") Long tipoRelacion);


    @Transactional
    @Modifying
    @Query("delete from Contacto c where c.persona.id = :idPersona")
    public void borrarContactosByIdPersona( long idPersona);

}
