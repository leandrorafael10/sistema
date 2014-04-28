/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Equipevenda;
import com.green.modelo.Funcionario;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("equipevendaDAO")
public class EquipevendaDAO {

    @Autowired
    private SessionFactory sf;

    public List<Equipevenda> listar() {
        return (List<Equipevenda>) getSf().getCurrentSession().createQuery("from com.green.modelo.Equipevenda e "
                + " where e.iDPromotor.ativo = :ativo order by e.iDPromotor.iDPessoa.razao").setBoolean("ativo", true).list();
    }
    /*
     * lista de promotores ja com equipe formada
     */
    public List<Equipevenda> listarComEquipe(){
        return (List<Equipevenda>) getSf().getCurrentSession().createQuery("from com.green.modelo.Equipevenda e "
                + " where e.iDPromotor.ativo = :ativo and iDGerente is not null order by e.iDPromotor.iDPessoa.razao").setBoolean("ativo", true).list();
        
    }

    public List<Equipevenda> listarPorGerente(Funcionario gerente) {
        List<Equipevenda> list = new ArrayList<>();
        if (gerente.getIDFuncionario() != null) {
            Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Equipevenda eq where eq.iDGerente = :gerente and eq.iDPromotor.ativo = :ativo  order by eq.iDPromotor.iDPessoa.razao ").setParameter("gerente", gerente).setBoolean("ativo", true);
        return (List<Equipevenda>) query.list();
        } else {
            return list;
        }
    }
    public List<Equipevenda> listarPorPromotor(Funcionario gerente) {
        List<Equipevenda> list = new ArrayList<>();
        if (gerente.getIDFuncionario() != null) {
            Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Equipevenda eq where eq.iDGerente = :promotor ").setParameter("promotor", gerente);
        return (List<Equipevenda>) query.list();
        } else {
            return list;
        }
    }
    public void excluir(Equipevenda equipevenda){
        getSf().getCurrentSession().delete(equipevenda);
    }
    
    

    public void salvar(Equipevenda equipevenda) {
        getSf().getCurrentSession().save(equipevenda);
    }

    public void atualizar(Equipevenda equipevenda) {
        getSf().getCurrentSession().update(equipevenda);
    }
    public Equipevenda buscarPorPromotor(Funcionario funcionario){
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Equipevenda eq where eq.iDPromotor = :promotor ").setParameter("promotor", funcionario);
        return (Equipevenda)query.uniqueResult();
    }

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    public Equipevenda carregar(Integer id) {
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Equipevenda e where e.idequipevenda = :id").setInteger("id", id);
        return (Equipevenda)query.uniqueResult();
    }
}
