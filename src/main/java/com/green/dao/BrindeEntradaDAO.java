/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.modelo.Brinde;
import com.green.modelo.BrindeEntrada;

/**
 *
 * @author leandro.silva
 */
@Repository("brindeEntradaDAO")
public class BrindeEntradaDAO {

    @Autowired
    private SessionFactory sf;

    public void salvar(BrindeEntrada brindeEntrada) {
        getSf().getCurrentSession().merge(brindeEntrada);
    }

    @SuppressWarnings("unchecked")
	public List<BrindeEntrada> listar() {
        return (List<BrindeEntrada>) getSf().getCurrentSession().createQuery("from com.green.modelo.BrindeEntrada").list();
    }

    public void atualizar(BrindeEntrada brindeEntrada) {
        getSf().getCurrentSession().update(brindeEntrada);
    }

    @SuppressWarnings("unchecked")
	public List<BrindeEntrada> listarPorBrinde(Brinde brinde) {
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.BrindeEntrada b "
                + "where b.iDBrinde = :brinde order by b.iDBrinde desc").setParameter("brinde", brinde);
        return (List<BrindeEntrada>) query.list();
    }

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

}
