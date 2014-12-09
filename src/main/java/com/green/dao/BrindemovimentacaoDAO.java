/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.modelo.Brinde;
import com.green.modelo.BrindeTermo;
import com.green.modelo.Brindemovimentacao;

/**
 *
 * @author leandro.silva
 */
@Repository("brindemovimentacaoDAO")
public class BrindemovimentacaoDAO {

    @Autowired
    private SessionFactory sf;

    public void salvar(Brindemovimentacao b) {
        getSf().getCurrentSession().save(b);
    }

    public void atualizar(Brindemovimentacao b) {
        getSf().getCurrentSession().merge(b);
    }

    public void excluir(Brindemovimentacao b) {
        getSf().getCurrentSession().delete(b);
    }

    @SuppressWarnings("unchecked")
	public List<Brindemovimentacao> listar() {
        return (List<Brindemovimentacao>) getSf().getCurrentSession().createQuery("from com.green.modelo.Brindemovimentacao ").list();
    }

    @SuppressWarnings("rawtypes")
	public List<BrindeTermo> somaBrindeMovimentacao(Brindemovimentacao b) {
        String sql = "select br.iDBrinde,  "
                +"sum(case  br.iDTermoResponsabilidade.entradaSaida when 0 then br.qtd when 1 then 0 end)"
                + "-sum( case br.iDTermoResponsabilidade.entradaSaida when 1 then br.qtd when 0 then 0 end) "
                + "from com.green.modelo.BrindeTermo br "
                + "  where br.iDTermoResponsabilidade.iDBrindemovimentacao = :b group by br.iDBrinde";
        Query query = getSf().getCurrentSession().createQuery(sql).setParameter("b", b);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List obj = query.list();
        List<BrindeTermo> somaBrindeTermo = new ArrayList<>();
        for (Object o : obj) {

            Map row = (Map) o;
            BrindeTermo bt = new BrindeTermo(null, (Brinde) row.get("0"), ((Long) row.get("1")).intValue());
            somaBrindeTermo.add(bt);
        }
        return somaBrindeTermo;

    }

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

}
