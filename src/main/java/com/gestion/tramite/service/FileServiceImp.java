package com.gestion.tramite.service;

import com.gestion.tramite.entidad.Archivo;
import com.gestion.tramite.repositorio.FileRepositorio;
import com.gestion.tramite.util.Util;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FileServiceImp implements FileService {

    Logger logger = LoggerFactory.getLogger(FileServiceImp.class);
    //Nombre de la carpeta donde vamos a almacenar los archivos
    //Se crea a nivel de raiz la carpeta
    Path root = Paths.get("C:/IntelliJ/gestionTramiteDocumentos");

    private final FileRepositorio repo;
    //private final GestionTramiteRepositorio repo1;

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("No se puede inicializar la carpeta uploads");
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            //copy (que queremos copiar, a donde queremos copiar)
            Files.copy(file.getInputStream(),
                    this.root.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            throw new RuntimeException("No se puede guardar el archivo. Error " + e.getMessage());
        }
    }



    @Override
    public Archivo save(Archivo a1)
    {
          return repo.save(a1);
    }






    @Override
    public String upload(MultipartFile file, Archivo a1)
    {
        String result = "";
        String localPath=null,nomFileFinal=null;
        try {
            //copy (que queremos copiar, a donde queremos copiar)
            //root = Paths.get(path);
            logger.info("ESTOY EN save FILE::: "+file.getName()+"-DNI: "+a1.getDni());
            a1.setEstado(1);
            a1.setFechaAlta(new Date());


            localPath=root+"/"+a1.getDni();
            //root = Paths.get(localPath);
            Path auxPath = Paths.get(localPath);
            logger.info("localPath:: "+localPath);
            logger.info("root:: "+auxPath.toString());

            File direc= new File(localPath);
            if (!direc.exists())
            {
                if (direc.mkdirs())
                {
                    System.out.println("SE CREO EL DIRECTOTIIO");
                }
            }

            Date fechaActual=new Date();
            System.out.println(Util.getFixedString(fechaActual, "yyyyMMdd-HHmmss"));

            nomFileFinal= a1.getDni()+"_"+Util.getFixedString(fechaActual, "yyyyMMdd_HHmmss")+Util.getFileExtension(file.getOriginalFilename());
            logger.info("nomFileFinal:: "+nomFileFinal);



            Files.copy(file.getInputStream(), auxPath.resolve(nomFileFinal));

        }
        catch (IOException e)
        {
            //throw new RuntimeException("No se puede guardar el archivo. Error " + e.getMessage());
            e.printStackTrace();


        }


        return nomFileFinal;
    }




    @Override
    public Resource load(String filename) {
        try {
            String[] aux= filename.split("_");

            logger.info("ESTOY EN load: "+filename);
            Path auxPath = Paths.get(root+"/"+aux[0]);
            logger.info("auxPath:: "+auxPath);


            Path file = auxPath.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new RuntimeException("No se puede leer el archivo ");
            }

        }catch (MalformedURLException e){
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

  /**  @Override
    public Stream<Path> loadAll(){
        //Files.walk recorre nuestras carpetas (uploads) buscando los archivos
        // el 1 es la profundidad o nivel que queremos recorrer
        // :: Referencias a metodos
        // Relativize sirve para crear una ruta relativa entre la ruta dada y esta ruta
        try{
            return Files.walk(this.root,1).filter(path -> !path.equals(this.root))
                    .map(this.root::relativize);
        }catch (RuntimeException | IOException e){
            throw new RuntimeException("No se pueden cargar los archivos ");
        }
    }**/



    @Override
    public Stream<Path> loadAll(String carpeta)
    {
        //Files.walk recorre nuestras carpetas (uploads) buscando los archivos
        // el 1 es la profundidad o nivel que queremos recorrer
        // :: Referencias a metodos
        // Relativize sirve para crear una ruta relativa entre la ruta dada y esta ruta
        try
        {
            logger.info("ESTOY EN BUSCAR ARCHIVOS DE CARPETA");
            Path auxPath = Paths.get(root+"/"+carpeta);
            logger.info("auxPath:: "+auxPath);
            return Files.walk(auxPath,1).filter(path -> !path.equals(auxPath)).map(auxPath::relativize);
        }
        catch (RuntimeException | IOException e)
        {
            throw new RuntimeException("No se pueden cargar los archivos ");
        }
    }


    @Override
    public List<Archivo> buscarFiles(Long idGestionTramite)
    {
        logger.info("ESTOY EN buscarFiles");
        List<Archivo> listFile = repo.getArchivosByIdGestionTramite(idGestionTramite);
        return listFile;
    }




    @Override
    public List<Archivo> listAllFiles()
    {
        logger.info("ESTOY EN buscarFiles");
        List<Archivo> listFile = repo.findAll();
        return listFile;
    }



    /**
     @Override
     public Stream<Path> loadAll(String carpeta)
     {
             //Files.walk recorre nuestras carpetas (uploads) buscando los archivos
             // el 1 es la profundidad o nivel que queremos recorrer
             // :: Referencias a metodos
             // Relativize sirve para crear una ruta relativa entre la ruta dada y esta ruta
             try
             {
             logger.info("ESTOY EN BUSCAR ARCHIVOS DE CARPETA");
             Path auxPath = Paths.get(root+"/"+carpeta);
             logger.info("auxPath:: "+auxPath);
             return Files.walk(auxPath,1).filter(path -> !path.equals(auxPath)).map(auxPath::relativize);
             }
             catch (RuntimeException | IOException e)
             {
             throw new RuntimeException("No se pueden cargar los archivos ");
             }
     }
     */


    @Override
    public boolean deleteFile(Archivo a1){

        boolean result= false;
        try {

            String[] aux= a1.getNombre().split("_");

            logger.info("ESTOY EN BORRAR ARCHIVO: "+a1.getNombre());
            Path auxPath = Paths.get(root+"/"+aux[0]);
            logger.info("auxPath:: "+auxPath);


            if (Files.deleteIfExists(auxPath.resolve(a1.getNombre())))
            {
                    repo.delete(a1);
                    result = true;
            }



        }catch (IOException e){
            e.printStackTrace();

        }
        return result;
    }


}