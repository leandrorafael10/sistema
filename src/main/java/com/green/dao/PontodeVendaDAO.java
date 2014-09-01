/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Pontodevenda;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("pontodeVendaDAO")
public class PontodeVendaDAO extends AbstractDao<Pontodevenda, Integer> {

    @Autowired
    private SessionFactory sf;

    @Override
    public Pontodevenda carregar(Integer k) {
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Pontodevenda p where p.iDPontodeVenda = :id").setInteger("id", k);
        return (Pontodevenda) query.uniqueResult();
    }

    @Override
    public Integer salvar(Pontodevenda obj) {
        getSf().getCurrentSession().save(obj);
        return 1;
    }

    @Override
    public List<Pontodevenda> listar() {
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Pontodevenda");
        return (List<Pontodevenda>) query.list();
    }

    public List listarPeriodo(Date inicio, Date fim, int status) {
        String opcao;
        switch (status) {
            case 0:
                opcao = "= 0 ";
                break;
            case 1:
                opcao = "= 1 ";
                break;
            case 2:
                opcao = ">= 0";
                break;
            default:
                opcao = " >= 0";
                break;
        }

        Query query = getSf().getCurrentSession().createQuery(" select c.iDPontovenda.descricao , c.iDPontovenda.cidade ,"
                + " count(c.iDCapalotejornal) , sum(c.valor)"
                + " from com.green.modelo.Capalotejornal c"
                + " where c.dTVenda >= :inicio and c.dTVenda <= :fim and c.status " + opcao
                + " group by c.iDPontovenda order by sum(c.valor) desc").setDate("inicio", inicio).setDate("fim", fim);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }

    @Override
    public void excluir(Pontodevenda obj) {
        getSf().getCurrentSession().delete(obj);
    }

    public void atualizar(Pontodevenda pontodevenda) {
        getSf().getCurrentSession().update(pontodevenda);
    }

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
}
