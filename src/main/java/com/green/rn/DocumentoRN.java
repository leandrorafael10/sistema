/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.DocumentoDAO;
import com.green.modelo.Documento;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leandro.silva
 */
@Service("documentoRN")
public class DocumentoRN {

    @Autowired
    private DocumentoDAO documentoDAO;

    public List<Documento> listar() {
        return getDocumentoDAO().listar();
    }

    public Documento carregar(Integer codigo) {
        return getDocumentoDAO().carregar(codigo);
    }

    public DocumentoDAO getDocumentoDAO() {
        return documentoDAO;
    }

    public void setDocumentoDAO(DocumentoDAO documentoDAO) {
        this.documentoDAO = documentoDAO;
    }
}
