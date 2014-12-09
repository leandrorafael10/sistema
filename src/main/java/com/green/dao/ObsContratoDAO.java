/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.ContratoMidia;
import com.green.modelo.ObsContrato;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("obsContratoDAO")
public class ObsContratoDAO {

    @Autowired
    private SessionFactory sf;

    public void atualizar(ObsContrato obsContrato) {
        getSf().getCurrentSession().update(obsContrato);
    }

    public void salvar(ObsContrato obsContrato) {
        getSf().getCurrentSession().save(obsContrato);
        getSf().getCurrentSession().flush();
    }

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    @SuppressWarnings("unchecked")
	public List<ObsContrato> buscaPorContrato(ContratoMidia midia) {
        Query query =getSf().getCurrentSession().createQuery("from com.green.modelo.ObsContrato c where c.iDContratoMidia = :cm").setParameter("cm", midia);
        return (List<ObsContrato>)query.list();
    }
}
