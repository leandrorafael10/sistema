/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Grupoacesso;
import com.green.rn.GrupoAcessoRN;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name="grupoAcessoBean")
@RequestScoped
public class GrupoAcessoBean {
    
    @ManagedProperty("#{grupoAcessoRN}")
    private GrupoAcessoRN grupoAcessoRN;
    
    private List<Grupoacesso> grupoacessos;

    @PostConstruct
    private void init(){
        setGrupoacessos(grupoAcessoRN.listar());
    }

    public List<Grupoacesso> getGrupoacessos() {
        return grupoacessos;
    }

    public void setGrupoacessos(List<Grupoacesso> grupoacessos) {
        this.grupoacessos = grupoacessos;
    }
    
    public GrupoAcessoRN getGrupoAcessoRN() {
        return grupoAcessoRN;
    }

    public void setGrupoAcessoRN(GrupoAcessoRN grupoAcessoRN) {
        this.grupoAcessoRN = grupoAcessoRN;
    }
    
    
}
