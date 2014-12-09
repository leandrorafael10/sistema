/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Funcionario;
import com.green.modelo.Funcionariometa;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("funcionariometaDAO")
@SuppressWarnings("unchecked")
public class FuncionariometaDAO {

    @Autowired
    private SessionFactory sf;

    public void salvar(Funcionariometa funcionariometa) {
        getSf().getCurrentSession().save(funcionariometa);
    }

    
	public List<Funcionariometa> listarPorFuncionario(Funcionario funcionario) {
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Funcionariometa f where f.idfuncionario = :fun").setParameter("fun", funcionario);
        return (List<Funcionariometa>) query.list();
    }

    public List<Funcionariometa> listar() {
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Funcionariometa f ");
        return (List<Funcionariometa>) query.list();
    }
    public Funcionariometa ultimaAtualizacao(Funcionario f){
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Funcionariometa f where f.idfuncionario = :fun order by  f.idfuncionariometa desc limit 1").setParameter("fun", f);
        return (Funcionariometa) query.uniqueResult();
    }

    public Funcionariometa buscar(Integer id) {
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Funcionariometa f where f.iDFuncionariometa = :id").setParameter("id", id);
        return (Funcionariometa) query.uniqueResult();
    }

    public void atualizar(Funcionariometa funcionariometa) {
        getSf().getCurrentSession().merge(funcionariometa);
    }

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
}
