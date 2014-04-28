/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Obsmailing;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("obsMailingDAO")
public class ObsMailingDAO {
    
    @Autowired
    private SessionFactory sf;

    public void atualizar(Obsmailing obsmailing){
        getSf().getCurrentSession().update(obsmailing);
    }
    public void salvar(Obsmailing obsmailing){
        getSf().getCurrentSession().save(obsmailing);
        getSf().getCurrentSession().flush();
    }
    
    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    
    
}
