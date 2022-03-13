package com.gestion.tramite.service;

import com.gestion.tramite.entidad.FiltroMovDTO;
import com.gestion.tramite.entidad.MovContableDTO;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
public class MovContableDTOService
{

    private final Mapper mapper = DozerBeanMapperBuilder.buildDefault();
    private EntityManager em;
    private CriteriaBuilder criteriaBuilder;



    public MovContableDTOService(EntityManager entityManager)
    {
        this.em = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }



    public List<MovContableDTO> listarMovContables (FiltroMovDTO filtro)
    {
        List<MovContableDTO> result = new ArrayList<>();

        SimpleDateFormat dt1 = new SimpleDateFormat("yyyyy-mm-dd");
        //em = com.fab.servicioFabrica.util.JPAUtils.getEntityManagerFactory().createEntityManager();
        String filtroEstado="and c.estado = 1 ";
        String query =  "select " +
                        "c.id as idContabilidad," +
                        "p.apellido, p.nombre, p.dni, t.tipo as tipoTramite," +
                        "m.nroCuota,c.importeTotal,c.diasVencimiento,c.concepto as conceptoContable,c.cantidadCuotas," +
                        "m.estado ,m.importe,m.fechaVencimiento,m.fechaPago,m.concepto as conceptoMov " +
                        "from Contabilidad c," +
                        "Movimientos m," +
                        "GestionTramite g," +
                        "Persona p," +
                        "TipoTramite t " +
                        "where m.contabilidad.id = c.id " +
                        "and c.gestionTramite.id =g.id " +
                        "and g.persona.id = p.id " +
                        "and g.tipoTramite.id = t.id ";

        //query = query +  "and c.estado = 1 ";


        if (Objects.nonNull(filtro))
        {
            log.info("%%%% LLEVA FILTRO DTO%%%%%");
            log.info("%%%% filtro_idContabilidad: "+filtro.getIdContabilidad());
            log.info("%%%% filtro_TipoTramite: "+filtro.getTipoTramite());
            log.info("%%%% filtro_Dni: "+filtro.getDni());
            log.info("%%%% filtro_Estado: "+filtro.getEstado());



            if (filtro.getIdContabilidad() != 0)
            {
                query = query +  "and m.contabilidad.id = "+filtro.getIdContabilidad()+" ";
            }

            if (filtro.getTipoTramite().length() > 0)
            {
                query = query + "and t.tipo like '%"+filtro.getTipoTramite()+"%' ";
            }

            if (filtro.getDni() != 0)
            {
                query = query +  "and p.dni = "+filtro.getDni()+" ";
            }

            log.info("%%%% filtro_Fecha Pago Desde: "+filtro.getFechaPagoDesde());
            log.info("%%%% filtro_Fecha Pago Hasta: "+filtro.getFechaPagoHasta());

            log.info("%%%% filtro_Fecha Venc Hasta: "+filtro.getFechaVtoDesde());
            log.info("%%%% filtro_Fecha Venc Hasta: "+filtro.getFechaVtoHasta());




            if (Objects.nonNull(filtro.getFechaVtoDesde()))
            {

                query = query +  "and m.fechaVencimiento >= '"+dt1.format(filtro.getFechaVtoDesde())+"' ";

            }


            if (Objects.nonNull(filtro.getFechaVtoHasta()))
            {

                query = query +  "and m.fechaVencimiento <= '"+dt1.format(filtro.getFechaVtoHasta())+"' ";

            }
            //and m.fechaPago >= '

            if (Objects.nonNull(filtro.getFechaPagoDesde()))
            {

                query = query +  "and m.fechaPago >= '"+dt1.format(filtro.getFechaPagoDesde())+"' ";

            }


            if (Objects.nonNull(filtro.getFechaPagoHasta()))
            {

                query = query +  "and m.fechaPago >=  '"+dt1.format(filtro.getFechaPagoHasta())+"' ";

            }


            if (filtro.getEstado() != -1)
            {
                filtroEstado ="and c.estado = "+filtro.getEstado();
            }


        }
        query = query +  filtroEstado;


        query = query +  "order by m.nroCuota";

        log.info(query);


        List<Object[]> lista= em.createQuery(query).getResultList();
        SimpleDateFormat dateParser = new SimpleDateFormat("yyyyy-MM-dd HH:mm:ss");
        Date dateFechaVenc = null;
        Date dateFechaPago = null;
        log.info("lista: "+lista.size());

        for (Object[] obj:lista)
        {
            MovContableDTO mov = new MovContableDTO();
            mov.setIdContabilidad(Long.valueOf(obj[0].toString()));
            mov.setApellido(obj[1].toString());
            mov.setNombre(obj[2].toString());
            mov.setDni(Integer.valueOf(obj[3].toString()));
            mov.setTipoTramite(obj[4].toString());
            mov.setNroCuota(Integer.valueOf(obj[5].toString()));
            mov.setImporteTotal(Double.valueOf(obj[6].toString()));
            mov.setDiasVencimiento(Integer.valueOf(obj[7].toString()));
            mov.setConceptoContable(obj[8].toString());
            mov.setCantidadCuotas(Integer.valueOf(obj[9].toString()));
            mov.setEstado(Integer.valueOf(obj[10].toString()));
            mov.setImporte(Double.valueOf(obj[11].toString()));


            try
            {
                mov.setFechaVencimiento(null);
                if (!Objects.isNull(obj[12]))
                {
                    dateFechaVenc = dateParser.parse(String.valueOf(obj[12]));
                    mov.setFechaVencimiento(dateFechaVenc);
                }

                //System.out.println(dateFechaVenc);

                mov.setFechaPago(null);
                if (!Objects.isNull(obj[13]))
                {
                    dateFechaPago = dateParser.parse(String.valueOf(obj[12]));
                    mov.setFechaPago(dateFechaPago);
                }

            }
            catch (ParseException e)
            {
                e.printStackTrace();

            }


            //mov.setFechaPago();

            mov.setConceptoMov(obj[14].toString());

            result.add(mov);

            //log.info("************** "+obj[0]);
            //log.info("Apellido: "+obj[1]+"; Fecha Venc: "+obj[12]+"; CONCEPTO MOV: "+obj[14]);

        }

        return result;
    }


}
