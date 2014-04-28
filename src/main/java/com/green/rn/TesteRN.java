/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.TesteDAO;
import com.green.modelo.Teste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leandro.silva
 */
@Service("testeRN")
public class TesteRN {
    
    @Autowired
    private TesteDAO testeDAO;

    public void salvar (Teste teste){
        testeDAO.salvar(teste);
    }
    public TesteDAO getTestefDAO() {
        return testeDAO;
    }

    public void setTestefDAO(TesteDAO testefDAO) {
        this.testeDAO = testefDAO;
    }
}
