/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.modelo.CapaLote;

/**
 *
 * @author leandro.silva
 */
@Repository("capaLoteDAO")
@SuppressWarnings({"unchecked","deprecation"})
public class CapaLoteDAO {
    @Autowired
    private SessionFactory sf;

    
	public List<CapaLote> listar(){
        return getSf().getCurrentSession().createCriteria(CapaLote.class).list();
    }
    public void salvar(CapaLote capaLote){
        getSf().getCurrentSession().merge(capaLote);
    }
    public void atualizar(CapaLote capaLote){
        getSf().getCurrentSession().update(capaLote);
    }
    public void excluir(CapaLote capaLote){
        getSf().getCurrentSession().delete(capaLote);
    }
    public SessionFactory getSf() {
        return sf;
    }
    public CapaLote carregar(Integer id){
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.CapaLote c where c.idcapaLote = :id").setInteger("id", id);
        return (CapaLote)query.uniqueResult();
    } 
    public CapaLote buscaPorCodigo(Integer codigo){
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.CapaLote c where c.numeroContrato = :cod").
                setInteger("cod", codigo);
        return (CapaLote) query.uniqueResult();
    }
    
	public List<CapaLote> listaDoDia(){
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.set(date.getYear()+1900, date.getMonth(), date.getDate());
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.CapaLote c where c.dtinc = :dia").setDate("dia",date);
        return (List<CapaLote>)query.list();
    }
    public List<CapaLote> listaCanceladosMes(Date dataInicio,Date dataFim){
        Calendar calendar = Calendar.getInstance();
        if(dataInicio==null){
            dataInicio = new Date(calendar.get(Calendar.YEAR) - 1900, calendar.get(Calendar.MONTH), 1);
        }
        if(dataFim ==null){
            dataFim = calendar.getTime();
        }
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.CapaLote c where c.dtCancel >= :dtinicio and c.dtCancel<= :dtfim"
                + " and c.ativo=:status").
                setDate("dtinicio",dataInicio).setDate("dtfim", dataFim).setBoolean("status", false);
        return (List<CapaLote>) query.list();
    }
    
    public List<CapaLote> listaDeVendasTresMeses(Date dataInicio,Date dataFim){
        Calendar calendar = Calendar.getInstance();
        if(dataInicio==null){
            dataInicio = new Date(calendar.get(Calendar.YEAR) - 1900, calendar.get(Calendar.MONTH)-3, 1);
        }
        if(dataFim ==null){
            dataFim = calendar.getTime();
        }
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.CapaLote c where c.dtCancel >= :dtinicio and c.dtCancel<= :dtfim"
                + " or c.dtvenda>=:dtinicio and c.dtvenda<=:dtfim").
                setDate("dtinicio",dataInicio).setDate("dtfim", dataFim);
        return (List<CapaLote>) query.list();
    }
    
    
    public List<CapaLote> listaDeVendas(Date dataInicio,Date dataFim){
        Calendar calendar = Calendar.getInstance();
        if(dataInicio==null){
            dataInicio = new Date(calendar.get(Calendar.YEAR) - 1900, calendar.get(Calendar.MONTH), 1);
        }
        if(dataFim ==null){
            dataFim = calendar.getTime();
        }
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.CapaLote c where c.dtCancel >= :dtinicio and c.dtCancel<= :dtfim"
                + " or c.dtvenda>=:dtinicio and c.dtvenda<=:dtfim").
                setDate("dtinicio",dataInicio).setDate("dtfim", dataFim);
        return (List<CapaLote>) query.list();
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    
    
}
