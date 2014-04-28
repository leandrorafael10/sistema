/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.ProdutoMidia;
import com.green.rn.ProdutoMidiaRN;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name="produtoMidiaBean")
@RequestScoped
public class ProdutoMidiaBean implements Serializable{
   
    @ManagedProperty("#{produtoMidiaRN}")
    private ProdutoMidiaRN produtoMidiaRN;
    
    private List<ProdutoMidia> produtoMidias;

    public List<ProdutoMidia> getProdutoMidias() {
        return produtoMidias;
    }

    public void setProdutoMidias(List<ProdutoMidia> produtoMidias) {
        this.produtoMidias = produtoMidias;
    }

   @PostConstruct
    private void init(){
        setProdutoMidias(getProdutoMidiaRN().listar());
    }
    
    
    public ProdutoMidiaRN getProdutoMidiaRN() {
        return produtoMidiaRN;
    }

    public void setProdutoMidiaRN(ProdutoMidiaRN produtoMidiaRN) {
        this.produtoMidiaRN = produtoMidiaRN;
    }
    
}
