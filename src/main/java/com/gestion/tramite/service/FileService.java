package com.gestion.tramite.service;


import com.gestion.tramite.entidad.Archivo;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface FileService {

    /*
    Metodo para crear la carpeta donde vamos a guardar los archivos
     */
    public void init();


    public Archivo save(Archivo a1);

    public String upload(MultipartFile file, Archivo a1);


    public List<Archivo> buscarFiles(Long idGestionTramite);


    public List<Archivo> listAllFiles();

    /*
    Metodo para guardar los archivos
     */
    public void save(MultipartFile file);

    /*
    Metodo para cargar un archivo
     */
    public Resource load(String filename);

    /*
    Metodo para borrar todos los archivos cada vez que se inicie el servidor
     */
    public void deleteAll();

    /*
    Metodo para Cargar todos los archivos
     */
    //public Stream<Path> loadAll();

    public Stream<Path> loadAll(String carpeta);

    /*
    Metodo para Borrar un archivo
     */
    public boolean deleteFile(Archivo archivo);
}