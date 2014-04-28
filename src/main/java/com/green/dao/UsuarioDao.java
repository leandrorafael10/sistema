/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Funcionario;
import com.green.modelo.Usuario;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("usuarioDao")
public class UsuarioDao  {
    
    @Autowired
    private SessionFactory sf;

    
    public Usuario carregarPeloLogin(String log){
        Criteria c = getSf().getCurrentSession().createCriteria(Usuario.class);
        c.add(Restrictions.eq("login",log));
        return  (Usuario) c.uniqueResult();
    }
    public void salvar (Usuario usuario){
        getSf().getCurrentSession().save(usuario);
    }
    public void atualizar(Usuario usuario){
        getSf().getCurrentSession().update(usuario);
    }
    
    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    public Integer carregarPeloFuncionario(Funcionario funcionario){
        Criterion filtro = Restrictions.eq("iDFuncionario", funcionario);
        Criteria criteria = getSf().getCurrentSession().createCriteria(Usuario.class);
        criteria.add(filtro);
        Usuario u= (Usuario)criteria.uniqueResult();
        if (u ==null){
            return 0;
        }else{
            return 1;
        }
            
    }
    public List<Usuario> listar(){
        Criteria criteria = getSf().getCurrentSession().createCriteria(Usuario.class);
        return criteria.list();
    }
    
}
