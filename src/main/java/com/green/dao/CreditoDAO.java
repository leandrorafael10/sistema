/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.modelo.Conta;
import com.green.modelo.Credito;

/**
 *
 * @author leandro.silva
 */
@Repository("creditoDAO")
public class CreditoDAO {

    @Autowired
    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    @SuppressWarnings("unchecked")
	public List<Credito> listando() {
        List<Credito> creditos;
        creditos = getSf().getCurrentSession().createCriteria(Credito.class).list();
        return creditos;
    }

    public void salvar(Credito credito) {
        getSf().getCurrentSession().save(credito);

    }
    /*
     public List<Credito> filtroCredito(Credito credito, Date fimBaixa, Date fimConciliacao) {
     Criteria criteria = getSf().getCurrentSession().createCriteria(Credito.class);
     if (credito.getIDConta() != null) {
     criteria.add(Restrictions.eq("iDConta", credito.getIDConta()));
     }
     if (credito.getIDTpDocumento() != null) {
     criteria.add(Restrictions.eq("iDTpDocumento", credito.getIDTpDocumento()));
     }
     if (credito.getIDAtividade() != null) {
     criteria.add(Restrictions.eq("iDAtividade", credito.getIDAtividade()));
     }
     if (credito.getIDClassificacao() != null) {
     criteria.add(Restrictions.eq("iDClassificacao", credito.getIDClassificacao()));
     }
     if (credito.getIDCCusto() != null) {
     criteria.add(Restrictions.eq("iDCCusto", credito.getIDCCusto()));
     }
     if (credito.getDTBaixa() != null && fimBaixa != null) {
     criteria.add(Restrictions.between("dTBaixa", credito.getDTBaixa(), fimBaixa));
     } else if (credito.getDTBaixa() != null && fimBaixa == null) {
     criteria.add(Restrictions.between("dTBaixa", credito.getDTBaixa(), new Date(999, 12, 30)));
     } else if (credito.getDTBaixa() == null && fimBaixa != null) {
     criteria.add(Restrictions.between("dTBaixa", new Date(000, 1, 1), fimBaixa));
     }
     if (credito.getDTConciliacao() != null && fimConciliacao != null) {
     criteria.add(Restrictions.between("dTConciliacao", credito.getDTConciliacao(), fimConciliacao));
     } else if (credito.getDTConciliacao() != null && fimConciliacao == null) {
     criteria.add(Restrictions.between("dTConciliacao", credito.getDTConciliacao(), new Date(999, 12, 30)));
     } else if (credito.getDTConciliacao() == null && fimConciliacao != null) {
     criteria.add(Restrictions.between("dTConciliacao", new Date(000, 1, 1), fimConciliacao));
     }

     return criteria.list();
     }
     */

    public void atualizar(Credito credito) {
        getSf().getCurrentSession().merge(credito);
        getSf().getCurrentSession().flush();
    }

    @SuppressWarnings("unchecked")
	public Credito ultimoConciliado(Conta conta) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Credito.class);
        criteria.add(Restrictions.eq("iDConta", conta));
        criteria.add(Restrictions.isNotNull("dTConciliacao"));
        List<Credito> creditosOrdem = criteria.addOrder(Order.asc("dTConciliacao")).list();
        Integer max = creditosOrdem.size();
        if (max != 0) {
            return creditosOrdem.get(max - 1);
        }
        return null;
    }

    public Credito carregar(Integer codigo) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Credito.class);
        return (Credito) criteria.add(Restrictions.eq("iDCredito", codigo)).uniqueResult();
    }
}
