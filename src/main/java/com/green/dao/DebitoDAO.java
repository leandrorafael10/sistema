/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.modelo.Conta;
import com.green.modelo.Debito;

/**
 *
 * @author leandro.silva
 */
@Repository("debitoDAO")
@SuppressWarnings("unchecked")
public class DebitoDAO {

    @Autowired
    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    
	public List<Debito> listando() {
        List<Debito> debitos;
        debitos = getSf().getCurrentSession().createCriteria(Debito.class).list();
        return debitos;
    }

    public void salvar(Debito debito) {
        getSf().getCurrentSession().save(debito);
    }

    public Debito carregar(Integer codigo) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Debito.class);
        criteria.add(Restrictions.eq("iDebito", codigo));
        return (Debito) criteria.uniqueResult();
    }

    public void atualizar(Debito debito) {
        getSf().getCurrentSession().merge(debito);
        getSf().getCurrentSession().flush();
    }

    public Debito ultimoConciliado(Conta conta) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Debito.class);
        criteria.add(Restrictions.eq("iDConta", conta));
        criteria.add(Restrictions.isNotNull("dTConciliacao"));
        List<Debito> debitosOrdem = criteria.addOrder(Order.asc("dTConciliacao")).list();
        Integer max = debitosOrdem.size();
        if (max != 0) {
            return debitosOrdem.get(max - 1);
        }
        return null;
    }
}
