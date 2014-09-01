/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Cliente;
import com.green.modelo.Receita;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("receitaDAO")
public class ReceitaDAO extends AbstractDao<Receita, Integer> {

    @Autowired
    private SessionFactory sf;

    @Override
    public Receita carregar(Integer k) {
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Receita r where r.iDReceita = :id ").setInteger("id", k);
        return (Receita) query.uniqueResult();
    }

    @Override
    public Integer salvar(Receita obj) {
        getSf().getCurrentSession().merge(obj);
        getSf().getCurrentSession().flush();
        return 0;
    }

    @Override
    public List<Receita> listar() {
        List<Receita> receitas;
        receitas = getSf().getCurrentSession().createCriteria(Receita.class).list();
        return receitas;
    }

    @Override
    public void excluir(Receita obj) {
        getSf().getCurrentSession().delete(obj);
    }

    public void atualizar(Receita receita) {
        getSf().getCurrentSession().update(receita);
    }

    public List<Receita> receitasVencidas() {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Receita.class);
        List<Receita> receitas = criteria.list();
        List<Receita> vencidas = new ArrayList<Receita>();
        Date dia = new Date(System.currentTimeMillis());
        dia.setDate(dia.getDate() - 1);
        for (Receita receita : receitas) {
            if (receita.getDTVencimento().before(dia)) {
                vencidas.add(receita);
            }
        }
        return vencidas;
    }

    public List<Receita> receitasAvencer() {
        List<Receita> receitas = new ArrayList<Receita>();
        Calendar diaAtual = Calendar.getInstance();
        diaAtual.add(Calendar.DAY_OF_MONTH, -1);
        Date d = diaAtual.getTime();
        for (Receita receita : listar()) {
            if (receita.getDTVencimento().after(d)) {
                receitas.add(receita);
            }
        }
        return receitas;
    }

    public SessionFactory getSf() {
        return sf;
    }

    public List<Receita> parcelasCliente(Date inicio, Date fim, Cliente c, boolean tipo) {
        Query query = getSf().getCurrentSession().createQuery(" from com.green.modelo.Receita d  "
                + "where d.pago = :pago and d.dTVencimento >= :inicio and d.dTVencimento <= :fim "
                + "and d.iDCliente = :cliente order by d.dTVencimento")
                .setBoolean("pago", tipo).setDate("inicio", inicio).setDate("fim", fim).setParameter("cliente", c);
        return query.list();
    }

    public List<Receita> filtro(Receita receitaFiltro, Date fimVenc, int pag, BigDecimal valorIni, BigDecimal valorFim) {
        Map<String, Object> params = new HashMap<>();
        String sql = " from com.green.modelo.Receita d  ";
        Query query;
        switch (pag) {
            case 0:
                sql = sql + " where";
                break;
            case 1:
                params.put("pagos", true);
                sql = sql + "where d.pago = :pagos and ";
                break;
            case 2:
                params.put("apagar", false);
                sql = sql + "where d.pago = :apagar and ";
                break;
        }
        if (receitaFiltro.getDTVencimento() == null && fimVenc == null) {
            Calendar d2 = Calendar.getInstance();
            Date dataMesAntes = new Date(d2.get(Calendar.YEAR) - 1900, d2.get(Calendar.MONTH), 1);
            params.put("inicio", dataMesAntes);
            params.put("fim", d2.getTime());
            sql = sql + " d.dTVencimento >= :inicio and d.dTVencimento <= :fim";
        } else {
            if (receitaFiltro.getDTVencimento() != null && fimVenc != null) {
                params.put("inicio", receitaFiltro.getDTVencimento());
                params.put("fim", fimVenc);
                sql = sql + " d.dTVencimento >= :inicio and d.dTVencimento <= :fim";
            } else if (receitaFiltro.getDTVencimento() != null && fimVenc == null) {
                params.put("inicio", receitaFiltro.getDTVencimento());
                params.put("fim", new Date(999, 12, 30));
                sql = sql + " d.dTVencimento >= :inicio and d.dTVencimento <= :fim ";
            } else if (receitaFiltro.getDTVencimento() == null && fimVenc != null) {
                params.put("inicio", new Date(000, 1, 1));
                params.put("fim", fimVenc);
                sql = sql + " d.dTVencimento >= :inicio and d.dTVencimento <= :fim ";
            }
        }

        if (receitaFiltro.getIDConta() != null) {
            params.put("conta", receitaFiltro.getIDConta());
            sql = sql + " and d.IDConta = :conta ";
        }
        if (receitaFiltro.getIDClassificacao() != null) {
            params.put("classificacao", receitaFiltro.getIDClassificacao());
            sql = sql + " and d.iDClassificacao = :classificacao ";

        }
        if (receitaFiltro.getIDCCusto() != null) {
            params.put("ccusto", receitaFiltro.getIDCCusto());
            sql = sql + " and d.iDCCusto = :ccusto ";
        }

        if (receitaFiltro.getIDCliente() != null) {
            params.put("cliente", receitaFiltro.getIDCliente());
            sql = sql + " and d.iDCliente = :cliente ";
        }
        if (!valorFim.equals(new BigDecimal("0.00")) || !valorIni.equals(new BigDecimal("0.00"))) {
            params.put("valorIni", valorIni);
            params.put("valorFim", valorFim);
            sql = sql + " and d.valorLiquido >= :valorIni and d.valorLiquido <= :valorFim";
        }
        query = getSf().getCurrentSession().
                createQuery(sql + " order by d.IDConta,d.dTVencimento").setProperties(params);

        return query.list();
    }

    public List<Receita> vencimentosDoDia() {
        Date d = new Date();
        Calendar d2 = Calendar.getInstance();
        Date dataMesAntes = new Date(d2.get(Calendar.YEAR) - 1900, d2.get(Calendar.MONTH), 1);
        d2.set(Calendar.DAY_OF_MONTH, d2.getActualMaximum(Calendar.DAY_OF_MONTH));
        Query query = getSf().getCurrentSession().createQuery("From com.green.modelo.Receita d where d.dTVencimento<= :ultimo and d.dTVencimento>=:d2 order by d.dTVencimento")
                .setParameter("d2", dataMesAntes).setParameter("ultimo", d2.getTime());
        return (List<Receita>) query.list();
    }

    public List<Receita> liberacaoPagamento() {
        Query query = getSf().getCurrentSession().createQuery("From com.green.modelo.Receita d where d.pago = 0 and d.atzPg = 1 order by d.dTVencimento ");
        return (List<Receita>) query.list();
    }

    public List<Receita> receitasPagas() {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Receita.class);
        List<Receita> receitas = criteria.list();
        List<Receita> pagas = new ArrayList<Receita>();
        for (Receita receita : receitas) {
            if (receita.getValorLiquido().compareTo(BigDecimal.ZERO) == 0) {
                pagas.add(receita);
            }
        }
        return pagas;
    }

    public List<Receita> receitasApagar() {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Receita.class);
        List<Receita> receitas = criteria.list();
        List<Receita> aPagar = new ArrayList<Receita>();
        for (Receita receita : receitas) {
            if (receita.getValorLiquido().compareTo(BigDecimal.ZERO) != 0) {
                aPagar.add(receita);
            }
        }
        return aPagar;
    }
    
    

    public List<Receita> pendentesPagamento() {
        Date d = new Date();
        Query query = getSf().getCurrentSession().createQuery("From com.green.modelo.Receita d where d.pago = 0 and d.atzPg = 2 order by d.dTVencimento");
        return (List<Receita>) query.list();

    }

    public List<Receita> pendentesPagamentoAteHoje() {
        Date d = new Date();
        Query query = getSf().getCurrentSession().createQuery("From com.green.modelo.Receita d where d.pago = 0 and d.atzPg != 3 and d.dTVencimento < :data order by d.dTVencimento ").setDate("data", d);
        return (List<Receita>) query.list();

    }

    public List<Receita> pagamentoProximoPendenteCliente() {
        Calendar c = new GregorianCalendar();
        c.add(GregorianCalendar.DATE, 10);
        Query query = getSf().getCurrentSession().createQuery("From com.green.modelo.Receita d where d.pago = 0 "
                + "and d.dTVencimento < :data and d.iDCliente.iDTipocliente is null order by d.dTVencimento ").setDate("data", c.getTime());
        return (List<Receita>) query.list();

    }
    public List<Receita> pagamentoPendenteParceiro() {
        Calendar c = new GregorianCalendar();
        c.add(GregorianCalendar.DATE, 10);
        Query query = getSf().getCurrentSession().createQuery("From com.green.modelo.Receita d where d.pago = 0 "
                + " and d.iDCliente.iDTipocliente is not null "
                + " order by d.dTVencimento , d.iDCliente ");
        return (List<Receita>) query.list();

    }
    public List<Receita> pagamentoPendenteContraApresentacao() {
        Calendar c = new GregorianCalendar();
        c.add(GregorianCalendar.DATE, 10);
        Query query = getSf().getCurrentSession().createQuery("From com.green.modelo.Receita d where d.pago = 0 "
                + " and d.idorigem.IDContratoMidia.iDtipopagamento.descricao = 'Contra Apresentação'"
                + " order by d.dTVencimento , d.iDCliente ");
        return (List<Receita>) query.list();

    }
}
