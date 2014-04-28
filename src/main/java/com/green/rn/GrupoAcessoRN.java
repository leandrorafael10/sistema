/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.GrupoAcessoDAO;
import com.green.modelo.Grupoacesso;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leandro.silva
 */
@Service("grupoAcessoRN")
public class GrupoAcessoRN {
   
    @Autowired
    private GrupoAcessoDAO grupoacessoDAO;
    
    public Grupoacesso carregar (Integer codigo){
        return getGrupoacessoDAO().carregar(codigo);
    }
    
    public List<Grupoacesso> listar(){
        return getGrupoacessoDAO().listar();
    }

    public GrupoAcessoDAO getGrupoacessoDAO() {
        return grupoacessoDAO;
    }

    public void setGrupoacessoDAO(GrupoAcessoDAO grupoacessoDAO) {
        this.grupoacessoDAO = grupoacessoDAO;
    }
    

    
    
}
