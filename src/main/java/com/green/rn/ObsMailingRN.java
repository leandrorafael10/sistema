/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.ObsMailingDAO;
import com.green.modelo.Obsmailing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leandro.silva
 */
@Service("obsMailingRN")
public class ObsMailingRN {
    
    @Autowired
    private ObsMailingDAO obsMailingDAO;

    public void salvar(Obsmailing obsmailing){
        getObsMailingDAO().salvar(obsmailing);
    }
    public ObsMailingDAO getObsMailingDAO() {
        return obsMailingDAO;
    }

    public void setObsMailingDAO(ObsMailingDAO obsMailingDAO) {
        this.obsMailingDAO = obsMailingDAO;
    }
    
    
}
