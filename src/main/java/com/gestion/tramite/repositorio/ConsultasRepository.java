package com.gestion.tramite.repositorio;

import com.fab.servicioFabrica.util.JPAUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ConsultasRepository
{
    private EntityManager em;


    public Object getAll (int cp)
    {
        Object result = null;
        em = JPAUtils.getEntityManagerFactory().createEntityManager();

        String query = "select CodPosPro.descripcion, CodPosPro.id.idCodPostal, Provincias.descLarga " +
                "from CodPosPro, Provincias " +
                "where CodPosPro.id.idCodPostal = " +cp+" "+
                "and CodPosPro.id.idProvincia = Provincias.id";
        result = em.createQuery(query).getSingleResult();


        return result;
    }




}
