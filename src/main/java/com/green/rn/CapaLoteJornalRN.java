/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.green.dao.CapaLoteJornalDAO;
import com.green.dao.LogMovimentacaoDAO;
import com.green.modelo.Capalotejornal;
import com.green.modelo.Equipevenda;
import com.green.modelo.LogMovimentacao;
import com.green.util.ContextoUtil;

/**
 *
 * @author leandro.silva
 */
@Service("capaLoteJornalRN")
public class CapaLoteJornalRN {

    @Autowired
    private CapaLoteJornalDAO capaLoteJornalDAO;
    @Autowired
    private LogMovimentacaoDAO logMovimentacaoDAO;

    public Capalotejornal carregar(Integer pk) {
        return getCapaLoteJornalDAO().carregar(pk);
    }

    public Capalotejornal buscarPorNumeroLike(int c) {
        return getCapaLoteJornalDAO().buscarPorNumeroLike(c);
    }

    public Capalotejornal buscarPorGestorLike(String numeroGestor) {
        return getCapaLoteJornalDAO().buscarPorGestorLike(numeroGestor);
    }

    public Capalotejornal buscarPorClienteLike(String cliente) {
        return getCapaLoteJornalDAO().buscarPorClienteLike(cliente);
    }

    @SuppressWarnings("rawtypes")
    public List rankingEquipe(int mes, int ano) {
        return getCapaLoteJornalDAO().rankingEquipe(mes, ano);
    }

    /*
     * busca completa para usuarios internos
     */
    public Capalotejornal buscarPorNumero(int c) {
        return getCapaLoteJornalDAO().buscarPorNumero(c);
    }
    /*
     * busca completa para usuarios internos
     */

    public Capalotejornal buscaPorGestor(String gestor) {
        return getCapaLoteJornalDAO().buscarPorNumGestor(gestor);
    }
    /*
     * busca completa para usuarios internos
     */

    public Capalotejornal buscaPorCliente(String nome) {
        return getCapaLoteJornalDAO().buscarPorCliente(nome);
    }

    public List<Capalotejornal> listar() {
        return getCapaLoteJornalDAO().listar();
    }

    public List<Capalotejornal> listarMesAtual() {
        return getCapaLoteJornalDAO().listarMesAtual();
    }

    public List<Capalotejornal> listarDia() {
        return getCapaLoteJornalDAO().listarDia();
    }

    @SuppressWarnings("rawtypes")
    public List vendasEquipe(Equipevenda equipevenda) {
        return getCapaLoteJornalDAO().vendasEquipe(equipevenda);
    }

    public List<Capalotejornal> listarComFiltro(int mes, int ano, int status) {
        return getCapaLoteJornalDAO().listarComFiltro(mes, ano, status);
    }

    public List<Capalotejornal> listarPorPeriodo(Date inicio, Date fim, int status) {
        return getCapaLoteJornalDAO().listarPorPeriodo(inicio, fim, status);
    }

    @Transactional("tmGreen")
    public void atualizar(Capalotejornal capalotejornal) {
        capalotejornal.setIDUsuarioAlt(ContextoUtil.getContextoBean()
                .getUsuarioLogado());
        capalotejornal.setDTAlt(new Date());
        getCapaLoteJornalDAO().atualizar(capalotejornal);
        LogMovimentacao logMovimentacao = new LogMovimentacao(null,
                capalotejornal.getValor(), capalotejornal.getModalidade(), new Date(), ContextoUtil
                .getContextoBean().getUsuarioLogado(),
                capalotejornal.getDTVenda(), capalotejornal);
        getLogMovimentacaoDAO().salvar(logMovimentacao);
    }

    public void excluir(Capalotejornal capalotejornal) {
        getCapaLoteJornalDAO().excluir(capalotejornal);
    }

    public List<Capalotejornal> listarSemGestor() {
        return getCapaLoteJornalDAO().listarSemGestor();
    }

    public CapaLoteJornalDAO getCapaLoteJornalDAO() {
        return capaLoteJornalDAO;
    }

    public void setCapaLoteJornalDAO(CapaLoteJornalDAO capaLoteJornalDAO) {
        this.capaLoteJornalDAO = capaLoteJornalDAO;
    }

    @Transactional("tmGreen")
    public void salvar(Capalotejornal capalotejornal) {
        Capalotejornal c = getCapaLoteJornalDAO().buscarPorNumContrato(
                capalotejornal.getContrato());
        if (c == null) {
            capalotejornal.setDTinc(new Date());
            capalotejornal.setIDUsuario(ContextoUtil.getContextoBean()
                    .getUsuarioLogado());
            capalotejornal.setWeb_otempo(true);
            capalotejornal.setWeb_super(true);
            capalotejornal.setFisico_otempo(true);
            getCapaLoteJornalDAO().salvar(capalotejornal);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Salvo com sucesso!", "Salvo com sucesso!"));
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Numero de contrato já cadastrado!",
                            "Numero de contrato já cadastrado!"));
        }
    }

    @Transactional("tmGreen")
    public void atualizarGerador(Capalotejornal cp, String gerador) {
        Capalotejornal c = getCapaLoteJornalDAO().buscarPorNumGestor(gerador);
        if (c == null) {

            cp.setGerador(gerador);
            cp.setIDUsuarioGerador(ContextoUtil.getContextoBean()
                    .getUsuarioLogado());
            cp.setDTIncGerador(new Date());
            getCapaLoteJornalDAO().atualizar(cp);
            LogMovimentacao logMovimentacao = new LogMovimentacao(null,
                    cp.getValor(), cp.getModalidade(), new Date(), ContextoUtil
                    .getContextoBean().getUsuarioLogado(),
                    cp.getDTVenda(), cp);
            getLogMovimentacaoDAO().salvar(logMovimentacao);

        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Erro!Numero de gestor já cadastrato!",
                            "Erro!Numero de gestor já cadastrato!"));
        }
    }

    @Transactional("tmGreen")
    public void atualizarValor(Capalotejornal cp, BigDecimal valor) {
        LogMovimentacao logMovimentacao = new LogMovimentacao(null,
                cp.getValor(), cp.getModalidade(), new Date(), ContextoUtil
                .getContextoBean().getUsuarioLogado(), cp.getDTVenda(), cp);
        getLogMovimentacaoDAO().salvar(logMovimentacao);
        cp.setValor(valor);
        getCapaLoteJornalDAO().atualizar(cp);

        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Atualizado com sucesso!", "Atualizado com sucesso!"));

    }

    @SuppressWarnings("rawtypes")
    public List faturamentoGeral() {
        return getCapaLoteJornalDAO().faturamentoGeral();
    }

    @SuppressWarnings("rawtypes")
    public List faturamentoPorEquipe() {
        return getCapaLoteJornalDAO().faturamentoPorEquipe();
    }

    @SuppressWarnings("rawtypes")
    public List situacaoVendas() {
        return getCapaLoteJornalDAO().situacaoVendas();
    }

    public LogMovimentacaoDAO getLogMovimentacaoDAO() {
        return logMovimentacaoDAO;
    }

    public void setLogMovimentacaoDAO(LogMovimentacaoDAO logMovimentacaoDAO) {
        this.logMovimentacaoDAO = logMovimentacaoDAO;
    }

    public List rankingEquipeFaturado(int mes, int ano, int diaFechamento) {
        return getCapaLoteJornalDAO().rankingEquipeFaturado(mes, ano, diaFechamento);
    }

}
