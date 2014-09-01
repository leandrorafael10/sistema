/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.BrindeDAO;
import com.green.modelo.Brinde;
import com.green.util.ContextoBean;
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
@Service("brindeRN")
public class BrindeRN {
    @Autowired
    private BrindeDAO brindeDAO;

    public List<Brinde> listar(){
        return getBrindeDAO().listar();
    }
    
    public Brinde carregar(Integer id){
        return getBrindeDAO().carregar(id);
    }
    @Transactional (value = "tmGreen")
    public void salvar(Brinde brinde){
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        brinde.setDTInc(new Date());
        brinde.setIDUsuario(contextoBean.getUsuarioLogado());
        brinde.setAtivo(true);
        getBrindeDAO().salvar(brinde);
    }
    public List listarPeriodo(Date inicio, Date fim) {
        return getBrindeDAO().listarPeriodo(inicio, fim);
    }
    public BrindeDAO getBrindeDAO() {
        return brindeDAO;
    }
    

    public void setBrindeDAO(BrindeDAO brindeDAO) {
        this.brindeDAO = brindeDAO;
    }


    @Transactional("tmGreen")
    public void atualizar(Brinde b) {
        b.setIDUsuarioAlt(ContextoUtil.getContextoBean().getUsuarioLogado());
        b.setDTAlt(new Date());
        getBrindeDAO().atualizar(b);
    }
    
    
    
}
