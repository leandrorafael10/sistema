/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.green.modelo.Capalotejornal;
import com.green.modelo.Equipevenda;
import java.util.GregorianCalendar;

/**
 *
 * @author leandro.silva
 */
@Repository("capaLoteJornalDAO")
public class CapaLoteJornalDAO {

    @Autowired
    private SessionFactory sf;

    public Capalotejornal carregar(Integer pk) {
        Query query = getSf()
                .getCurrentSession()
                .createQuery(
                        "from com.green.modelo.Capalotejornal cp where cp.iDCapalotejornal = :id")
                .setInteger("id", pk);
        return (Capalotejornal) query.uniqueResult();
    }
    
    

    @SuppressWarnings("unchecked")
    public List<Capalotejornal> listar() {
        return (List<Capalotejornal>) getSf()
                .getCurrentSession()
                .createQuery(
                        "from com.green.modelo.Capalotejornal order by iDCapalotejornal desc")
                .list();
    }

    @SuppressWarnings("unchecked")
    public List<Capalotejornal> listarMesAtual() {

        Calendar calendar = Calendar.getInstance();
        int mes = calendar.get(Calendar.MONTH) + 1;
        int ano = calendar.get(Calendar.YEAR);
        Query query = getSf()
                .getCurrentSession()
                .createQuery(
                        "from com.green.modelo.Capalotejornal where month(dTVenda) = :inicio "
                        + "and year(dTVenda) <= :fim  order by iDCapalotejornal desc")
                .setInteger("inicio", mes).setInteger("fim", ano);
        return (List<Capalotejornal>) query.list();
    }

    @SuppressWarnings("unchecked")
    public List<Capalotejornal> listarDia() {

        Calendar calendar = Calendar.getInstance();
        int mes = calendar.get(Calendar.MONTH) + 1;
        int ano = calendar.get(Calendar.YEAR);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        Query query = getSf()
                .getCurrentSession()
                .createQuery(
                        "from com.green.modelo.Capalotejornal where month(dTInc) = :inicio "
                        + "and year(dTInc) = :fim and day(dTInc) = :dia  order by iDCapalotejornal desc")
                .setInteger("inicio", mes).setInteger("fim", ano).setInteger("dia", dia);
        return (List<Capalotejornal>) query.list();
    }

    /*
     * Busca por numero  para usuarios externos
     * Não retorna contratos agendados
     */
    public Capalotejornal buscarPorNumeroLike(int c) {
        Query query = getSf()
                .getCurrentSession()
                .createQuery(
                        "from com.green.modelo.Capalotejornal "
                        + "cp where cp.contrato = :numero and cp.status !=3")
                .setInteger("numero", c);
        return (Capalotejornal) query.uniqueResult();
    }

    /*
     * Busca por numero do gestor para usuarios externos
     * Não retorna contratos agendados
     */
    public Capalotejornal buscarPorGestorLike(String numeroGestor) {
        Query query = getSf()
                .getCurrentSession()
                .createQuery(
                        "from com.green.modelo.Capalotejornal "
                        + "cp where cp.gerador like :numero and cp.status !=3")
                .setParameter("numero", "%" + numeroGestor + "%").setMaxResults(1);
        return (Capalotejornal) query.uniqueResult();

    }
    /*
     * Busca por nome para usuarios externos 
     * Não busca os contratos agendados
     */

    public Capalotejornal buscarPorClienteLike(String cliente) {
        Query query = getSf()
                .getCurrentSession()
                .createQuery(
                        "from com.green.modelo.Capalotejornal "
                        + "cp where upper(cp.nome) like upper(:numero) and cp.status !=3")
                .setParameter("numero", "%" + cliente + "%").setMaxResults(1);
        return (Capalotejornal) query.uniqueResult();
    }
    /*
     * Busca referente o numero contrato para usuarios internos
     */

    public Capalotejornal buscarPorNumero(int c) {
        Query query = getSf()
                .getCurrentSession()
                .createQuery(
                        "from com.green.modelo.Capalotejornal "
                        + "cp where cp.contrato = :numero ")
                .setInteger("numero", c);
        return (Capalotejornal) query.uniqueResult();
    }
    /*
     * Busca referente o numero do gestor para usuarios internos
     */

    public Capalotejornal buscarPorNumGestor(String c) {
        Query query = getSf()
                .getCurrentSession()
                .createQuery(
                        "from com.green.modelo.Capalotejornal "
                        + "cp where cp.gerador like :numero")
                .setParameter("numero", c);
        return (Capalotejornal) query.uniqueResult();
    }
    /*
     * Busca por nome do cliente somente para usuarios internos
     */

    public Capalotejornal buscarPorCliente(String cliente) {
        Query query = getSf()
                .getCurrentSession()
                .createQuery(
                        "from com.green.modelo.Capalotejornal "
                        + "cp where upper(cp.nome) like upper(:numero)")
                .setParameter("numero", "%" + cliente + "%").setMaxResults(1);
        return (Capalotejornal) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<Capalotejornal> listarSemGestor() {
        Query query = getSf()
                .getCurrentSession()
                .createQuery(
                        "from com.green.modelo.Capalotejornal cp where cp.gerador is null and cp.status = 1 order by cp.contrato");
        return (List<Capalotejornal>) query.list();
    }

    public List rankingEquipe(int mes, int ano) {

        //ultimo dia do mes da consulta
        GregorianCalendar fimMes = (GregorianCalendar) GregorianCalendar.getInstance();
        fimMes.set(Calendar.MONTH, mes - 1);
        int ultimoDia = fimMes.getActualMaximum(Calendar.DAY_OF_MONTH);
        fimMes.set(Calendar.YEAR, ano);
        fimMes.set(Calendar.DAY_OF_MONTH, ultimoDia);

        String sql1 = "Select cj.iDGerente.iDPessoa.razao ,"  //[0] equipe
                + "cj.iDFuncionarioPromotor.iDPessoa.razao ,"  // [1] promotor
                + " count(cj.iDCapalotejornal), " // [2] total
                + "sum(cj.valor)," //[3] soma total
                + "sum(case when cj.status = 1 then cj.valor else 0 end)" // [4] soma ativas
                + " ,sum(case when cj.status = 1 then 1 else 0 end)" // [5] conta ativas
                + " ,sum(case when cj.status = 0 then 1 else 0 end)" //[6] conta canceladas
                + ",sum(case when cj.status = 2 then 1 else 0 end) " //[7] conta pendentes
                + ",sum(case when cj.status = 3 then 1 else 0 end) " // [8] conta agendadas
                + ",sum(case when cj.status = 4 then 1 else 0 end) " // [9] conta renovação
                + ",sum(case when cj.status = 5 then 1 else 0 end), " //[10] conta estornado
                + "sum(case when cj.status = 0 then cj.valor else 0 end), " //[11] soma cancelados
                + "sum(case when cj.status = 2 then cj.valor else 0 end), "//[12] soma pendentes
                + "sum(case when cj.status = 3 then cj.valor else 0 end), "//[13] soma agendados
                + "sum(case when cj.status = 4 then cj.valor else 0 end) "//[14] soma renovados
                + "  from com.green.modelo.Capalotejornal cj "
                + "where (month(cj.dTVenda) = :mes and year(cj.dTVenda) = :ano) or (cj.status in(2,3)"
                + "and cj.dTVenda<= :fechamento) group by cj.iDGerente,cj.iDFuncionarioPromotor "
                + "order by cj.iDGerente.iDPessoa.razao,cj.iDFuncionarioPromotor.iDPessoa.razao";

        Query query = getSf().getCurrentSession().createQuery(sql1).setInteger("mes", mes)
                .setInteger("ano", ano).setDate("fechamento", fimMes.getTime());
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List obj = query.list();
        return obj;
    }

    public List rankingEquipeFaturado(int mes, int ano, int diaFinal) {
        Calendar diaFechamento = Calendar.getInstance();
        Calendar primeiroDia = Calendar.getInstance();
        primeiroDia.set(Calendar.MONTH, mes-1);
        primeiroDia.set(Calendar.DAY_OF_MONTH, 1);
        diaFechamento.set(Calendar.MONTH, mes-1);
        diaFechamento.set(Calendar.DAY_OF_MONTH, diaFinal);
        diaFechamento.add(Calendar.MONTH, +1);
        String sql = "Select iDGerente.iDPessoa.razao ,iDFuncionarioPromotor.iDPessoa.razao "
                + ", count(iDCapalotejornal), "
                + "sum(valor),"
                + " avg(valor) "
                + " from com.green.modelo.Capalotejornal where ((month(dTVenda) = "+mes+" "
                + " and year(dTVenda) = "+ano+" and dTIncGerador <= :diaFechamento "
                + " and dTIncGerador >= :primeiroDia )"
                + " or ( dTIncGerador <= :diaFechamento  "
                + " and dTIncGerador >= :primeiroDia and dTVenda < :primeiroDia))"
                + " and status = 1 group by iDGerente , iDFuncionarioPromotor "
                + "order by iDGerente.iDPessoa.razao , iDFuncionarioPromotor.iDPessoa.razao";

        Query query = getSf().getCurrentSession().createQuery(sql).setDate("diaFechamento", diaFechamento.getTime()).setDate("primeiroDia", primeiroDia.getTime());
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List obj = query.list();
        return obj;
    }

    @SuppressWarnings("unchecked")
    public List<Capalotejornal> listarComFiltro(int mes, int ano, int status) {
        String sql;
        Query query = null;
        if (status == 5) {
            sql = "from com.green.modelo.Capalotejornal where month(dTVenda)=:mes and year(dTVenda) =:ano ";
            query = getSf().getCurrentSession().createQuery(sql)
                    .setInteger("mes", mes).setInteger("ano", ano);
        } else {
            sql = "from com.green.modelo.Capalotejornal where month(dTVenda)=:mes and year(dTVenda) =:ano and status = :tipo";
            query = getSf().getCurrentSession().createQuery(sql)
                    .setInteger("mes", mes).setInteger("ano", ano)
                    .setInteger("tipo", status);
        }
        return (List<Capalotejornal>) query.list();
    }

    @SuppressWarnings("unchecked")
    public List<Capalotejornal> listarPorPeriodo(Date inicio, Date fim, int status) {
        String sql;
        Query query = null;
        if (status == 5) {
            sql = "from com.green.modelo.Capalotejornal where dTVenda >=:inicio and "
                    + "dTVenda <=:fim ";
            query = getSf().getCurrentSession().createQuery(sql)
                    .setDate("inicio", inicio).setDate("fim", fim);
        } else {
            sql = "from com.green.modelo.Capalotejornal where dTVenda >=:inicio and dTVenda <=:fim and status = :tipo";
            query = getSf().getCurrentSession().createQuery(sql)
                    .setDate("inicio", inicio).setDate("fim", fim)
                    .setInteger("tipo", status);
        }
        return (List<Capalotejornal>) query.list();
    }

    public void atualizar(Capalotejornal capalotejornal) {
        getSf().getCurrentSession().update(capalotejornal);
    }

    public Capalotejornal buscarPorNumContrato(int codigo) {
        Query query = getSf()
                .getCurrentSession()
                .createQuery(
                        "from com.green.modelo.Capalotejornal cp where cp.contrato = :codigo")
                .setInteger("codigo", codigo);
        return (Capalotejornal) query.uniqueResult();

    }

    @SuppressWarnings("rawtypes")
    public List vendasEquipe(Equipevenda equipevenda) {
        String sql = "Select count(cj.iDCapalotejornal) ,month(cj.dTVenda),year(cj.dTVenda),sum(cj.valor),"
                + "avg(cj.valor * cp.modalidade)  from com.green.modelo.Capalotejornal cj "
                + "where cj.iDFuncionarioPromotor = :promotor group by cj.iDFuncionarioPromotor, month(cj.dTVenda) ,"
                + "year(cj.dTVenda) order by year(cj.dTVenda) desc,month(cj.dTVenda) desc ";
        Query query = getSf().getCurrentSession().createQuery(sql)
                .setParameter("promotor", equipevenda.getIDPromotor())
                .setMaxResults(12);
        // midias = query.list();
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List obj = query.list();
        return obj;
    }

    @SuppressWarnings("rawtypes")
    public List faturamentoGeral() {
        String sql = "select count(cj.iDCapalotejornal), sum(cj.valor * cj.modalidade),year(cj.dTVenda),month(cj.dTVenda)"
                + " from com.green.modelo.Capalotejornal cj where cj.status = 1 group by year(cj.dTVenda) ,month(cj.dTVenda)";
        Query query = getSf().getCurrentSession().createQuery(sql)
                .setMaxResults(12);
        // midias = query.list();
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List obj = query.list();
        return obj;
    }

    @SuppressWarnings("rawtypes")
    public List faturamentoPorEquipe() {
        String sql = "select cp.iDGerente,sum(cp.valor * cp.modalidade),count(cp.iDCapalotejornal)  from com.green.modelo.Capalotejornal cp  group by cp.iDGerente";
        Query query = getSf().getCurrentSession().createQuery(sql);
        // midias = query.list();
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List obj = query.list();
        return obj;
    }

    @SuppressWarnings("rawtypes")
    public List situacaoVendas() {
        String sql = "select count(cp.iDCapalotejornal),case cp.status when 0 then 'cancelado' "
                + "when 1 then 'faturado' when 2 "
                + "then 'pendente' when 3 then 'agendado' end  from com.green.modelo.Capalotejornal cp group by cp.status";
        Query query = getSf().getCurrentSession().createQuery(sql);
        // midias = query.list();
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List obj = query.list();
        return obj;
    }

    public void excluir(Capalotejornal capalotejornal) {
        getSf().getCurrentSession().delete(capalotejornal);
    }

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    public void salvar(Capalotejornal capalotejornal) {
        getSf().getCurrentSession().save(capalotejornal);
    }

}
