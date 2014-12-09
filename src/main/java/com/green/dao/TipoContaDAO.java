/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.modelo.Tipoconta;

/**
 *
 * @author leandro.silva
 */
@Repository("tipoContaDAO")
public class TipoContaDAO extends AbstractDao<Tipoconta, Integer> {

    @Autowired
    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Tipoconta carregar(Integer k) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Tipoconta.class);
        Criterion filtro = Restrictions.eq("idtipoconta", k);
        return (Tipoconta) criteria.add(filtro).uniqueResult();
    }

    @Override
    public Integer salvar(Tipoconta obj) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Tipoconta.class);
        criteria.setProjection(Projections.max("idtipoconta"));
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
    public List<Tipoconta> listar() {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Tipoconta.class);
        return criteria.list();
    }

    @Override
    public void excluir(Tipoconta obj) {
        try {
            getSf().getCurrentSession().delete(obj);

        } catch (ConstraintViolationException exception ) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro " + exception.getSQLState() + ". Não foi possivel excluir por ter registros em outras operações!", "erro " + exception.getSQLState() + ". Não foi possivel excluir por ter registros em outras operações!"));
            System.out.println("nao foi pocivel excluir " + String.valueOf(exception.getMessage()));
            getSf().getCurrentSession().clear();
        }

    }

    public void atualizar(Tipoconta tipoconta) {
        getSf().getCurrentSession().update(tipoconta);
        getSf().getCurrentSession().flush();
    }

}
