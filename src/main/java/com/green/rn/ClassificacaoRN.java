/*
 * To change this template, choose Tools | Templates
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

import com.green.dao.ClassificacaoDAO;
import com.green.modelo.Classificacao;
import com.green.util.ContextoBean;
import com.green.util.ContextoUtil;

/**
 *
 * @author leandro.silva
 */
@Service("classificacaoRN")
public class ClassificacaoRN {

    @Autowired
    private ClassificacaoDAO classificacaoDAO;

    public ClassificacaoDAO getClassificacaoDAO() {
        return classificacaoDAO;
    }

    public void setClassificacaoDAO(ClassificacaoDAO classificacaoDAO) {
        this.classificacaoDAO = classificacaoDAO;
    }

    @Transactional("tmGreen")
    public void salvar(Classificacao classificacao) {
        Classificacao c = getClassificacaoDAO().carregarCodigoEstrututal(classificacao.getCodigo());
        if (c == null) {
            classificacao.setDTInc(new Date(System.currentTimeMillis()));
            classificacao.setIDUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());
            getClassificacaoDAO().salvar(classificacao);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok! Salvo com sucesso!", "Ok! Salvo com sucesso!"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha!Código estrutural já cadastrado!", "Falha! Código estrutural já cadastrado!"));
        }

    }
    public List<Classificacao> listarNatureza(boolean tipo) {
    	return getClassificacaoDAO().listarNatureza(tipo);
    }

    public List<Classificacao> listar() {
        return getClassificacaoDAO().listar();
    }

    @Transactional("tmGreen")
    public void excluir(Classificacao classificacao) {
        getClassificacaoDAO().excluir(classificacao);
    }

    public Classificacao carregar(Integer codigo) {
        return getClassificacaoDAO().carregar(codigo);
    }

    @Transactional("tmGreen")
    public void atualizar(Classificacao classificacao) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();

        classificacao.setDtalt(new Date(System.currentTimeMillis()));
        classificacao.setIdusuarioAlt(contextoBean.getUsuarioLogado());
        getClassificacaoDAO().atualizar(classificacao);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok! Atualizado com sucesso!", "Ok! Atualizado com sucesso!"));
    }

    public List<Classificacao> filtroClassificacao(String codigo, String descricao) {
        return getClassificacaoDAO().filtroClassificacao(codigo, descricao);
    }
}
