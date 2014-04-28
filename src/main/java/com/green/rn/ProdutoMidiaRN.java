/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.ProdutoMidiaDAO;
import com.green.modelo.ProdutoMidia;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leandro.silva
 */
@Service("produtoMidiaRN")
public class ProdutoMidiaRN {
    @Autowired
    private ProdutoMidiaDAO produtoMidiaDAO;

    
    public void excluir(ProdutoMidia produtoMidia){
        getProdutoMidiaDAO().excluir(produtoMidia);
    }
    public List<ProdutoMidia> listar(){
        return getProdutoMidiaDAO().listar();
    }
    public ProdutoMidia carregar(Integer k){
        return getProdutoMidiaDAO().carregar(k);
    }
    public Integer salvar(ProdutoMidia produtoMidia){
        return  produtoMidiaDAO.salvar(produtoMidia);
    }
    
    public ProdutoMidiaDAO getProdutoMidiaDAO() {
        return produtoMidiaDAO;
    }

    public void setProdutoMidiaDAO(ProdutoMidiaDAO produtoMidiaDAO) {
        this.produtoMidiaDAO = produtoMidiaDAO;
    }
}
