/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.CalculoComissao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("calculoComissaoDAO")
public class CalculoComissaoDAO {
    @Autowired
    private SessionFactory sf;

   public CalculoComissao carregarUltimo(){
      // Query  query=  getSf().getCurrentSession().createQuery("select max(idcalculoComissao) from com.modelo.CalculoComissao");
      // Integer id = (int)query.uniqueResult();
       Query  query=  getSf().getCurrentSession().createQuery("from com.green.modelo.CalculoComissao c where c.idcalculoComissao = (select max(idcalculoComissao) from com.green.modelo.CalculoComissao)");
       CalculoComissao cc = (CalculoComissao)query.uniqueResult();
       return cc;
   }
   public void salvar(CalculoComissao calculoComissao){
       getSf().getCurrentSession().save(calculoComissao);
   }
   public void alterar(CalculoComissao comissao){
       getSf().getCurrentSession().merge(comissao);
   }
    
    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    
    
}
