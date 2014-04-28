/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.TerceirosDAO;
import com.green.modelo.Funcao;
import com.green.modelo.Terceiros;
import com.green.util.ContextoUtil;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author leandro.silva
 */
@Service ("terceirosRN")
public class TerceirosRN {
    @Autowired
    private TerceirosDAO terceirosDAO;

    @Transactional(value = "tmGreen")
    public void salvar(Terceiros terceiros){
        terceiros.setIDUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());
        terceiros.setDTInc(new Date());
        terceiros.setAtivo(Boolean.TRUE);
        getTerceirosDAO().salvar(terceiros);
    }
    @Transactional(value = "tmGreen")
    public void atualizar(Terceiros terceiros){
        getTerceirosDAO().atualizar(terceiros);
    }
    @Transactional(value = "tmGreen")
    public void demitirTerceiro(Terceiros terceiros){
        terceiros.setAtivo(!terceiros.getAtivo());
        terceiros.setDTCancel(new Date());
        getTerceirosDAO().atualizar(terceiros);
    }
    
    public List<Terceiros> listarPorFuncao(Funcao funcao){
        return getTerceirosDAO().listarPorFuncao(funcao);
    }
     public List<Terceiros> listarPorEquipe(Terceiros t){
         return getTerceirosDAO().listarPorEquipe(t);
     }
    public Terceiros carregar(Integer id){
        return getTerceirosDAO().carregar(id);
    }
    public TerceirosDAO getTerceirosDAO() {
        return terceirosDAO;
    }

    public void setTerceirosDAO(TerceirosDAO terceirosDAO) {
        this.terceirosDAO = terceirosDAO;
    }
    
    
}
