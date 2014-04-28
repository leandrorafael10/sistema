/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.ContratoPracasDAO;
import com.green.modelo.ContratoMidia;
import com.green.modelo.ContratoPracas;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leandro.silva
 */
@Service("contratoPracasRN")
public class ContratoPracasRN {

    @Autowired
    private ContratoPracasDAO contratoPracasDAO;

    public List<ContratoPracas> listar() {
        return getContratoPracasDAO().listar();
    }

    public ContratoPracasDAO getContratoPracasDAO() {
        return contratoPracasDAO;
    }

    public void setContratoPracasDAO(ContratoPracasDAO contratoPracasDAO) {
        this.contratoPracasDAO = contratoPracasDAO;
    }

    public List<ContratoPracas> listarPorPracas(ContratoMidia cm) {
        if (cm.getIdcontratoMidia() != null) {
            return getContratoPracasDAO().listarPorContrato(cm);
        }
        else{
            return null;
        }
    }
}
