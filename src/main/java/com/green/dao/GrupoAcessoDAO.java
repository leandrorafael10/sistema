/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Grupoacesso;
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
@Repository("grupoAcessoDAO")
public class GrupoAcessoDAO {
    
    @Autowired
    private SessionFactory sf;

    public List<Grupoacesso> listar(){
        Criteria criteria = getSf().getCurrentSession().createCriteria(Grupoacesso.class);
        return criteria.list();
    }
    public Grupoacesso carregar(Integer codigo){
        Criterion filtro = Restrictions.eq("iDGrupoAcesso", codigo);
        Criteria criteria = getSf().getCurrentSession().createCriteria(Grupoacesso.class);
        criteria.add(filtro);
        return (Grupoacesso)criteria.uniqueResult();
    }
    
    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
}
