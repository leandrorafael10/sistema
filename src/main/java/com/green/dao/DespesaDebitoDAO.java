/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Despesadebito;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("despesaDebitoDAO")
public class DespesaDebitoDAO {
    @Autowired
    private SessionFactory sf;

    public void salvar(Despesadebito despesadebito){
        despesadebito.setTipoPagamento(true);
        getSf().getCurrentSession().save(despesadebito);
        getSf().getCurrentSession().flush();
    }
    
    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
}
