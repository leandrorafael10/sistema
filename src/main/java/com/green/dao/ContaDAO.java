/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Conta;
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
@Repository
public class ContaDAO extends AbstractDao<Conta, Integer> {

    @Autowired
    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Conta carregar(Integer k) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Conta.class);
        Criterion filtro = Restrictions.eq("iDConta", k);
        Conta c = (Conta) criteria.add(filtro).uniqueResult();
        return (Conta) criteria.add(filtro).uniqueResult();
    }

    @Override
    public Integer salvar(Conta obj) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Conta.class);
        criteria.setProjection(Projections.max("iDConta"));
        Integer codigo = (Integer) criteria.uniqueResult();
        getSf().getCurrentSession().save(obj);
        if (codigo == null) {
            return 1;
        } else {
            return codigo+1;
        }
    }

    @Override
    public List<Conta> listar() {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Conta.class);
        return criteria.list();
    }

    @Override
    public void excluir(Conta obj) {
        getSf().getCurrentSession().delete(obj);
        getSf().getCurrentSession().flush();
    }
    public void atializar(Conta conta){
        getSf().getCurrentSession().update(conta);
        getSf().getCurrentSession().flush();
    }
    public Conta carregarNumeroConta(Conta conta){
        Criteria criteria = getSf().getCurrentSession().createCriteria(Conta.class);
        Criterion filtroNumero = Restrictions.eq("numero", conta.getNumero());
        Criterion filtroDigito = Restrictions.eq("numeroDigito", conta.getNumeroDigito());
        Criterion filtroagencia = Restrictions.eq("agencia", conta.getAgencia());
        Criterion agenciaDigito = Restrictions.eq("agenciaDigito", conta.getAgenciaDigito());
        Criterion filtrotitular = Restrictions.eq("titular", conta.getTitular());
        criteria.add(filtroNumero).add(filtroDigito).add(filtroagencia).add(agenciaDigito).add(filtrotitular);
        return (Conta) criteria.uniqueResult();
    }
}
