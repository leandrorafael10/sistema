/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Atividade;
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
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("atividadeDAO")
public class AtividadeDAO extends AbstractDao<Atividade, Integer> {

    @Autowired
    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Atividade carregar(Integer k) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Atividade.class);
        Criterion filtro  = Restrictions.eq("iDAtividade", k);
        criteria.add(filtro);
        return (Atividade)criteria.uniqueResult();
    }

    @Override
    public Integer salvar(Atividade obj) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Atividade.class);
        criteria.setProjection(Projections.max("iDAtividade"));
        Integer codigo = (Integer) criteria.uniqueResult();
        getSf().getCurrentSession().save(obj);
        if (codigo == null) {
            return 1;
        } else {
            return codigo+1;
        }
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Atividade> listar() {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Atividade.class);
        return (List<Atividade>)criteria.list();
    }

    @Override
    public void excluir(Atividade atividade) {
        try{
            Query query = getSf().getCurrentSession().createQuery("delete Atividade o" +    
                 " where o.iDAtividade = :iDAtividade")  
             .setParameter("iDAtividade",atividade.getIDAtividade());  
            query.executeUpdate();
            
        }catch(ConstraintViolationException exception){
            FacesContext facesContext = FacesContext.getCurrentInstance();
            RequestContext context = RequestContext.getCurrentInstance();
            facesContext.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"erro "+exception.getSQLState()+". Não foi possivel excluir por ter registros em outras operações!","erro "+exception.getSQLState()+". Não foi possivel excluir por ter registros em outras operações!"));
            context.update("formAtividade:pgAtividade");
            System.out.println("nao foi pocivel excluir "+String.valueOf(exception.getMessage()));
            getSf().getCurrentSession().clear();
        }finally{
            getSf().getCurrentSession().clear();
        }
    }
    public void atualizar(Atividade atividade){
        getSf().getCurrentSession().update(atividade);
        getSf().getCurrentSession().flush();
    }
   
}
