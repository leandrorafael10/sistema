/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.modelo.Funcao;
import com.green.modelo.Terceiros;

/**
 *
 * @author leandro.silva
 */
@Repository("terceirosDAO")
@SuppressWarnings("unchecked")
public class TerceirosDAO {
    @Autowired
    private SessionFactory sf;

    
	public List<Terceiros> listar(){
        return getSf().getCurrentSession().createCriteria(Terceiros.class).list();
    }
    
    public List<Terceiros> listarPorFuncao(Funcao funcao){
        Criterion criterion = Restrictions.eq("iDfuncao", funcao);
        Criteria criteria = getSf().getCurrentSession().createCriteria(Terceiros.class);
        criteria.add(criterion);
        return criteria.list();
    }
    public List<Terceiros> listarPorEquipe(Terceiros t){
        Criterion criterion = Restrictions.eq("iDTerceirosGerente", t);
        Criterion filtro = Restrictions.eq("ativo", true);
        Criteria criteria = getSf().getCurrentSession().createCriteria(Terceiros.class);
        criteria.add(criterion).add(filtro);
        
        return criteria.list();
    }
    public Terceiros carregar(Integer id){
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Terceiros t where t.iDTerceiros = :id").setInteger("id", id);
        return (Terceiros)query.uniqueResult();
    }
    public void salvar(Terceiros terceiros){
        getSf().getCurrentSession().merge(terceiros);
    }
    public void atualizar(Terceiros terceiros){
        getSf().getCurrentSession().update(terceiros);
    }
        
    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    
}
