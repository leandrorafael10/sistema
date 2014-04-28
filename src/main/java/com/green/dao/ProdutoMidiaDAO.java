/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.ProdutoMidia;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("produtoMidiaDAO")
public class ProdutoMidiaDAO extends AbstractDao<ProdutoMidia, Integer> {
    
    @Autowired
    SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public ProdutoMidia carregar(Integer k) {
        return (ProdutoMidia) getSf().getCurrentSession().get(ProdutoMidia.class, k);
    }

    @Override
    public Integer salvar(ProdutoMidia obj) {
        return (Integer)getSf().getCurrentSession().save(obj);
    }

    @Override
    public List<ProdutoMidia> listar() {
        Criteria criteria = getSf().getCurrentSession().createCriteria(ProdutoMidia.class);
        return criteria.list();
    }

    @Override
    public void excluir(ProdutoMidia obj) {
        getSf().getCurrentSession().delete(obj);
        getSf().getCurrentSession().flush();
    }
    
    
}
