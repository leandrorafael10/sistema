/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.green.dao;

import com.green.modelo.Brinde;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



/**
 *
 * @author leandro.silva
 */
@Repository
public class BrindeDAO {
    
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
	public List<Brinde> listar(){
        return getSessionFactory().getCurrentSession().createCriteria(Brinde.class).list();
    }
    public Brinde carregar(Integer id){
        Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Brinde.class);
        criteria.add(Restrictions.eq("iDBrinde", id));
        return (Brinde)criteria.uniqueResult();
    }
    public void salvar(Brinde brinde){
        getSessionFactory().getCurrentSession().merge(brinde);
    }
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void atualizar(Brinde b) {
        getSessionFactory().getCurrentSession().update(b);
    }
    
}
