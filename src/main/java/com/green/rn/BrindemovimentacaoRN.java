/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.green.dao.BrindeDAO;
import com.green.dao.BrindeTermoDAO;
import com.green.dao.BrindemovimentacaoDAO;
import com.green.dao.TermoResponsabilidadeDAO;
import com.green.modelo.BrindeTermo;
import com.green.modelo.Brindemovimentacao;
import com.green.modelo.TermoResponsabilidade;
import com.green.util.ContextoUtil;

/**
 *
 * @author leandro.silva
 */
@Service("brindemovimentacaoRN")
public class BrindemovimentacaoRN {

    @Autowired
    private BrindemovimentacaoDAO brindemovimentacaoDAO;
    @Autowired
    private TermoResponsabilidadeDAO termoResponsabilidadeDAO;
    @Autowired
    private BrindeTermoDAO brindeTermoDAO;
    @Autowired
    private BrindeDAO brindeDAO;

    public BrindemovimentacaoDAO getBrindemovimentacaoDAO() {
        return brindemovimentacaoDAO;
    }

    public void setBrindemovimentacaoDAO(BrindemovimentacaoDAO brindemovimentacaoDAO) {
        this.brindemovimentacaoDAO = brindemovimentacaoDAO;
    }

    public TermoResponsabilidadeDAO getTermoResponsabilidadeDAO() {
        return termoResponsabilidadeDAO;
    }

    public void setTermoResponsabilidadeDAO(TermoResponsabilidadeDAO termoResponsabilidadeDAO) {
        this.termoResponsabilidadeDAO = termoResponsabilidadeDAO;
    }

    public BrindeTermoDAO getBrindeTermoDAO() {
        return brindeTermoDAO;
    }

    public void setBrindeTermoDAO(BrindeTermoDAO brindeTermoDAO) {
        this.brindeTermoDAO = brindeTermoDAO;
    }

    public void salvar(Brindemovimentacao o) {
        getBrindemovimentacaoDAO().salvar(o);
    }

    @Transactional(value = "tmGreen")
    public void salvar(Brindemovimentacao o, List<BrindeTermo> brindeTermos) {
        getBrindemovimentacaoDAO().salvar(o);
        for (TermoResponsabilidade termo : o.getTermoResponsabilidadeList()) {
            termo.setIDUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());
            termo.setDTInc(new Date());
            getTermoResponsabilidadeDAO().salvar(termo);
            for (BrindeTermo brindeTermo : brindeTermos) {
                brindeTermo.setIDTermoResponsabilidade(termo);
                brindeTermo.getIDBrinde().setSaldoAtual(brindeTermo.getIDBrinde().getSaldoAtual() - brindeTermo.getQtd());
                getBrindeDAO().atualizar(brindeTermo.getIDBrinde());
                getBrindeTermoDAO().salvar(brindeTermo);
            }
        }
    }

    @Transactional(value = "tmGreen")
    public void salvarRetornoExtra(TermoResponsabilidade tr) {
        tr.setIDUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());
        tr.setDTInc(new Date());
        getTermoResponsabilidadeDAO().salvar(tr);
        for (BrindeTermo brindeTermo : tr.getBrindeTermoList()) {
            brindeTermo.setIDTermoResponsabilidade(tr);
            brindeTermo.getIDBrinde().setSaldoAtual(brindeTermo.getIDBrinde().getSaldoAtual() + brindeTermo.getQtd());
            getBrindeDAO().atualizar(brindeTermo.getIDBrinde());
            getBrindeTermoDAO().salvar(brindeTermo);
        }
        if (tr.getEntradaSaida()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Retorno salvo com sucesso!", "Retorno salvo com sucesso!"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Retirada extra confirmada!", "Retirada extra confirmada!"));
        }

    }
    @Transactional(value = "tmGreen")
    public void salvarSaidaExtra(TermoResponsabilidade tr) {
        tr.setIDUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());
        tr.setDTInc(new Date());
        getTermoResponsabilidadeDAO().salvar(tr);
        for (BrindeTermo brindeTermo : tr.getBrindeTermoList()) {
            brindeTermo.setIDTermoResponsabilidade(tr);
            brindeTermo.getIDBrinde().setSaldoAtual(brindeTermo.getIDBrinde().getSaldoAtual() - brindeTermo.getQtd());
            getBrindeDAO().atualizar(brindeTermo.getIDBrinde());
            getBrindeTermoDAO().salvar(brindeTermo);
        }
        if (tr.getEntradaSaida()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Retorno salvo com sucesso!", "Retorno salvo com sucesso!"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Retirada extra confirmada!", "Retirada extra confirmada!"));
        }

    }

    public List<BrindeTermo> somaBrindeMovimentacao(Brindemovimentacao b) {
        return getBrindemovimentacaoDAO().somaBrindeMovimentacao(b);
    }

    public void excluir(Brindemovimentacao o) {
        getBrindemovimentacaoDAO().excluir(o);
    }

    public void atualizar(Brindemovimentacao o) {
        getBrindemovimentacaoDAO().atualizar(o);
    }

    public List<Brindemovimentacao> listar() {
        return getBrindemovimentacaoDAO().listar();
    }

    public BrindeDAO getBrindeDAO() {
        return brindeDAO;
    }

    public void setBrindeDAO(BrindeDAO brindeDAO) {
        this.brindeDAO = brindeDAO;
    }

}
