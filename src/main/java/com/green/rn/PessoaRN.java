/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.PessoaDao;
import com.green.modelo.Pessoa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leandro.silva
 */
@Service("pessoaRN")
public class PessoaRN {

    @Autowired
    private PessoaDao PessoaDao;
    
    
   public Pessoa buscaCpfCnpj(String pessoa){
       return getPessoaDao().buscaCpfCnpj(pessoa);
   }
   
    public Pessoa carregar (Integer k){
        return getPessoaDao().carregar(k);
    } 
    public List<Pessoa> listar(){
        return getPessoaDao().listar();
    }
    public void excluir(Pessoa pessoa){
        getPessoaDao().excluir(pessoa);
    }
   
    public Integer salvar(Pessoa pessoa){
       return getPessoaDao().salvar(pessoa);
    }
    

    public PessoaDao getPessoaDao() {
        return PessoaDao;
    }

    public void setPessoaDao(PessoaDao pessoaDao) {
        this.PessoaDao = pessoaDao;
    }
}
