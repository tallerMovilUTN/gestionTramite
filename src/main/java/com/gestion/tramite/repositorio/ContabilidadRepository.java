package com.gestion.tramite.repositorio;

import com.gestion.tramite.entidad.Contabilidad;
import com.gestion.tramite.entidad.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface  ContabilidadRepository  extends JpaRepository<Contabilidad, Long> {

    @Query("select p from Contabilidad p where p.estado = 1 order by p.id desc")
    public List<Contabilidad> listAllContabilidad();

}
