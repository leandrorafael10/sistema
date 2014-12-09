/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Documento;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
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
@Repository("documentoDAO")
public class DocumentoDAO extends AbstractDao<Documento, Integer> {

    @Autowired
    private SessionFactory sf;

    @Override
    public Documento carregar(Integer k) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Documento.class);
        Criterion filtro = Restrictions.eq("iDDocumento", k);
        return (Documento) criteria.add(filtro).uniqueResult();
    }

    @Override
    public Integer salvar(Documento obj) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Documento.class);
        criteria.setProjection(Projections.max("iDDocumento"));
        Integer codigo = (Integer) criteria.uniqueResult();
        getSf().getCurrentSession().save(obj);
        if (codigo == null) {
            return 1;
        }
        return codigo + 1;

    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Documento> listar() {
       Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Documento c where c.iDDocumento != :trans1 "
                ).setInteger("trans1", 17);
       return (List<Documento>)query.list();
    }

    @Override
    public void excluir(Documento obj) {
        getSf().getCurrentSession().delete(obj);
        getSf().getCurrentSession().flush();
    }

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
}
