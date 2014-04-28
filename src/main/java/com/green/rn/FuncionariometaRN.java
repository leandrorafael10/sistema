/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.EquipevendaDAO;
import com.green.dao.FuncionariometaDAO;
import com.green.modelo.Equipevenda;
import com.green.modelo.Funcionario;
import com.green.modelo.Funcionariometa;
import com.green.util.ContextoUtil;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author leandro.silva
 */
@Service("funcionariometaRN")
public class FuncionariometaRN {

    @Autowired
    private FuncionariometaDAO funcionariometaDAO;
    @Autowired
    private EquipevendaDAO equipevendaDAO;

    public void salvar(Funcionariometa funcionariometa) {
        getFuncionariometaDAO().salvar(funcionariometa);
    }

    @Transactional("tmGreen")
    public void atualizar(Funcionariometa funcionariometa) {
        funcionariometa.setDtalt(new Date());
        getFuncionariometaDAO().atualizar(funcionariometa);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado com sucesso!", "Atualizado com sucesso!"));
    }

    @Transactional("tmGreen")
    public void salvarNova(Funcionariometa f, Equipevenda e) {
        boolean verifica = true;
        for (Funcionariometa e1 : e.getFuncionariometaList()) {
            if (e1.getAno() == f.getAno() && e1.getMes() == f.getMes()) {
                verifica = false;
            }
        }
        if (verifica) {
            f.setIDUsuarioInc(ContextoUtil.getContextoBean().getUsuarioLogado());
            f.setDTInc(new Date());
            e.getFuncionariometaList().add(f);
            getEquipevendaDAO().atualizar(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Meta inserida com sucesso!", "Meta inserida com sucesso!"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Já existe meta definida para esse periodo.Para atualizar clique em atualizar!", "Já existe meta definida para esse periodo.Para atualizar clique em atualizar!"));
        }

    }

    public Funcionariometa ultimaAtualizacao(Funcionario f) {
        return getFuncionariometaDAO().ultimaAtualizacao(f);
    }

    public List<Funcionariometa> listarPorFuncionario(Funcionario funcionario) {
        return getFuncionariometaDAO().listarPorFuncionario(funcionario);
    }

    public List<Funcionariometa> listar() {
        return getFuncionariometaDAO().listar();
    }

    public FuncionariometaDAO getFuncionariometaDAO() {
        return funcionariometaDAO;
    }

    public void setFuncionariometaDAO(FuncionariometaDAO funcionariometaDAO) {
        this.funcionariometaDAO = funcionariometaDAO;
    }

    public EquipevendaDAO getEquipevendaDAO() {
        return equipevendaDAO;
    }

    public void setEquipevendaDAO(EquipevendaDAO equipevendaDAO) {
        this.equipevendaDAO = equipevendaDAO;
    }
}
