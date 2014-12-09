/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.green.dao;

import com.green.modelo.Boleto;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("boletoDAO")
public class BoletoDAO {
    
    @Autowired
    private SessionFactory sf;

    public Boleto buscarBoletoPadrao(){
        Query query= getSf().getCurrentSession().createQuery("from com.green.modelo.Boleto where ativo = true");
        return (Boleto)query.uniqueResult();
    }
    
    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    
}
