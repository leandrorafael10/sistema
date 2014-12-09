/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.dao.ProducaoMidiaDAO;
import com.green.modelo.ProducaoMidia;
import com.green.util.ContextoBean;
import com.green.util.ContextoUtil;

/**
 *
 * @author leandro.silva
 */
@Service("producaoMidiaRN")
public class ProducaoMidiaRN {

    @Autowired
    private ProducaoMidiaDAO producaoMidiaDAO;

    public void alterarStatusMat(ProducaoMidia pm, int opc) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        String msg;
        if (opc == 1) {
            if (pm.getStatusMaterial() < 2) {
                pm.setStatusMaterial(pm.getStatusMaterial() + 1);
                if (pm.getStatusMaterial() == 2) {
                    pm.setDTFimMaterial(new Date());
                    pm.setIDUsuarioAltMaterial(contextoBean.getUsuarioLogado());
                }

                getProducaoMidiaDAO().alterar(pm);
                msg = "Alteração de status do material efetuada com sucesso!";
            } else {
                msg = "Material já foi finalizado!";
            }
        } else {
            if (pm.getStatusProducao() < 2) {
                if (pm.getStatusMaterial() == 2) {
                    pm.setStatusProducao(pm.getStatusProducao() + 1);
                    if (pm.getStatusProducao() == 2) {
                        pm.setDTFimProducao(new Date());
                        pm.setIDUsuarioAltProducao(contextoBean.getUsuarioLogado());
                    }
                    getProducaoMidiaDAO().alterar(pm);
                    msg = "Alteração de status da produção efetuada com sucesso!";
                } else {
                    msg = "Só podera alterar o status da produção quando o material estiver finalizado!";
                }
            } else {
                msg = "não e possivel alterar o status porque video já foi produzido! ";
            }
        }
        facesContext.addMessage(null, new FacesMessage(msg, msg));

    }

    public void alterarStatusProd(ProducaoMidia pm) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        String msg;

        if (pm.getStatusMaterial() == 2) {
            if(pm.getStatusProducao()==3){
                pm.setDTFimProducao(new Date());
                pm.setIDUsuarioAltProducao(contextoBean.getUsuarioLogado());
                RequestContext context = RequestContext.getCurrentInstance();
                context.update("formproduzidos");
            }
            getProducaoMidiaDAO().alterar(pm);
            msg="status alterado com sucesso!";
        } else {
            msg="material para a produção ainda nao finalizado";
        }
        facesContext.addMessage(null, new FacesMessage(msg, msg));

    }

    public List<ProducaoMidia> listar() {
        return getProducaoMidiaDAO().listar();
    }
    public List<ProducaoMidia> listarFinalizados() {
        return getProducaoMidiaDAO().listarFinalizados();
    }

    public ProducaoMidia carregar(Integer id) {
        return getProducaoMidiaDAO().carregar(id);
    }

    public void atualizar(ProducaoMidia producaoMidia) {
        getProducaoMidiaDAO().alterar(producaoMidia);
    }

    public ProducaoMidiaDAO getProducaoMidiaDAO() {
        return producaoMidiaDAO;
    }

    public void setProducaoMidiaDAO(ProducaoMidiaDAO producaoMidiaDAO) {
        this.producaoMidiaDAO = producaoMidiaDAO;
    }
}
