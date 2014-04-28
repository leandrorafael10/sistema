/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.LogMovimentacao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("logMovimentacaoDAO")
public class LogMovimentacaoDAO {
    
    @Autowired
    private SessionFactory sf;
    
    public void salvar(LogMovimentacao logMovimentacao){
       getSf().getCurrentSession().save(logMovimentacao);
    }

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    
    
}
