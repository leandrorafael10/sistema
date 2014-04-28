/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.RamoAtividadeDAO;
import com.green.modelo.Ramoatividade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leandro.silva
 */
@Service("ramoAtividadeRN")
public class RamoAtividadeRN {
    
    @Autowired
    private RamoAtividadeDAO ramoAtividadeDAO;

    public List<Ramoatividade> listar(){
        return getRamoAtividadeDAO().listar();
    }
    public Ramoatividade buscar(Integer id){
        return getRamoAtividadeDAO().buscar(id);
    } 
    public RamoAtividadeDAO getRamoAtividadeDAO() {
        return ramoAtividadeDAO;
    }

    public void setRamoAtividadeDAO(RamoAtividadeDAO ramoAtividadeDAO) {
        this.ramoAtividadeDAO = ramoAtividadeDAO;
    }
    
    
}
