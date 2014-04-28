/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Conta;
import com.green.modelo.Debito;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("debitoDAO")
public class DebitoDAO {

    @Autowired
    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    public List<Debito> listando() {
        List<Debito> debitos;
        debitos = getSf().getCurrentSession().createCriteria(Debito.class).list();
        return debitos;
    }

    public void salvar(Debito debito) {
        getSf().getCurrentSession().save(debito);
    }

    public Debito carregar(Integer codigo) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Debito.class);
        criteria.add(Restrictions.eq("iDebito", codigo));
        return (Debito) criteria.uniqueResult();
    }

    public List<Debito> filtroDebito(Debito debito, Date fimBaixa, Date fimConciliacao) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Debito.class);
        if (debito.getIDConta() != null) {
            criteria.add(Restrictions.eq("iDConta", debito.getIDConta()));
        }
        if (debito.getIDTpDocumento() != null) {
            criteria.add(Restrictions.eq("iDTpDocumento", debito.getIDTpDocumento()));
        }
        if (debito.getIDAtividade() != null) {
            criteria.add(Restrictions.eq("iDAtividade", debito.getIDAtividade()));
        }
        if (debito.getIDClassificacao() != null) {
            criteria.add(Restrictions.eq("iDClassificacao", debito.getIDClassificacao()));
        }
        if (debito.getIDCCusto() != null) {
            criteria.add(Restrictions.eq("iDCCusto", debito.getIDCCusto()));
        }
        if (debito.getDTBaixa() != null && fimBaixa != null) {
            criteria.add(Restrictions.between("dTBaixa", debito.getDTBaixa(), fimBaixa));
        } else if (debito.getDTBaixa() != null && fimBaixa == null) {
            criteria.add(Restrictions.between("dTBaixa", debito.getDTBaixa(), new Date(999, 12, 30)));
        } else if (debito.getDTBaixa() == null && fimBaixa != null) {
            criteria.add(Restrictions.between("dTBaixa", new Date(000, 1, 1), fimBaixa));
        }
        if (debito.getDTConciliacao() != null && fimConciliacao != null) {
            criteria.add(Restrictions.between("dTConciliacao", debito.getDTConciliacao(), fimConciliacao));
        } else if (debito.getDTConciliacao() != null && fimConciliacao == null) {
            criteria.add(Restrictions.between("dTConciliacao", debito.getDTConciliacao(), new Date(999, 12, 30)));
        } else if (debito.getDTConciliacao() == null && fimConciliacao != null) {
            criteria.add(Restrictions.between("dTConciliacao", new Date(000, 1, 1), fimConciliacao));
        }

        return criteria.list();
    }

    public void atualizar(Debito debito) {
        getSf().getCurrentSession().merge(debito);
        getSf().getCurrentSession().flush();
    }

    public Debito ultimoConciliado(Conta conta) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Debito.class);
        criteria.add(Restrictions.eq("iDConta", conta));
        criteria.add(Restrictions.isNotNull("dTConciliacao"));
        List<Debito> debitosOrdem = criteria.addOrder(Order.asc("dTConciliacao")).list();
        Integer max = debitosOrdem.size();
        if (max != 0) {
            return debitosOrdem.get(max - 1);
        }
        return null;
    }
}
