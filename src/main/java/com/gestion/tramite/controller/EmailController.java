package com.gestion.tramite.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.tramite.entidad.Emails;
import com.gestion.tramite.service.EmailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@CrossOrigin( origins = "*" , methods = { RequestMethod. GET , RequestMethod. POST,RequestMethod.PUT })
@RestController
@RequestMapping(value = "/emailController")
public class EmailController {

    @Autowired

    EmailsService service;
    Logger logger = LoggerFactory.getLogger(EmailController.class);


 //   public ResponseEntity<String> sendEmail(@RequestBody Emails email, @RequestParam("archivos") String archivos ) {
     @RequestMapping(value = "/sendMail",method = RequestMethod.POST,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> sendEmail(@RequestPart("email")String emailA, @RequestParam (required = false,name="archivos")MultipartFile[] file ) {

        logger.info("ESTOY EN SEND MAIL");
        try
        {
          /*  logger.info("ESTOY EN SEND MAIL");

            logger.info("ESTOY EN SEND MAIL archivos "+archivos);
            logger.info("MENSAJE A ENVIAR:: "+email);
            sendmail(email);
            service.createEmail(email);
            //return "OK";
            return new ResponseEntity<>("OK", HttpStatus.OK);*/


            logger.info("JSON(emailA):: "+emailA);
            Emails email = new ObjectMapper().readValue(emailA, Emails.class);


            logger.info("ESTOY EN SEND MAIL archivos "+file);
            logger.info("MENSAJE A ENVIAR:: "+email);

            logger.info("LocalDate.now() 1 :: "+ LocalDate.now());
            sendmail(email,file);
            logger.info("LocalDate.now() 2 :: "+ LocalDate.now());
            service.createEmail(email);
            logger.info("LocalDate.now() 3 :: "+ LocalDate.now());
            //return "OK";
            return new ResponseEntity<>("OK", HttpStatus.OK);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

    }




    @GetMapping(value = "/{id}")
    public ResponseEntity<Emails> getEmail(@PathVariable("id") Long id)
    {
        logger.info("ESTOY EN getEmailsByIdPersona-"+id);
        Emails result = service.getEmail(id);
        /*if (result.e()){
            return ResponseEntity.noContent().build();
        }*/
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


    public  static File multipartToFile(MultipartFile multipart, String fileName) throws IllegalStateException, IOException {
        //File convFile = new File(System.getProperty("Java.io.tmpdir")+"/"+fileName);
        //File convFile = new File(System.getProperty("spring.servlet.multipart.location")+"/"+fileName);
        File convFile = new File("C:/temp"+"/"+fileName);
    try {

        multipart.transferTo(convFile);


    }catch(Exception e) {
        System.out.println("multipartToFile Exception: " + e.getMessage());
    }
        return convFile;
    }


    private void sendmail(Emails emailDTO, MultipartFile[] archivos ) throws AddressException, MessagingException, IOException
    {

        logger.info("ESTOY EN sendmail");

        if (archivos==null){
            logger.info("ESTOY EN sendmail - no se adjuntaron archivos");
        }
        else
        {
            logger.info("ESTOY EN sendmail - se adjuntaron archivos "+archivos.length);
        }

        //logger.info("ESTOY EN sendmail "+archivos.getOriginalFilename());
        //logger.info("ESTOY EN sendmail "+archivos.getName());
        //logger.info("ESTOY EN sendmail "+archivos.getContentType());
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
        //Message msg = new MimeMessage(session);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("gestionciudadania342@gmail.com","Estudio Gestion Ciudadania"));

       // msg.setFrom(new InternetAddress("Estudio Gestion Ciudadania", false));

        logger.info("ESTOY EN sendmail" +emailDTO.getAsunto());

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDTO.getPara()));
        msg.setSubject(emailDTO.getAsunto());
        //msg.setContent("Tutorials point email", "text/html");
     /*   String contenido = "<html xmlns:th=\"http://www.thymeleaf.org\">\n" +
                "  <head>\n" +
                "    <title th:remove=\"all\">Template for HTML email with inline image</title>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <p>\n" +
               // "      Hola,"+emailDTO.getName()+" !\n" +
                //"      You have been successfully subscribed to the <b>Fake newsletter</b> on\n" +
                emailDTO.getMensaje()+
                "    </p>\n" +
                "    <p>\n" +
               // "      <img src=\"sample.png\" th:src=\"|cid:${imageResourceName}|\" />\n" +
                "    </p>\n" +
                "    <p>\n" +
                "      Saludos, <br />\n" +
                "      <em>Estudios Gestion Ciudadania</em>\n" +
                "    </p>\n" +
                "  </body>\n" +
                "</html>";*/


        String contenido =
                emailDTO.getMensaje() +
             "\n "+
                        "\n "+
                "Saludos, \n"+
                "Estudios Gestion Ciudadania\n";



        //MimeMultipart multiParte = new MimeMultipart();
      //  msg.setDataHandler(new DataHandler(new FileDataSource("D:/facturaClaro.pdf")));
       // msg.setFileName("facturaClaro.pdf");

       // msg.setContent(contenido, "text/html");

        //msg.setContent(contenido, "text/plain");

        msg.setSentDate(new Date());


       // msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        //messageBodyPart.setContent("Tutorials point email", "text/html");
        messageBodyPart.setContent(contenido, "text/plain");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        //attachPart.attachFile("D:/facturaClaro.pdf");
        logger.info("ESTOY EN sendmail 2");

        if (archivos!=null) {
            for (MultipartFile var : archivos) {
                logger.info("ESTOY EN sendmail " + var.getOriginalFilename());
                File file = multipartToFile(var, var.getOriginalFilename());
                logger.info("ESTOY EN sendmail 3");
                MimeBodyPart attachPart = new MimeBodyPart();
                attachPart.attachFile(file);
                multipart.addBodyPart(attachPart);
            }
        }


        //File file =multipartToFile(archivos,archivos.getOriginalFilename());
        logger.info("ESTOY EN sendmail 3");
        //attachPart.attachFile(file);

        logger.info("ESTOY EN sendmail 4");
        //multipart.addBodyPart(attachPart);
        logger.info("ESTOY EN sendmail 5");
        msg.setContent(multipart);

        logger.info("ESTOY EN sendmail 6");


        Transport.send(msg);
    }
}