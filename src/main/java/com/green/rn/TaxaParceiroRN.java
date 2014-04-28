/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.TaxaParceiroDAO;
import com.green.modelo.TaxaParceiro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leandro.silva
 */
@Service("taxaParceiroRN")
public class TaxaParceiroRN {
    
    @Autowired
    private TaxaParceiroDAO taxaParceiroDAO;

    public void savar(TaxaParceiro tp){
        getTaxaParceiroDAO().salvar(tp);
    }
    public List<TaxaParceiro> listar(){
        return getTaxaParceiroDAO().listar();
    }
    
    
    public TaxaParceiroDAO getTaxaParceiroDAO() {
        return taxaParceiroDAO;
    }

    public void setTaxaParceiroDAO(TaxaParceiroDAO taxaParceiroDAO) {
        this.taxaParceiroDAO = taxaParceiroDAO;
    }
    
    
}
