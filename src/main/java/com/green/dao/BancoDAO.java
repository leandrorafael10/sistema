/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Banco;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("bancoDAO")
public class BancoDAO extends AbstractDao<Banco, Integer> {

    @Autowired
    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Banco carregar(Integer k) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Banco.class);
        Criterion filtro = Restrictions.eq("iDBanco", k);
        return (Banco) criteria.add(filtro).uniqueResult();
    }

    @Override
    public Integer salvar(Banco obj) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Banco.class);
        criteria.setProjection(Projections.max("iDBanco"));
        Integer codigo = (Integer) criteria.uniqueResult();
        getSf().getCurrentSession().save(obj);
        if (codigo == null) {
            return 1;
        } else {
            return codigo + 1;
        }
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Banco> listar() {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Banco.class);
        return criteria.list();
    }

    @Override
    public void excluir(Banco obj) {
        getSf().getCurrentSession().delete(obj);
        getSf().getCurrentSession().flush();
    }
}
