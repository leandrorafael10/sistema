/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.modelo.Teste;

/**
 *
 * @author leandro.silva
 */
@Repository("testeDAO")
public class TesteDAO {
     @Autowired
    private SessionFactory sf ;
   
    
    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    public void salvar(Teste teste) {
        
        sf.getCurrentSession().save(teste);
       
    }
}
