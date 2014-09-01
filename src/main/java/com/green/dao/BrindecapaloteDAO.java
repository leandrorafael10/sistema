/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Brindecapalote;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("brindecapaloteDAO")
public class BrindecapaloteDAO {

    @Autowired
    private SessionFactory sf;

    public void salvar(Brindecapalote brindecapalote) {
        getSf().getCurrentSession().save(brindecapalote);

    }

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

}
