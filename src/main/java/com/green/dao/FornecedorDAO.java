/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Fornecedor;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("fornecedorDAO")
public class FornecedorDAO {

    @Autowired
    private SessionFactory sf;

    public void salvar(Fornecedor fornecedor) {
        getSf().getCurrentSession().save(fornecedor);
    }

    public List<Fornecedor> listar() {
        return getSf().getCurrentSession().createCriteria(Fornecedor.class).list();

    }

    public List<Fornecedor> listarAgencias() {
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Fornecedor where idRamoAtividade.iDRamoAtividade = :id").setInteger("id", 9);
        return (List<Fornecedor>) query.list();
    }

    public Fornecedor buscar(Integer id) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Fornecedor.class);
        Criterion filtro = Restrictions.eq("iDFornecedor", id);
        criteria.add(filtro);
        return (Fornecedor) criteria.uniqueResult();
    }

    public void excluir(Fornecedor fornecedor) {
        getSf().getCurrentSession().delete(fornecedor);
    }

    public void alterar(Fornecedor fornecedor) {
        getSf().getCurrentSession().merge(fornecedor);
        getSf().getCurrentSession().flush();
    }

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
}
