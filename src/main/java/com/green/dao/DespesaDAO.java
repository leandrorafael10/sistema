/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.modelo.Despesa;

/**
 *
 * @author leandro.silva
 */
@Repository("despesaDAO")
@SuppressWarnings({"unchecked","deprecation"})
public class DespesaDAO {

    @Autowired
    private SessionFactory sf;

    
	public List<Despesa> listando() {
        return getSf().getCurrentSession().createCriteria(Despesa.class).list();
    }

    public void salvar(Despesa despesa) {
        getSf().getCurrentSession().merge(despesa);

    }

    public void atualizar(Despesa despesa) {
        getSf().getCurrentSession().merge(despesa);
    }

    public void excluir(Despesa despesa) {
        getSf().getCurrentSession().delete(despesa);
        
    }

    public Despesa buscar(Integer codigo) {
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Despesa r where r.iDDespesa = :id ").setInteger("id", codigo);
        return (Despesa) query.uniqueResult();
    }
    
    public List<Despesa> filtroPeriodoSituacao(Date inicio,Date fim,boolean pago){
    	Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Despesa d where d.dTVencimento >= :inicio and d.dTVencimento <= :fim and d.pago = :pago")
    			.setDate("inicio", inicio).setDate("fim", fim).setBoolean("pago", pago);
    	return (List<Despesa>)query.list();
    }

    
    public List<Despesa> despesasAvencer() {
        List<Despesa> despesas = new ArrayList<Despesa>();
        Calendar diaAtual = Calendar.getInstance();
        diaAtual.add(Calendar.DAY_OF_MONTH, -1);
        Date d = diaAtual.getTime();
        for (Despesa despesa : listando()) {
            if (despesa.getDTVencimento().after(d)) {
                despesas.add(despesa);
            }
        }
        return despesas;
    }

    public List<Despesa> despesasVencidas() {
        Date d = new Date();
        Query query = getSf().getCurrentSession().createQuery("From com.green.modelo.Despesa d where d.dTVencimento< :d  and d.pago = 0 order by d.dTVencimento desc")
                .setParameter("d", d);

        List<Despesa> despesas = query.list();

        return despesas;
    }
    

    public List<Despesa> despesasPagas() {
        Date d = new Date();
        Calendar d2 = Calendar.getInstance();
        Date dataMesAntes = new Date(d2.get(Calendar.YEAR) - 1900, d2.get(Calendar.MONTH), 1);
        Query query = getSf().getCurrentSession().createQuery("From com.green.modelo.Despesa d where d.dTVencimento<= :d and d.dTVencimento>=:d2 and d.pago = 1 order by d.dTVencimento desc")
                .setParameter("d", d).setParameter("d2", dataMesAntes);

        List<Despesa> despesas = query.list();

        return despesas;
    }

    public List<Despesa> despesasApagar() {
        Date d = new Date();
        Calendar d2 = Calendar.getInstance();
        Date dataMesAntes = new Date(d2.get(Calendar.YEAR) - 1900, d2.get(Calendar.MONTH), 1);
        Query query = getSf().getCurrentSession().createQuery("From com.green.modelo.Despesa d where d.dTVencimento<= :d and d.dTVencimento>=:d2 and d.pago = 0 order by d.dTVencimento desc")
                .setParameter("d", d).setParameter("d2", dataMesAntes);
        List<Despesa> despesas = query.list();

        return despesas;
    }

   

    public List<Despesa> pendentesPagamento() {
        Query query = getSf().getCurrentSession().createQuery("From com.green.modelo.Despesa d where d.pago = 0 and d.atzPg = 2 order by d.dTVencimento ");
        List<Despesa> despesas = query.list();
        return despesas;
    }

    //pagamento em fase de liberaçao gerando pdf

    public List<Despesa> liberacaoPagamento() {
        Query query = getSf().getCurrentSession().createQuery("From com.green.modelo.Despesa d where d.pago = 0 and d.atzPg = 1 order by d.IDConta,d.dTVencimento ");
        List<Despesa> despesas = query.list();
        return despesas;
    }

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
}
