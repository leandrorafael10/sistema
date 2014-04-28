/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Histaltlote;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("histaltloteDAO")
public class HistaltloteDAO {
    
    @Autowired
    private SessionFactory  sf;

    public void salvar (Histaltlote histaltlote){
        getSf().getCurrentSession().save(histaltlote);
    }
    
    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    
    
}
