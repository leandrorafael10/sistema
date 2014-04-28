/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Funcao;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("funcaoDAO")
public class FuncaoDAO extends AbstractDao<Funcao, Integer> {
   
    @Autowired
    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Funcao carregar(Integer k) {
        Criterion filtro = Restrictions.eq("iDFuncao", k);
        Criteria criteria = getSf().getCurrentSession().createCriteria(Funcao.class);
        criteria.add(filtro);
        return (Funcao)criteria.uniqueResult();
    }
    

    @Override
    public Integer salvar(Funcao obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Funcao> listar() {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Funcao.class);
        return criteria.list();
    }

    @Override
    public void excluir(Funcao obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
