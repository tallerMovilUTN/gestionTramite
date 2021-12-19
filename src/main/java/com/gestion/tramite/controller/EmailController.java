package com.gestion.tramite.controller;

import com.gestion.tramite.entidad.Contacto;
import com.gestion.tramite.entidad.Emails;
import com.gestion.tramite.entidad.Persona;
import com.gestion.tramite.entidad.dto.EmailDTO;
import com.gestion.tramite.service.ContactoService;
import com.gestion.tramite.service.EmailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@CrossOrigin( origins = "*" , methods = { RequestMethod. GET , RequestMethod. POST,RequestMethod.PUT })
@RestController
@RequestMapping(value = "/sendMail")
public class EmailController {

    @Autowired
    EmailsService service;
    Logger logger = LoggerFactory.getLogger(EmailController.class);

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody EmailDTO emailDTO, @RequestBody Persona persona) {
        try
        {
            logger.info("ESTOY EN SEND MAIL");
            logger.info("MENSAJE A ENVIAR:: "+emailDTO);
            sendmail(emailDTO);
            Emails email = new Emails();
            email.setPersona(persona);
            email.setTo(emailDTO.getTo());
            email.setSubject(emailDTO.getSubject());
            email.setMsg(email.getMsg());
            service.createEmail(email);
            //return "OK";
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

    }




    @GetMapping(value = "/{idPersona}")
    public ResponseEntity<List<Emails>> getEmailsByIdPersona(@PathVariable("idPersona") Long idPersona)
    {
        logger.info("ESTOY EN getEmailsByIdPersona-"+idPersona);
        List<Emails> result = service.getEmailsByIdPersona(idPersona);
        if (result.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }



    @GetMapping
    public ResponseEntity<List<Emails>> listEmails()
    {
        List<Emails> cat = new ArrayList<>();
        cat = service.listAllEmail();
        if (cat.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cat);
    }





    private void sendmail(EmailDTO emailDTO) throws AddressException, MessagingException, IOException
    {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("gestionciudadania342@gmail.com", "GestionCiud2021");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("gestionciudadania342@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDTO.getTo()));
        msg.setSubject(emailDTO.getSubject());
        //msg.setContent("Tutorials point email", "text/html");
        String contenido = "<html xmlns:th=\"http://www.thymeleaf.org\">\n" +
                "  <head>\n" +
                "    <title th:remove=\"all\">Template for HTML email with inline image</title>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <p>\n" +
               // "      Hola,"+emailDTO.getName()+" !\n" +
                //"      You have been successfully subscribed to the <b>Fake newsletter</b> on\n" +
                emailDTO.getMsg()+
                "    </p>\n" +
                "    <p>\n" +
               // "      <img src=\"sample.png\" th:src=\"|cid:${imageResourceName}|\" />\n" +
                "    </p>\n" +
                "    <p>\n" +
                "      Saludos, <br />\n" +
                "      <em>Estudios Gestion Ciudadania</em>\n" +
                "    </p>\n" +
                "  </body>\n" +
                "</html>";
        msg.setContent(contenido, "text/html");



        msg.setSentDate(new Date());

        /**MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Tutorials point email", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();
        attachPart.attachFile("/var/tmp/image19.png");
        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);**/
        Transport.send(msg);
    }
}