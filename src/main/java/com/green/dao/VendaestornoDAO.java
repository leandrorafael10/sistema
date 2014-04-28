package com.green.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.modelo.Vendaestorno;

@Repository("vendaestornoDAO")
public class VendaestornoDAO {
	
	
	@Autowired
	private SessionFactory sf;
	
	public void salvar (Vendaestorno vendaestorno){
		getSf().getCurrentSession().save(vendaestorno);
	}
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	
	

}
