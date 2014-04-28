/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.ContratoMidia;
import com.green.modelo.ContratoPracas;
import com.green.modelo.Praca;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("contratoPracasDAO")
public class ContratoPracasDAO {
    @Autowired
    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    public List<ContratoPracas> listarPorPracas(Praca praca){
        Criterion filtro = Restrictions.eq("iDpraca", praca);
        Criteria criteria = getSf().getCurrentSession().createCriteria(ContratoPracas.class);
        criteria.add(filtro);
        return criteria.list();
    }
    
    public void salvar(ContratoPracas contratoPracas){
            getSf().getCurrentSession().save(contratoPracas);
        
    }
    public List<ContratoPracas> listar(){
        Criteria criteria = getSf().getCurrentSession().createCriteria(ContratoPracas.class);
        return  criteria.list();
    }
    public  List<ContratoPracas> listarPorContrato(ContratoMidia cm){
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.ContratoPracas cp where cp.iDcontratomidia = :c").
                setParameter("c", cm);
        return (List<ContratoPracas>)query.list();
    }
}
