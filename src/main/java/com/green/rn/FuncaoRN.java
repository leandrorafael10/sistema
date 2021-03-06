/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.dao.FuncaoDAO;
import com.green.modelo.Funcao;

/**
 *
 * @author leandro.silva
 */
@Service("funcaoRN")
public class FuncaoRN {
    
    @Autowired
    private FuncaoDAO funcaoDAO;

    public FuncaoDAO getFuncaoDAO() {
        return funcaoDAO;
    }

    public void setFuncaoDAO(FuncaoDAO funcaoDAO) {
        this.funcaoDAO = funcaoDAO;
    }
    public Funcao carregar (Integer k){
        return  getFuncaoDAO().carregar(k);
    }
    public List<Funcao> listar(){
        return getFuncaoDAO().listar();
    }
    public List<Funcao> listaFuncoes(List<Integer> id) {
        
        return getFuncaoDAO().listaFuncoes(id);
    }
    
}