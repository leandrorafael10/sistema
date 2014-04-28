/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Ccusto;
import com.green.rn.CCustoRN;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name="ccustoBean")
@ViewScoped
public class CCustoBean implements Serializable{
    
    @ManagedProperty("#{ccustoRN}")
    private CCustoRN cCustoRN;
    
    private List<Ccusto> ccustos = new ArrayList<Ccusto>();
    private List<Ccusto> filtroccustos = new ArrayList<Ccusto>();
    private Ccusto ccustoNovo = new Ccusto();
    private Ccusto ccustoEditado =new Ccusto();
    private Ccusto ccusto = new Ccusto();
    
    @PostConstruct
    private void init(){
        setCcustos(getcCustoRN().listar());
    }
    
    public void listando(){
        setCcustos(getcCustoRN().listar());
    }
    public void salvar(){
        getcCustoRN().salvar(getCcustoNovo());
        setCcustos(getcCustoRN().listar());
    }
    public void atualizar(){
        getcCustoRN().atualizar(getCcustoEditado(),getCcusto());
    }
    public void excluir(){
        getcCustoRN().excluir(getCcusto());
    }


    public Ccusto getCcusto() {
        return ccusto;
    }

    public void setCcusto(Ccusto ccusto) {
        this.ccusto = ccusto;
    }
     
    public Ccusto getCcustoNovo() {
        return ccustoNovo;
    }

    public void setCcustoNovo(Ccusto ccustoNovo) {
        this.ccustoNovo = ccustoNovo;
    }
    
    
    public Ccusto getCcustoEditado() {
        return ccustoEditado;
    }

    public void setCcustoEditado(Ccusto ccustoEditado) {
        this.ccustoEditado = ccustoEditado;
    }
    
    public List<Ccusto> getCcustos() {
        return ccustos;
    }

    public void setCcustos(List<Ccusto> ccustos) {
        this.ccustos = ccustos;
    }
    
    public CCustoRN getcCustoRN() {
        return cCustoRN;
    }

    public void setcCustoRN(CCustoRN cCustoRN) {
        this.cCustoRN = cCustoRN;
    }

    public List<Ccusto> getFiltroccustos() {
        return filtroccustos;
    }

    public void setFiltroccustos(List<Ccusto> filtroccustos) {
        this.filtroccustos = filtroccustos;
    }
    
     
}
