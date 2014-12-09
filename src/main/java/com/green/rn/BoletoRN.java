/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.BoletoDAO;
import com.green.modelo.Boleto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leandro.silva
 */
@Service("boletoRN")
public class BoletoRN {

    @Autowired
    private BoletoDAO boletoDAO;

    public Boleto buscarBoletoPadrao() {
        return getBoletoDAO().buscarBoletoPadrao();
    }

    public BoletoDAO getBoletoDAO() {
        return boletoDAO;
    }

    public void setBoletoDAO(BoletoDAO boletoDAO) {
        this.boletoDAO = boletoDAO;
    }

}
