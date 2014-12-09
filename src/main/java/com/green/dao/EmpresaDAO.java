/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Empresa;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("empresaDAO")
public class EmpresaDAO implements Crud<Empresa> {

    @Autowired
    private SessionFactory sf;

    @Override
    public void salvar(Empresa o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(Empresa o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(Empresa o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Empresa> listar() {
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Empresa");
        return query.list();
    }

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    public Empresa carregar(int parseInt) {
        Query query = getSf().getCurrentSession().createQuery(" from com.green.modelo.Empresa e where e.iDEmpresa = :id").setInteger("id", parseInt);
        return (Empresa)query.uniqueResult();
    }

}
