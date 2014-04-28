/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.PracaDAO;
import com.green.modelo.Pessoa;
import com.green.modelo.Praca;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leandro.silva
 */
@Service("pracaRN")
public class PracaRN {

    @Autowired
    private PracaDAO pracaDAO;

    public PracaDAO getPracaDAO() {
        return pracaDAO;
    }

    public void setPracaDAO(PracaDAO pracaDAO) {
        this.pracaDAO = pracaDAO;
    }

    public Praca carregarPorNome(String nome) {
        return pracaDAO.carregarPorNome(nome);
    }

    public Praca carregar(Integer k) {
        return pracaDAO.carregar(k);
    }

    public List<Praca> listar() {
        return getPracaDAO().listar();
    }

    

    public List<String> listarPraca() {
        return getPracaDAO().listarPraca();
    }

    
    public List<Praca> listarPorPessoa(Pessoa p){
        return getPracaDAO().listarPorPessoa(p);
    }
}
