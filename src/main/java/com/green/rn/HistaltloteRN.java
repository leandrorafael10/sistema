/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.HistaltloteDAO;
import com.green.modelo.Histaltlote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leandro.silva
 */
@Service("histaltloteRN")
public class HistaltloteRN {
    
    @Autowired
    private HistaltloteDAO histaltloteDAO;

    public void salvar(Histaltlote histaltlote){
        getHistaltloteDAO().salvar(histaltlote);
    }
    
    public HistaltloteDAO getHistaltloteDAO() {
        return histaltloteDAO;
    }

    public void setHistaltloteDAO(HistaltloteDAO histaltloteDAO) {
        this.histaltloteDAO = histaltloteDAO;
    }
    
    
    
}
