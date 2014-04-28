/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.ProducaoMidia;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("producaoMidiaDAO")
public class ProducaoMidiaDAO {

    @Autowired
    private SessionFactory sf;

    public List<ProducaoMidia> listar() {
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.ProducaoMidia p where p.statusProducao != 3");
        return (List<ProducaoMidia>) query.list();
    }
    public List<ProducaoMidia> listarFinalizados() {
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.ProducaoMidia p where p.statusProducao = 3");
        return (List<ProducaoMidia>) query.list();
    }

    public void alterar(ProducaoMidia producaoMidia) {
        getSf().getCurrentSession().update(producaoMidia);
        getSf().getCurrentSession().flush();
    }

    public void salvar(ProducaoMidia producaoMidia) {
        getSf().getCurrentSession().save(producaoMidia);
    }

    public ProducaoMidia carregar(Integer id) {
        Query query = getSf().getCurrentSession().createQuery("from com.modelo.ProducaoMidia where iDProducaoMidia = :id").setParameter("id", id);
        return (ProducaoMidia) query.uniqueResult();
    }

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
}
