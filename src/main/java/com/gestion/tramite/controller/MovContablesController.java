package com.gestion.tramite.controller;


import com.gestion.tramite.entidad.Contabilidad;
import com.gestion.tramite.entidad.FiltroMovDTO;
import com.gestion.tramite.entidad.MovContableDTO;
import com.gestion.tramite.entidad.ReciboDTO;
import com.gestion.tramite.service.ContabilidadService;
import com.gestion.tramite.service.MovContableDTOService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin( origins = "*" , methods = { RequestMethod. GET , RequestMethod. POST , RequestMethod.DELETE ,RequestMethod.PUT })
@RestController
@Slf4j
@RequestMapping(value = "/movContables")
public class MovContablesController
{

    @Autowired
    private MovContableDTOService service;

    @PostMapping
    public ResponseEntity<List<MovContableDTO>> listarMovContables(@RequestBody FiltroMovDTO filtro)
    {
            List<MovContableDTO> a1 = new ArrayList<>();
            a1 = service.listarMovContables(filtro);
            if (a1.isEmpty())
            {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(a1);
    }





    @GetMapping("/{reportName}")
    public void getReportByParam(
            @PathVariable("reportName") final String reportName,
            @RequestParam(required = false) ReciboDTO recibo,
            HttpServletResponse response) throws SQLException, ClassNotFoundException, JRException, IOException {

        HashMap parameters =  new HashMap<>();
        parameters.put("fecha",recibo.getFecha());
        parameters.put("apellido",recibo.getApellido());
        parameters.put("cantidadletra",recibo.getCantidadletra());
        parameters.put("concepto",recibo.getConcepto());
        parameters.put("cantidadNro",recibo.getCantidadNro());
        parameters.put("nroCuota",recibo.getNroCuota());
        parameters.put("pagoEfectivo",recibo.getPagoEfectivo());
        parameters.put("pagoCheque",recibo.getPagoCheque());
        parameters.put("pagoTransferencia",recibo.getPagoTransferencia());


        /**HashMap parameters = new HashMap<>();
        parameters.put("fecha","27/03/2022");
        parameters.put("apellido","ARRUA HUGO ORLANDO");
        parameters.put("cantidadNro",500000);
        **/

        ClassPathResource resource = new ClassPathResource("jasperreports" + File.separator + reportName + ".jasper");
        InputStream jasperStream = resource.getInputStream();

        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        ///JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline;");
        final OutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
    }


    @GetMapping("/recibo")
    public void getReportByParam(
            @RequestParam("fecha") String fecha,
            @RequestParam("apellido") String apellido,
            @RequestParam("cantidadLetra") String cantidadLetra,
            @RequestParam("concepto") String concepto,
            @RequestParam("cantidadNro") String cantidadNro,
            @RequestParam("nroCuota") String nroCuota,
            @RequestParam("pagoEfectivo") String pagoEfectivo,
            @RequestParam("pagoCheque") String pagoCheque,
            @RequestParam("pagoTransferencia") String pagoTransferencia,
            HttpServletResponse response) throws SQLException, ClassNotFoundException, JRException, IOException {

         log.info("fecha: "+fecha);
         log.info("apellido: "+apellido);
         log.info("cantidadLetra: "+cantidadLetra);
         log.info("concepto: "+concepto);
         log.info("cantidadNro: "+cantidadNro);
         log.info("nroCuota: "+nroCuota);
         log.info("pagoEfectivo: "+pagoEfectivo);
         log.info("pagoCheque: "+pagoCheque);
         log.info("pagoTransferencia: "+pagoTransferencia);
         HashMap parameters = new HashMap<>();
         parameters.put("fecha",fecha);
         parameters.put("apellido",apellido);
         parameters.put("cantidadLetra",cantidadLetra);
         parameters.put("concepto",concepto);
         parameters.put("cantidadNro",cantidadNro);
         parameters.put("nroCuota",nroCuota);
         parameters.put("pagoEfectivo",pagoEfectivo);
         parameters.put("pagoCheque",pagoCheque);
         parameters.put("pagoTransferencia",pagoTransferencia);


        ClassPathResource resource = new ClassPathResource("jasperreports" + File.separator + "recibo.jasper");
        InputStream jasperStream = resource.getInputStream();

        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        ///JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline;");
        final OutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
    }
}
