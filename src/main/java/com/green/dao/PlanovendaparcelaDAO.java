/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Planovendaparcela;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("planovendaparcelaDAO")
public class PlanovendaparcelaDAO {
    
    @Autowired
    private SessionFactory sf;
    
    @SuppressWarnings("unchecked")
	public List<Planovendaparcela> listar(){
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Planovendaparcela pl");
        return (List<Planovendaparcela>)query.list();
    }
    public void salvar(Planovendaparcela p){
        getSf().getCurrentSession().save(p);
    }
    public  void atualizar(Planovendaparcela p){
        getSf().getCurrentSession().update(p);
    }
    public Planovendaparcela buscar(Integer id){
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Planovendaparcela pl where pl.iDPlanoVendaParcela = :id").setInteger("id", id);
        return (Planovendaparcela)query.uniqueResult();
    }

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
}
