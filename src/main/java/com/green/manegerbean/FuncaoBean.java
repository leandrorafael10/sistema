/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Funcao;
import com.green.rn.FuncaoRN;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name="funcaoBean")
@ViewScoped
public class FuncaoBean implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{funcaoRN}")
    private FuncaoRN funcaoRN;
    private Funcao funcao;
    private List<Funcao> funcaos;

    
    @PostConstruct
    private void init(){
        setFuncaos(getFuncaoRN().listar());
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public List<Funcao> getFuncaos() {
        return funcaos;
    }

    public void setFuncaos(List<Funcao> funcaos) {
        this.funcaos = funcaos;
    }
    

    public FuncaoRN getFuncaoRN() {
        return funcaoRN;
    }

    public void setFuncaoRN(FuncaoRN funcaoRN) {
        this.funcaoRN = funcaoRN;
    }
    
}
