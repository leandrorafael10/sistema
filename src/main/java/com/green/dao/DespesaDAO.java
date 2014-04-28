/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Despesa;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("despesaDAO")
public class DespesaDAO {

    @Autowired
    private SessionFactory sf;

    public List<Despesa> listando() {
        return getSf().getCurrentSession().createCriteria(Despesa.class).list();
    }

    public void salvar(Despesa despesa) {
        getSf().getCurrentSession().merge(despesa);
        getSf().getCurrentSession().flush();
    }

    public void atualizar(Despesa despesa) {
        getSf().getCurrentSession().merge(despesa);
        getSf().getCurrentSession().flush();
    }

    public void excluir(Despesa despesa) {
        getSf().getCurrentSession().delete(despesa);
        getSf().getCurrentSession().flush();
    }

    public Despesa buscar(Integer codigo) {
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Despesa r where r.iDDespesa = :id ").setInteger("id",codigo);
        return (Despesa) query.uniqueResult();
    }

    public List<Despesa> filtro(Despesa despesaFiltro, Date fimVenc, int pag, BigDecimal valorIni, BigDecimal valorFim) {
        Map<String, Object> params = new HashMap<>();
        String sql = " From com.green.modelo.Despesa d  ";
        Query query;
        switch (pag) {
            case 0:
                sql = sql + " where";
                break;
            case 1:
                params.put("pagos", true);
                sql = sql + "where pago = :pagos and ";
                break;
            case 2:
                params.put("apagar",false);
                sql = sql + "where pago = :apagar and ";
                break;
        }
        if (despesaFiltro.getDTVencimento() == null && fimVenc == null) {
            Calendar d2 = Calendar.getInstance();
            Date dataMesAntes = new Date(d2.get(Calendar.YEAR) - 1900, d2.get(Calendar.MONTH), 1);
            params.put("inicio", dataMesAntes);
            params.put("fim", d2.getTime());
            sql = sql + " dTVencimento >= :inicio and dTVencimento <= :fim";
        } else {
            if (despesaFiltro.getDTVencimento() != null && fimVenc != null) {
                params.put("inicio", despesaFiltro.getDTVencimento());
                params.put("fim", fimVenc);
                sql = sql + " dTVencimento >= :inicio and dTVencimento <= :fim";
            } else if (despesaFiltro.getDTVencimento() != null && fimVenc == null) {
                params.put("inicio", despesaFiltro.getDTVencimento());
                params.put("fim", new Date(999, 12, 30));
                sql = sql + " dTVencimento >= :inicio and dTVencimento <= :fim ";
            } else if (despesaFiltro.getDTVencimento() == null && fimVenc != null) {
                params.put("inicio", new Date(000, 1, 1));
                params.put("fim", fimVenc);
                sql = sql + " dTVencimento >= :inicio and dTVencimento <= :fim ";
            }
        }

        if (despesaFiltro.getIDConta() != null) {
            params.put("conta", despesaFiltro.getIDConta());
            sql = sql + " and IDConta = :conta ";
        }
        if (despesaFiltro.getIDClassificacao() != null) {
            params.put("classificacao", despesaFiltro.getIDClassificacao());
            sql = sql + " and iDClassificacao = :classificacao ";

        }
        if (despesaFiltro.getIDCCusto() != null) {
            params.put("ccusto", despesaFiltro.getIDCCusto());
            sql = sql + " and iDCCusto = :ccusto ";
        }
        
        if (!valorFim.equals(new BigDecimal("0.00")) || !valorIni.equals(new BigDecimal("0.00"))) {
            params.put("valorIni", valorIni);
            params.put("valorFim", valorFim);
            sql = sql + " and valorLiquido >= :valorIni and valorLiquido <= :valorFim";
        }
        query = getSf().getCurrentSession().
                createQuery(sql + " order by d.IDConta,d.dTVencimento").setProperties(params);

        return query.list();
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

    public List<Despesa> vencimentosDoDia() {
        Date d = new Date();
        Calendar d2 = Calendar.getInstance();
        Date dataMesAntes = new Date(d2.get(Calendar.YEAR) - 1900, d2.get(Calendar.MONTH), 1);
        d2.set(Calendar.DAY_OF_MONTH, d2.getActualMaximum(Calendar.DAY_OF_MONTH)) ;
        Query query = getSf().getCurrentSession().createQuery("From com.green.modelo.Despesa d where d.dTVencimento<= :ultimo and d.dTVencimento>=:d2 order by d.dTVencimento")
                .setParameter("d2", dataMesAntes).setParameter("ultimo", d2.getTime());
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
