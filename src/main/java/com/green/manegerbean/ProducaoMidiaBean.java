/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.green.modelo.ObsContrato;
import com.green.modelo.ProducaoMidia;
import com.green.rn.ObsContratoRN;
import com.green.rn.ProducaoMidiaRN;
import com.green.util.ContextoUtil;

/**
 *
 * @author leandro.silva
 */
@SuppressWarnings("serial")
@ManagedBean(name = "producaoMidiaBean")
@ViewScoped
public class ProducaoMidiaBean implements Serializable {

    @ManagedProperty("#{producaoMidiaRN}")
    private ProducaoMidiaRN producaoMidiaRN;
    @ManagedProperty("#{obsContratoRN}")
    private ObsContratoRN obsContratoRN;
    private ProducaoMidia producaoMidia = new ProducaoMidia();
    private List<ProducaoMidia> producaoMidias;
    private int opc;
    private static final int VOLTA_PRODUCAO = 2;

    public void alterarStatus(int tipo) {
        ObsContrato obsContrato = new ObsContrato();
        obsContrato.setDTObs(new Date());
        obsContrato.setIDFuncionario(ContextoUtil.getContextoBean().getUsuarioLogado().getIDFuncionario());
        switch (tipo) {
            case 0:
                obsContrato.setObs("-Não tratado-");
            case 1:
                obsContrato.setObs("-Pendente Cliente-");
            case 2:
                obsContrato.setObs("-Pronto -");
        }
        obsContrato.setIDContratoMidia(getProducaoMidia().getIDContratoMidia());
        getProducaoMidiaRN().alterarStatusMat(getProducaoMidia(), getOpc());
        this.getObsContratoRN().salvar(obsContrato);

    }

    public void alterarStatusProd(ActionEvent actionEvent) {
        ObsContrato obsContrato = new ObsContrato();
        obsContrato.setDTObs(new Date());
        obsContrato.setIDFuncionario(ContextoUtil.getContextoBean().getUsuarioLogado().getIDFuncionario());
        switch (getProducaoMidia().getStatusProducao()) {
            case 0:
                obsContrato.setObs("-Não tratado-");
            case 1:
                obsContrato.setObs("-Pendente Produção-");
            case 2:
                obsContrato.setObs("-Aguardando Aprovação do Cliente-");
            case 3:
                obsContrato.setObs("-Aprovado Pelo Cliente-");
        }
        obsContrato.setIDContratoMidia(getProducaoMidia().getIDContratoMidia());
        getProducaoMidiaRN().alterarStatusProd(getProducaoMidia());
        this.getObsContratoRN().salvar(obsContrato);
    }

    public void voltarProducao(ActionEvent actionEvent) {
        ObsContrato obsContrato = new ObsContrato();
        obsContrato.setDTObs(new Date());
        obsContrato.setIDFuncionario(ContextoUtil.getContextoBean().getUsuarioLogado().getIDFuncionario());
        obsContrato.setObs("-Voltando Produção-");
        obsContrato.setIDContratoMidia(getProducaoMidia().getIDContratoMidia());
        getProducaoMidia().setStatusProducao(VOLTA_PRODUCAO);
        getProducaoMidiaRN().alterarStatusProd(getProducaoMidia());
        this.getObsContratoRN().salvar(obsContrato);
    }

    public List<ProducaoMidia> listar() {
        return getProducaoMidiaRN().listar();
    }

    public List<ProducaoMidia> listarFinalizados() {
        return getProducaoMidiaRN().listarFinalizados();
    }

    public ProducaoMidiaRN getProducaoMidiaRN() {
        return producaoMidiaRN;
    }

    public void setProducaoMidiaRN(ProducaoMidiaRN producaoMidiaRN) {
        this.producaoMidiaRN = producaoMidiaRN;
    }

    public ProducaoMidia getProducaoMidia() {
        return producaoMidia;
    }

    public void setProducaoMidia(ProducaoMidia producaoMidia) {
        this.producaoMidia = producaoMidia;
    }

    public List<ProducaoMidia> getProducaoMidias() {
        return producaoMidias;
    }

    public void setProducaoMidias(List<ProducaoMidia> producaoMidias) {
        this.producaoMidias = producaoMidias;
    }

    public int getOpc() {
        return opc;
    }

    public void setOpc(int opc) {
        this.opc = opc;
    }

    public ObsContratoRN getObsContratoRN() {
        return obsContratoRN;
    }

    public void setObsContratoRN(ObsContratoRN obsContratoRN) {
        this.obsContratoRN = obsContratoRN;
    }
    
}
