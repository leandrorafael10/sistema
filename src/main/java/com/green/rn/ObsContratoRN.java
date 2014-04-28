/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.ObsContratoDAO;
import com.green.modelo.ContratoMidia;
import com.green.modelo.ObsContrato;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leandro.silva
 */
@Service("obsContratoRN")
public class ObsContratoRN {

    @Autowired
    private ObsContratoDAO obsContratoDAO;

    public void salvar(ObsContrato obsContrato) {
        getObsContratoDAO().salvar(obsContrato);
    }

    public void atualizar(ObsContrato obsContrato) {
        getObsContratoDAO().atualizar(obsContrato);

    }

    public ObsContratoDAO getObsContratoDAO() {
        return obsContratoDAO;
    }

    public void setObsContratoDAO(ObsContratoDAO obsContratoDAO) {
        this.obsContratoDAO = obsContratoDAO;
    }

    public List<ObsContrato> buscarPorContrato(ContratoMidia midia) {
        return getObsContratoDAO().buscaPorContrato(midia);
    }
}
