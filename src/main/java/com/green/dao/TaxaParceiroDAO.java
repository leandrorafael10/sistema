/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.TaxaParceiro;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("taxaParceiroDAO")
public class TaxaParceiroDAO {
    
    @Autowired
    private SessionFactory sf;

    public void salvar(TaxaParceiro tp){
        getSf().getCurrentSession().save(tp);
    }
    @SuppressWarnings("unchecked")
	public List<TaxaParceiro> listar(){
        return  (List<TaxaParceiro>)getSf().getCurrentSession().createCriteria(TaxaParceiro.class).list();
    }
    
    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    
}
