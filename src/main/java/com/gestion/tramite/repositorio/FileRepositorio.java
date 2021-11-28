package com.gestion.tramite.repositorio;


import com.gestion.tramite.entidad.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileRepositorio extends JpaRepository<Archivo, Long> {

    @Query("select a from Archivo a where a.gestionTramite.id= :idGestionTramite order by a.fechaAlta")
    public List<Archivo> getArchivosByIdGestionTramite(@Param("idGestionTramite") Long idGestionTramite);

}
