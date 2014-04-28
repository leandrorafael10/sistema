/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.CalculoComissaoDAO;
import com.green.modelo.CalculoComissao;
import com.green.util.ContextoBean;
import com.green.util.ContextoUtil;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author leandro.silva
 */
@Service("calculoComissaoRN")
public class CalculoComissaoRN {
    @Autowired
    private CalculoComissaoDAO calculoComissaoDAO;

    @Transactional(value = "tmGreen")
    public void salvar(CalculoComissao calculoComissao){
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        calculoComissao.setIDUsuario(contextoBean.getUsuarioLogado());
        calculoComissao.setDTInc(new Date());
        getCalculoComissaoDAO().salvar(calculoComissao);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado com sucesso!", "Atualizado com sucesso!"));
    }
    public CalculoComissao carregarUltimo(){
       return getCalculoComissaoDAO().carregarUltimo();
    }
    
    
    public CalculoComissaoDAO getCalculoComissaoDAO() {
        return calculoComissaoDAO;
    }

    public void setCalculoComissaoDAO(CalculoComissaoDAO calculoComissaoDAO) {
        this.calculoComissaoDAO = calculoComissaoDAO;
    }
    
    
    
}
