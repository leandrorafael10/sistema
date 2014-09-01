/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.BrindeEntradaDAO;
import com.green.modelo.Brinde;
import com.green.modelo.BrindeEntrada;
import com.green.util.ContextoUtil;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author leandro.silva
 */
@Service("brindeEntradaRN")
public class BrindeEntradaRN {

    @Autowired
    private BrindeEntradaDAO brindeEntradaDAO;
    @Autowired
    private BrindeRN brindeDAO;

    public void salvar(BrindeEntrada brindeEntrada) {
        getBrindeEntradaDAO().salvar(brindeEntrada);
    }

    public void atualizar(BrindeEntrada brindeEntrada) {
        getBrindeEntradaDAO().atualizar(brindeEntrada);
    }

    @Transactional(value = "tmGreen")
    public void entradaBrinde(BrindeEntrada brindeEntrada) {

        brindeEntrada.setDTInc(new Date());
        brindeEntrada.setIDUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());
        brindeEntrada.getIDBrinde().setSaldoAtual(brindeEntrada.getIDBrinde().getSaldoAtual() + brindeEntrada.getQuantidade());
        getBrindeEntradaDAO().salvar(brindeEntrada);
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Entrada efetuada com sucesso!", "Entrada efetuada com sucesso!"));
        RequestContext.getCurrentInstance().update("formListaBrindes");
        RequestContext.getCurrentInstance().execute("PF('dialogAdicionar').hide()");
    }

    public List<BrindeEntrada> listarPorBrinde(Brinde brinde) {
        return getBrindeEntradaDAO().listarPorBrinde(brinde);
    }

    public BrindeEntradaDAO getBrindeEntradaDAO() {
        return brindeEntradaDAO;
    }

    public void setBrindeEntradaDAO(BrindeEntradaDAO brindeEntradaDAO) {
        this.brindeEntradaDAO = brindeEntradaDAO;
    }

    public BrindeRN getBrindeDAO() {
        return brindeDAO;
    }

    public void setBrindeDAO(BrindeRN brindeDAO) {
        this.brindeDAO = brindeDAO;
    }

}
