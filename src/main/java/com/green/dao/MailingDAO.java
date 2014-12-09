/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.modelo.Funcao;
import com.green.modelo.Funcionario;
import com.green.modelo.Mailing;

/**
 *
 * @author leandro.silva
 */
@Repository("mailingDAO")
@SuppressWarnings("unchecked")
public class MailingDAO {

    @Autowired
    private SessionFactory sf;

    
	public List<Mailing> listar() {
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Mailing");
        return (List<Mailing>) query.list();
    }

    public void salvar(Mailing mailing) {
        getSf().getCurrentSession().save(mailing);
    }

    public void atualizar(Mailing mailing) {
        getSf().getCurrentSession().merge(mailing);
    }

    public List<Mailing> listarPorVendedor(Funcionario funcionario) {
        Query query;
        if (funcionario != null) {
            query = getSf().getCurrentSession().createQuery("from com.green.modelo.Mailing m where m.iDFuncionario = :func").
                    setParameter("func", funcionario);
        } else {
            query = getSf().getCurrentSession().createQuery("from com.green.modelo.Mailing m");
        }
        return (List<Mailing>) query.list();
    }

    public List<Funcionario> listaPorFuncao(Funcao func) {
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Funcionario m where m.iDFuncionario.iDFuncao = :func and m.ativo = true").
                setParameter("func", func);
        return (List<Funcionario>) query.list();
    }

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    public Mailing buscar(Integer id) {
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Mailing m where m.iDMailing = :id").setInteger("id", id);
        return (Mailing) query.uniqueResult();
    }
}
