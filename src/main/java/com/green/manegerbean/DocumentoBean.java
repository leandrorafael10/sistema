/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Documento;
import com.green.rn.DocumentoRN;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author leandro.silva
 */
@SuppressWarnings("serial")
@ManagedBean(name = "documentoBean")
@ViewScoped
public class DocumentoBean implements Serializable {

    @ManagedProperty("#{documentoRN}")
    private DocumentoRN documentoRN;
    private List<Documento> documentos;
    
    @PostConstruct
    private void init() {
        setDocumentos(getDocumentoRN().listar());
    }
    
    public DocumentoRN getDocumentoRN() {
        return documentoRN;
    }
    
    public void setDocumentoRN(DocumentoRN documentoRN) {
        this.documentoRN = documentoRN;
    }
    
    public List<Documento> getDocumentos() {
        return documentos;
    }
    
    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }
}
