/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Ramoatividade;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("ramoAtividadeDAO")
public class RamoAtividadeDAO {
    
    @Autowired
    private SessionFactory sf;

    @SuppressWarnings("unchecked")
	public List<Ramoatividade> listar(){
        return getSf().getCurrentSession().createCriteria(Ramoatividade.class).list();
    }
    public Ramoatividade buscar(Integer id){
        return (Ramoatividade)getSf().getCurrentSession().get(Ramoatividade.class, id);
    }
    
    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    
}
