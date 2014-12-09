/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.modelo.Ccusto;

/**
 *
 * @author leandro.silva
 */
@Repository("ccustoDAO")

public class CCustoDAO extends AbstractDao<Ccusto, Integer> {

    @Autowired
    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Ccusto carregar(Integer k) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Ccusto.class);
        Criterion filtro = Restrictions.eq("iDCCusto", k);
        return (Ccusto)criteria.add(filtro).uniqueResult();
    }

    @Override
    public Integer salvar(Ccusto obj) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Ccusto.class);
        criteria.setProjection(Projections.max("iDCCusto"));
        Integer codigo = (Integer) criteria.uniqueResult();
        getSf().getCurrentSession().save(obj);
        if (codigo == null) {
            return 1;
        } else {
            return codigo+1;
        }
    }
    public Ccusto carregarCodigoEstrutural(String codigo){
        Criteria criteria = getSf().getCurrentSession().createCriteria(Ccusto.class);
        Criterion filtro = Restrictions.eq("codigo", codigo);
        return (Ccusto)criteria.add(filtro).uniqueResult();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Ccusto> listar() {
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Ccusto c where c.iDCCusto != :trans1 and c.iDCCusto"
                + " != :trans2").setInteger("trans1", new Integer("14")).setInteger("trans2",new Integer("15"));
        return (List<Ccusto>)query.list();
    }

    @Override
    public void excluir(Ccusto obj) {
        try{
            Query query = getSf().getCurrentSession().createQuery("delete Ccusto o" +    
                 " where o.iDCCusto = :iDCCusto")  
             .setParameter("iDCCusto",obj.getIDCCusto());  
            query.executeUpdate();
            
        }catch(ConstraintViolationException exception){
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"erro "+exception.getSQLState()+". Não foi possivel excluir por ter registros em outras operações!","erro "+exception.getSQLState()+". Não foi possivel excluir por ter registros em outras operações!"));
            System.out.println("não foi possível excluir "+String.valueOf(exception.getMessage()));
            getSf().getCurrentSession().clear();
        }finally{
            getSf().getCurrentSession().clear();
        }
    }
    public void atualizar (Ccusto ccusto){
        getSf().getCurrentSession().merge(ccusto);
    }
}
