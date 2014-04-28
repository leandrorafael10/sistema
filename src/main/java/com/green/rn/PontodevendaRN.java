/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.PontodeVendaDAO;
import com.green.modelo.Pontodevenda;
import com.green.util.ContextoUtil;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author leandro.silva
 */
@Service("pontodevendaRN")
public class PontodevendaRN {

    @Autowired
    private PontodeVendaDAO pontodeVendaDAO;

    public List<Pontodevenda> listar() {
        return getPontodeVendaDAO().listar();
    }

    public Pontodevenda carregar(Integer pk) {
        return getPontodeVendaDAO().carregar(pk);
    }

    public void salvar(Pontodevenda pontodevenda) {
        pontodevenda.setDTInc(new Date());
        pontodevenda.setIDUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());
        pontodevenda.setPais("Brasil");
        getPontodeVendaDAO().salvar(pontodevenda);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", "Salvo com sucesso!"));
    }

    public void excluir(Pontodevenda pontodevenda) {
        getPontodeVendaDAO().excluir(pontodevenda);
    }

    @Transactional("tmGreen")
    public void atualizar(Pontodevenda pontodevenda) {
        pontodevenda.setIDUsuarioAlt(ContextoUtil.getContextoBean().getUsuarioLogado());
        pontodevenda.setDTAlt(new Date());
        getPontodeVendaDAO().atualizar(pontodevenda);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado com sucesso!", "Atualizado com sucesso!"));
    }

    public PontodeVendaDAO getPontodeVendaDAO() {
        return pontodeVendaDAO;
    }

    public void setPontodeVendaDAO(PontodeVendaDAO pontodeVendaDAO) {
        this.pontodeVendaDAO = pontodeVendaDAO;
    }
}
