/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Receita;
import com.green.modelo.Receitacredito;
import java.math.BigDecimal;
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
@Repository("receitaCreditoDAO")
public class ReceitaCreditoDAO {
    @Autowired
    private SessionFactory sf;

    public void salvar(Receitacredito receitacredito){
        receitacredito.setTipoPagamento(true);
        getSf().getCurrentSession().save(receitacredito);
        getSf().getCurrentSession().flush();
    }
    public List<Receitacredito> filtro(Receita receitaFiltro, Date fimVenc, int pag, BigDecimal valorIni, BigDecimal valorFim){
        Map<String, Object> params = new HashMap<>();
        String sql = " From com.green.modelo.Receitacredito d where ";
        Query query;
        if (receitaFiltro.getDTVencimento() == null && fimVenc == null) {
            Calendar d2 = Calendar.getInstance();
            Date dataMesAntes = new Date(d2.get(Calendar.YEAR) - 1900, d2.get(Calendar.MONTH), 1);
            params.put("inicio", dataMesAntes);
            params.put("fim", d2.getTime());
            sql = sql + " d.iDCredito.dTBaixa >= :inicio and d.iDCredito.dTBaixa <= :fim";
        } else {
            if (receitaFiltro.getDTVencimento() != null && fimVenc != null) {
                params.put("inicio", receitaFiltro.getDTVencimento());
                params.put("fim", fimVenc);
                sql = sql + " d.iDCredito.dTBaixa >= :inicio and d.iDCredito.dTBaixa <= :fim";
            } else if (receitaFiltro.getDTVencimento() != null && fimVenc == null) {
                params.put("inicio", receitaFiltro.getDTVencimento());
                params.put("fim", new Date(999, 12, 30));
                sql = sql + " d.iDCredito.dTBaixa >= :inicio and d.iDCredito.dTBaixa <= :fim ";
            } else if (receitaFiltro.getDTVencimento() == null && fimVenc != null) {
                params.put("inicio", new Date(000, 1, 1));
                params.put("fim", fimVenc);
                sql = sql + " d.iDCredito.dTBaixa >= :inicio and d.iDCredito.dTBaixa <= :fim ";
            }
        }
         
        
        
        if (receitaFiltro.getIDCliente() != null) {
            params.put("cliente",receitaFiltro.getIDCliente());
            sql = sql + " and iDCliente = :cliente ";
        }
        if (!valorFim.equals(new BigDecimal("0.00")) || !valorIni.equals(new BigDecimal("0.00"))) {
            params.put("valorIni", valorIni);
            params.put("valorFim", valorFim);
            sql = sql + " and valor >= :valorIni and valor <= :valorFim";
        }
        query = getSf().getCurrentSession().
                createQuery(sql + " order by d.iDCredito.dTBaixa").setProperties(params);

        return query.list();
    }
    
    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    
}
