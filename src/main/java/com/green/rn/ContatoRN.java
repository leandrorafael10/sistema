/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.ContatoDAO;
import com.green.modelo.Contato;
import com.green.modelo.Pessoa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leandro.silva
 */
@Service("contatoRN")
public class ContatoRN {
    
    @Autowired
    private ContatoDAO contatoDAO;

    public ContatoDAO getContatoDAO() {
        return contatoDAO;
    }

    public void setContatoDAO(ContatoDAO contatoDAO) {
        this.contatoDAO = contatoDAO;
    }
    
    public Integer salvar(Contato contato){
        return getContatoDAO().salvar(contato);
    }
    public List<Contato> contatoPessoa(Pessoa pessoa){
        return  getContatoDAO().contatoPessoa(pessoa);
    }
    public Contato buscaPorContato(Pessoa pessoa){
        return getContatoDAO().buscarPorPessoa(pessoa);
    }
}
