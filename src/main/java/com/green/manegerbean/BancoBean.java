/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.green.modelo.Banco;
import com.green.rn.BancoRN;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name="bancoBean")
@ViewScoped
public class BancoBean implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{bancoRN}")
    private BancoRN bancoRN;
    private List<Banco> bancos;
    private Banco banco;
    
    
    @PostConstruct
    private void init(){
        setBancos(getBancoRN().listar());
    }
    public void salvar(){
        getBancoRN().salvar(getBanco());
    }
    
    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public List<Banco> getBancos() {
        return bancos;
    }

    public void setBancos(List<Banco> bancos) {
        this.bancos = bancos;
    }

    public void setBancoRN(BancoRN bancoRN) {
        this.bancoRN = bancoRN;
    }

    public BancoRN getBancoRN() {
        return bancoRN;
    }
    
    
}
