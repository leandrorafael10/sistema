/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Classificacao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@Repository("classificacaoDAO")
public class ClassificacaoDAO extends AbstractDao<Classificacao, Integer> {

    @Autowired
    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Classificacao carregar(Integer k) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Classificacao.class);
        Criterion filtro = Restrictions.eq("iDClassificacao", k);
        return (Classificacao) criteria.add(filtro).uniqueResult();
    }

    public Classificacao carregarCodigoEstrututal(String codigo) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Classificacao.class);
        Criterion filtro = Restrictions.eq("codigo", codigo);
        return (Classificacao) criteria.add(filtro).uniqueResult();
    }

    @Override
    public Integer salvar(Classificacao obj) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Classificacao.class);
        criteria.setProjection(Projections.max("iDClassificacao"));
        Integer codigo = (Integer) criteria.uniqueResult();
        getSf().getCurrentSession().save(obj);
        if (codigo == null) {
            return 1;
        } else {
            return codigo + 1;
        }
    }

    @Override
    public List<Classificacao> listar() {
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Classificacao c "
                + "where c.iDClassificacao != :trans1 ").setInteger("trans1", 137);
        return (List<Classificacao>) query.list();
    }

    @Override
    public void excluir(Classificacao obj) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            Query query = getSf().getCurrentSession().createQuery("delete  Classificacao o"
                    + " where o.iDClassificacao = :iDClassificacao").setParameter("iDClassificacao", obj.getIDClassificacao());
            query.executeUpdate();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok! Excluido com sucesso!", "Ok! Excluido com sucesso!"));
        } catch (ConstraintViolationException exception) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro " + exception.getSQLState() + ". Não foi possivel excluir por ter registros em outras operações!", "erro " + exception.getSQLState() + ". Não foi possivel excluir por ter registros em outras operações!"));
            System.out.println("nao foi pocivel excluir " + String.valueOf(exception.getMessage()));
            getSf().getCurrentSession().clear();
        } finally {
            getSf().getCurrentSession().clear();
        }
        context.update("formPlanoContas");
    }

    public List<Classificacao> filtroClassificacao(String codigo, String descricao) {

        String sql = "from com.green.modelo.Classificacao c ";
        Map<String, Object> params = new HashMap<>();
        if (!descricao.equals("") || !codigo.equals("")) {
            if (!codigo.equals("")) {
                sql = sql + " where ";
                params.put("codigo", codigo + "%");
                sql = sql + " codigo LIKE :codigo";
            }
            if (!descricao.equals("") && codigo.equals("")) {
                params.put("descricao", descricao+"%");
                sql = sql + " where ";
                sql = sql + " descricao LIKE :descricao ";
            } 
            if(!descricao.equals("") &&!codigo.equals("")) {
                params.put("descricao", descricao+"%");
                sql = sql + " and descricao LIKE :descricao ";
            }

            /* Criteria criteria = getSf().getCurrentSession().createCriteria(Classificacao.class);
             if (!codigo.equals("")) {
             criteria.add(Restrictions.like("codigo", codigo + "%"));
             }
             if (!descricao.equals("")) {
             criteria.add(Restrictions.like("descricao", descricao + "%"));
             }
             return criteria.list();*/
            Query query = getSf().getCurrentSession().createQuery(sql);
            query.setProperties(params);
            return (List<Classificacao>) query.list();

        } else {
            Query query = getSf().getCurrentSession().createQuery(sql);

            return (List<Classificacao>) query.list();


        }

    }

    public void atualizar(Classificacao classificacao) {
        getSf().getCurrentSession().update(classificacao);
        getSf().getCurrentSession().flush();
    }
}
