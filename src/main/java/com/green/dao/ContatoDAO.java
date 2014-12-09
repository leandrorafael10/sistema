/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.modelo.Contato;
import com.green.modelo.Pessoa;

/**
 *
 * @author leandro.silva
 */
@Repository("contatoDAO")
public class ContatoDAO extends AbstractDao<Contato, Integer> {

    @Autowired
    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Contato carregar(Integer k) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer salvar(Contato obj) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Contato.class);
        criteria.setProjection(Projections.max("iDContato"));
        Integer max = (Integer) criteria.uniqueResult();
        getSf().getCurrentSession().save(obj);
        if (max == null) {
            return 1;
        }
        return max + 1;
    }
    public void salvarContato(Contato c){
        getSf().getCurrentSession().save(c);
    }

    @Override
    public List<Contato> listar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void excluir(Contato obj) {
        getSf().getCurrentSession().delete(obj);
        getSf().getCurrentSession().flush();
    }
    @SuppressWarnings("unchecked")
	public List<Contato> contatoPessoa(Pessoa pessoa){
        Criterion filtro = Restrictions.eq("iDPessoa", pessoa);
        Criteria criteria = getSf().getCurrentSession().createCriteria(Contato.class);
        criteria.add(filtro);
        return criteria.list();
    }
    public void atualizar(Contato contato){
        getSf().getCurrentSession().update(contato);
        getSf().getCurrentSession().flush();
    }
    public Contato buscarPorPessoa(Pessoa pessoa){
        Criteria criteria = getSf().getCurrentSession().createCriteria(Contato.class);
        Criterion filtro = Restrictions.eq("iDPessoa", pessoa);
        return (Contato)criteria.add(filtro).uniqueResult();
        
    }
}
