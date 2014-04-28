/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.BancoDAO;
import com.green.modelo.Banco;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leandro.silva
 */
@Service("bancoRN")
public class BancoRN {
    
    @Autowired
    private BancoDAO bancoDAO;
    
    
    public BancoDAO getBancoDAO() {
        return bancoDAO;
    }

    public void setBancoDAO(BancoDAO bancoDAO) {
        this.bancoDAO = bancoDAO;
    }
    
    public List<Banco> listar(){
        return getBancoDAO().listar();
    }
    
    public Banco carregar(Integer codigo){
        return getBancoDAO().carregar(codigo);
    }
    
    public void salvar(Banco banco){
        getBancoDAO().salvar(banco);
    }
    
}
