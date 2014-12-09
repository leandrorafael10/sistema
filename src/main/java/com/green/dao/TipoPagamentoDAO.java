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

import com.green.modelo.Tipopagamento;

/**
 *
 * @author leandro.silva
 */
@Repository("tipoPagamentoDAO")
@SuppressWarnings("unchecked")
public class TipoPagamentoDAO extends AbstractDao<Tipopagamento, Integer> {
    @Autowired
    SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    @Override
    public Tipopagamento carregar(Integer k) {
        Criterion filtro = Restrictions.eq("iDTipoPagamento", k);
        Criteria criteria = getSf().getCurrentSession().createCriteria(Tipopagamento.class);
        criteria.add(filtro);
        return (Tipopagamento)criteria.uniqueResult();
    }

    @Override
    public Integer salvar(Tipopagamento obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
	@Override
    public List<Tipopagamento> listar() {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Tipopagamento.class);
        return criteria.list();
    }
    public List<Tipopagamento> listarJornal(){
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Tipopagamento t where t.iDTipoPagamento in (1,8,9) ");
        return (List<Tipopagamento>)query.list();
    }

    @Override
    public void excluir(Tipopagamento obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
