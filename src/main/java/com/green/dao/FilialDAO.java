/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Filial;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("filialDAO")
public class FilialDAO {
    
    @Autowired
    private SessionFactory sf;

    public List<Filial> listar(){
        Query query=getSf().getCurrentSession().createQuery("from com.green.modelo.Filial ");
        return (List<Filial>)query.list();
    }
    
    public Filial buscar(Integer id){
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Filial l where l.iDFilial = :id").setInteger("id", id);
        return (Filial)query.uniqueResult();
    }
    
    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    
}
