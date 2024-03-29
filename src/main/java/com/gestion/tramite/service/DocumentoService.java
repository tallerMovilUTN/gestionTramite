package com.gestion.tramite.service;

import com.gestion.tramite.entidad.Documento;

import java.util.List;

public interface DocumentoService {

     List<Documento> listAllDocumento();
     Documento getDocumento(Long id);
     Documento createDocumento(Documento a1);
     Documento updateDocumento(Documento a1);
      Documento deleteDocumento(Long id);
}
