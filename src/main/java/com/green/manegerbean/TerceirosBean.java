/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Terceiros;
import com.green.rn.FuncaoRN;
import com.green.rn.TerceirosRN;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "terceirosBean")
@ViewScoped
public class TerceirosBean {
    
    private Terceiros terceiro =new Terceiros() ;
    private List<Terceiros> terceiros;
    @ManagedProperty("#{terceirosRN}")
    private TerceirosRN terceirosRN;
    @ManagedProperty("#{funcaoRN}")
    private FuncaoRN funcaoRN;
    private boolean opcaoFuncao = true;

    public void salvar(ActionEvent event){
        if(isOpcaoFuncao()){
           getTerceiro().setIDfuncao(getFuncaoRN().carregar(new Integer(5)));
           getTerceiro().setUf(getTerceiro().getIDTerceirosGerente().getUf());
        }
        else{
            getTerceiro().setIDfuncao(getFuncaoRN().carregar(new Integer(1)));
        }
        getTerceirosRN().salvar(getTerceiro());
        setTerceiro(new Terceiros());
    }
    public void demitirTereciro(ActionEvent event){
        getTerceirosRN().demitirTerceiro(getTerceiro());
    }
    
    public Terceiros getTerceiro() {
        return terceiro;
    }

    public void setTerceiro(Terceiros terceiro) {
        this.terceiro = terceiro;
    }

    public List<Terceiros> getTerceiros() {
        return terceiros;
    }

    public void setTerceiros(List<Terceiros> terceiros) {
        this.terceiros = terceiros;
    }

    public TerceirosRN getTerceirosRN() {
        return terceirosRN;
    }

    public void setTerceirosRN(TerceirosRN terceirosRN) {
        this.terceirosRN = terceirosRN;
    }

    public boolean isOpcaoFuncao() {
        return opcaoFuncao;
    }

    public void setOpcaoFuncao(boolean opcaoFuncao) {
        this.opcaoFuncao = opcaoFuncao;
    }

    public FuncaoRN getFuncaoRN() {
        return funcaoRN;
    }

    public void setFuncaoRN(FuncaoRN funcaoRN) {
        this.funcaoRN = funcaoRN;
    }
    
    
    
}
