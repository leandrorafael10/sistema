/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.green.rn;

import com.green.dao.EmpresaDAO;
import com.green.modelo.Empresa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leandro.silva
 */
@Service("empresaRN")
public class EmpresaRN {
    
    @Autowired
    private EmpresaDAO empresaDAO;
    
    public List<Empresa> listar(){
        return getEmpresaDAO().listar();
    }

    public EmpresaDAO getEmpresaDAO() {
        return empresaDAO;
    }

    public void setEmpresaDAO(EmpresaDAO empresaDAO) {
        this.empresaDAO = empresaDAO;
    }
    
}
