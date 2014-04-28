/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.PlanovendaparcelaDAO;
import com.green.modelo.Planovendaparcela;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author leandro.silva
 */
@Service("planovendaparcelaRN")
public class PlanovendaparcelaRN {
    
    @Autowired
    private PlanovendaparcelaDAO planovendaparcelaDAO;

    public void salvar(Planovendaparcela p){
        getPlanovendaparcelaDAO().salvar(p);
        
    }
    public List<Planovendaparcela> listar(){
        return getPlanovendaparcelaDAO().listar();
    }
    @Transactional("tmGreen")
    public void atualizar(Planovendaparcela planovendaparcela){
        getPlanovendaparcelaDAO().atualizar(planovendaparcela);
    }
    public Planovendaparcela buscar(Integer id){
        return getPlanovendaparcelaDAO().buscar(id);
    }
    
    public PlanovendaparcelaDAO getPlanovendaparcelaDAO() {
        return planovendaparcelaDAO;
    }

    public void setPlanovendaparcelaDAO(PlanovendaparcelaDAO planovendaparcelaDAO) {
        this.planovendaparcelaDAO = planovendaparcelaDAO;
    }
    
    
}
