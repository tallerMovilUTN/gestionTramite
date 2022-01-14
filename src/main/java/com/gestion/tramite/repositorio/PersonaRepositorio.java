package com.gestion.tramite.repositorio;


import com.gestion.tramite.entidad.Contacto;
import com.gestion.tramite.entidad.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonaRepositorio extends JpaRepository<Persona, Long> {

    Persona findByDni(int dni);

    @Query("select p from Persona p where p.dni = :dni and p.estado = 1")
    public Persona getPersonasActivasByDNI(@Param("dni") int dni);


    @Query("select p from Persona p where p.estado = 1")
    public List<Persona> getPersonasActivas();


}
