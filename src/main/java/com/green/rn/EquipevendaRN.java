/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.EquipevendaDAO;
import com.green.modelo.Equipevenda;
import com.green.modelo.Funcionario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author leandro.silva
 */
@Service("equipevendaRN")
public class EquipevendaRN {

    @Autowired
    private EquipevendaDAO equipevendaDAO;

    public List<Equipevenda> listar() {
        return getEquipevendaDAO().listar();
    }
    public List<Equipevenda> listarComEquipe(){
        return getEquipevendaDAO().listarComEquipe();
    }

    public void salvar(Equipevenda equipevenda) {
        getEquipevendaDAO().salvar(equipevenda);
    }

    @Transactional("tmGreen")
    public void atualizar(Equipevenda equipevenda) {
        getEquipevendaDAO().atualizar(equipevenda);
    }

    public List<Equipevenda> listarPorGerente(Funcionario gerente) {
        return getEquipevendaDAO().listarPorGerente(gerente);
    }

    public EquipevendaDAO getEquipevendaDAO() {
        return equipevendaDAO;
    }

    public void setEquipevendaDAO(EquipevendaDAO equipevendaDAO) {
        this.equipevendaDAO = equipevendaDAO;
    }

    public Equipevenda carregar(Integer id) {
    return getEquipevendaDAO().carregar(id);
    }
}
