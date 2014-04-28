/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.ContratoMidia;
import com.green.modelo.Funcionario;
import com.green.modelo.Tipopagamento;
import com.green.modelo.Usuario;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("contratoMidiaDAO")
public class ContratoMidiaDAO extends AbstractDao<ContratoMidia, Integer> {

    @Autowired
    SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public ContratoMidia carregar(Integer k) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(ContratoMidia.class);
        criteria.add(Restrictions.eq("idcontratoMidia", k));
        return (ContratoMidia) criteria.uniqueResult();
    }

    @Override
    public Integer salvar(ContratoMidia obj) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(ContratoMidia.class);
        criteria.setProjection(Projections.max("idcontratoMidia"));
        Integer codigo = (Integer) criteria.uniqueResult();
        getSf().getCurrentSession().merge(obj);
        if (codigo == null) {
            return 1;
        }
        return codigo + 1;
    }

    public void salvarContrato(ContratoMidia cm) {
        getSf().getCurrentSession().save(cm);
    }

    @Override
    public List<ContratoMidia> listar() {
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.ContratoMidia ");
        
        return (List<ContratoMidia>)query.list();
    }

    @Override
    public void excluir(ContratoMidia obj) {
        getSf().getCurrentSession().delete(obj);
    }

    public List listaVend() {
     
        Query query = getSf().getCurrentSession().createQuery("Select c.iDvendedor ,sum(c.valor),c.dTinc from com.green.modelo.ContratoMidia c group by c.iDvendedor,year(c.dTinc),month(c.dTinc)");
        //midias = query.list();
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List obj = query.list();
        return obj;

    }
    //listagem faltando 1 mes para o vencimento

    public List<ContratoMidia> listaVencidos() {
        Date date = new Date();
        Calendar c1 = Calendar.getInstance();
        c1.add(Calendar.DAY_OF_MONTH, +30);
        Query query = getSf().getCurrentSession().createQuery("From com.green.modelo.ContratoMidia c where c.dataFimContrato<= :d and ativo != 2").
                setDate("d", c1.getTime());

        return (List<ContratoMidia>) query.list();
    }

    public List<ContratoMidia> listarPorPraca(Integer id) {
        Query query = getSf().getCurrentSession().createQuery("select c.iDcontratomidia from com.green.modelo.ContratoPracas c where c.iDpraca.idpraca = :id and c.iDcontratomidia.dataFimContrato>= :hoje and c.iDcontratomidia.dataInicioContrato<= :hoje and c.iDcontratomidia.ativo = 0").setInteger("id", id).setDate("hoje", new Date());
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

        List obj = query.list();
        List<ContratoMidia> list = new ArrayList<>();
        for (Object o : obj) {
            Map row = (Map) o;
            list.add((ContratoMidia) row.get("0"));
        }
        return list;

    }

    public List<ContratoMidia> contratoUsuario(Usuario usuario) {
        Calendar calendar = Calendar.getInstance();
        Date dataDia = new Date(System.currentTimeMillis());
        switch (usuario.getIDFuncionario().getIDFuncao().getDescricao()) {
            case "Executivo de contas": {
                Date dataMesAntes = new Date(calendar.get(Calendar.YEAR) - 1900, calendar.get(Calendar.MONTH), 1);
                Criteria criteria = getSf().getCurrentSession().createCriteria(ContratoMidia.class);
                Criterion filtroData = Restrictions.between("dataInicioContrato", dataMesAntes, dataDia);
                Criterion filtro = Restrictions.eq("iDvendedor", usuario.getIDFuncionario());
                criteria.add(filtro).add(filtroData);
                return criteria.list();
            }
            case "Gerente de Vendas": {
                Date dataMesAntes = new Date(calendar.get(Calendar.YEAR) - 1900, calendar.get(Calendar.MONTH), 1);
                Criteria criteria = getSf().getCurrentSession().createCriteria(ContratoMidia.class);
                Criterion filtroData = Restrictions.between("dataInicioContrato", dataMesAntes, dataDia);
                Criterion filtro = Restrictions.eq("iDgerentevendas", usuario.getIDFuncionario());
                criteria.add(filtro).add(filtroData);
                return criteria.list();
            }
            case "Auxiliar Administrativo": {
                Date dataMesAntes = new Date(calendar.get(Calendar.YEAR) - 1900, calendar.get(Calendar.MONTH), 1);
                Criteria criteria = getSf().getCurrentSession().createCriteria(ContratoMidia.class);
                Criterion filtroData = Restrictions.between("dataInicioContrato", dataMesAntes, dataDia);
                criteria.add(filtroData);
                return criteria.list();
            }
            case "Adminstrativo": {
                Date dataMesAntes = new Date(calendar.get(Calendar.YEAR) - 1900, calendar.get(Calendar.MONTH), 1);
                Criteria criteria = getSf().getCurrentSession().createCriteria(ContratoMidia.class);
                Criterion filtroData = Restrictions.between("dataInicioContrato", dataMesAntes, dataDia);
                criteria.add(filtroData);
                return criteria.list();
            }
            default:
                return null;
        }

    }

    public void atualizar(ContratoMidia contratoMidia) {
        getSf().getCurrentSession().update(contratoMidia);
    }

    public List<ContratoMidia> buscaPorVendedor(Funcionario funcionario, Date dtInicio, Date dtFim) {
        Query query;
        Map<String, Object> params = new HashMap<>();
        String sql = "From com.green.modelo.ContratoMidia c";
        Calendar c1 = Calendar.getInstance();
        c1.set(1900 + dtInicio.getYear(), dtInicio.getMonth(), dtInicio.getDate(), 0, 1);
        Calendar c2 = Calendar.getInstance();
        c2.set(1900 + dtFim.getYear(), dtFim.getMonth(), dtFim.getDate(), 23, 59);

        params.put("dtini", dtInicio);
        params.put("dtfim",dtFim);
        if (funcionario == null) {
            sql = sql + " where dTVend >= :dtini and dTVend <= :dtfim ";
        } else {
            sql = sql + " where iDvendedor = :vend and dTVend >= :dtini and dTVend <= :dtfim ";
            params.put("vend", funcionario);
        }
        query = getSf().getCurrentSession().createQuery(sql).setProperties(params);
        return (List<ContratoMidia>) query.list();

    }
    public List<ContratoMidia> buscaPorFormaPag(Tipopagamento tipopagamento, Date dtInicio, Date dtFim) {
        Query query;
        Map<String, Object> params = new HashMap<>();
        String sql = "From com.green.modelo.ContratoMidia c";
        Calendar c1 = Calendar.getInstance();
        c1.set(1900 + dtInicio.getYear(), dtInicio.getMonth(), dtInicio.getDate(), 0, 1);
        Calendar c2 = Calendar.getInstance();
        c2.set(1900 + dtFim.getYear(), dtFim.getMonth(), dtFim.getDate(), 23, 59);

        params.put("dtini", dtInicio);
        params.put("dtfim",dtFim);
        if (tipopagamento == null) {
            sql = sql + " where dTVend >= :dtini and dTVend <= :dtfim ";
        } else {
            sql = sql + " where iDtipopagamento = :vend and dTVend >= :dtini and dTVend <= :dtfim ";
            params.put("vend", tipopagamento);
        }
        query = getSf().getCurrentSession().createQuery(sql).setProperties(params);
        return (List<ContratoMidia>) query.list();

    }
    
    //nao esta sendo utilizado
  public List<ContratoMidia> buscarRazao(String razao,String cnpj){
        String sql = " from com.green.modelo.ContratoMidia c";
        Map<String, Object> params = new HashMap<>();
        Query query ;
        if(!razao.equals("")&&!cnpj.equals("")){
            sql = sql+" where c.iDcliente.iDPessoa.razao like :emp";
            razao = razao.toUpperCase();
            params.put("emp", "%"+razao+"%");
            query =getSf().getCurrentSession().createQuery(sql).setProperties(params);
        }else{
            query =getSf().getCurrentSession().createQuery(sql);
        }
        
        return (List<ContratoMidia>)query.list();
    }
}
