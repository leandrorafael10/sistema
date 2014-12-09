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

import com.green.modelo.Pessoa;
import com.green.modelo.Praca;

/**
 *
 * @author leandro.silva
 */
@Repository("pracaDao")
@SuppressWarnings("unchecked")
public class PracaDAO extends AbstractDao<Praca, Integer> {

    @Autowired
    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    public Praca carregarPorNome(String nome) {
        Criterion filtro = Restrictions.eq("descricao", nome);
        Criteria criteria = getSf().getCurrentSession().createCriteria(Praca.class);
        criteria.add(filtro);
        return (Praca) criteria.uniqueResult();
    }

    @Override
    public Praca carregar(Integer k) {
        return (Praca) getSf().getCurrentSession().get(Praca.class, k);
    }

    @Override
    public Integer salvar(Praca obj) {
        getSf().getCurrentSession().save(obj);
        return 1;
    }

    
	@Override
    public List<Praca> listar() {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Praca.class);
        return criteria.list();
    }

    
    public List<Praca> listarPorPessoa(Pessoa p) {
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Praca p where p.iDPessoa = :p").setParameter("p", p);
        return (List<Praca>) query.list();
    }

    

    @Override
    public void excluir(Praca obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
