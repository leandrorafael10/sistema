/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.FilialDAO;
import com.green.modelo.Filial;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leandro.silva
 */
@Service("filialRN")
public class FilialRN {
    
    @Autowired
    private FilialDAO filialDAO;

    public List<Filial> listar(){
        return getFilialDAO().listar();
    }
    
    public Filial buscar(Integer id){
        return getFilialDAO().buscar(id);
    }
    
    public FilialDAO getFilialDAO() {
        return filialDAO;
    }

    public void setFilialDAO(FilialDAO filialDAO) {
        this.filialDAO = filialDAO;
    }
            
    
    
}
