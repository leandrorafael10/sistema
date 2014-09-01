/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.green.dao;

import com.green.modelo.Cliente;
import com.green.modelo.ContratoMidia;
import com.green.modelo.ContratoParceiro;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("contratoParceiroDAO")
public class ContratoParceiroDAO implements Crud<ContratoParceiro>{

    @Autowired
    private SessionFactory sf;
    
    @Override
    public void salvar(ContratoParceiro o) {
        getSf().getCurrentSession().save(o);
    }

    @Override
    public void excluir(ContratoParceiro o) {
        getSf().getCurrentSession().delete(o);
    }

    @Override
    public void atualizar(ContratoParceiro o) {
        getSf().getCurrentSession().update(o);
    }
    
    public List<ContratoParceiro> buscaPorPeriodoTipo_parceiro(Date inicio,Date fim,int tipo,Cliente c){
        String opcao = "";
        switch(tipo){
            case 0: opcao = "= 0 ";
                    break;
            case 1:opcao = "= 1 ";
                break;
            case 2: opcao = "> 0 ";
                break;
        }
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.ContratoParceiro c "
                + "where c.iDContratoMidia.dataFimContrato >= :inicio and c.iDContratoMidia.dataFimContrato <= :fim and c.iDContratoMidia.ativo "+opcao
        +" and c.iDContratoMidia.iDcliente.iDTipocliente is not null and c.iDContratoMidia.iDcliente = :cliente").setDate("inicio", inicio)
                .setDate("fim", fim).setParameter("cliente", c);
        return query.list();
    }

    @Override
    public List<ContratoParceiro> listar() {
        return getSf().getCurrentSession().createQuery("from com.green.modelo.ContratoParceiro").list();
    }

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    
}
